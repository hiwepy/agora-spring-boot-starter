package io.agora.spring.boot;

import java.io.IOException;
import java.util.Base64;
import java.util.function.Consumer;

import io.agora.spring.boot.resp.AgoraResponse;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.agora.media.RtcTokenBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * https://docs.agora.io/cn/Interactive%20Broadcast/rtc_channel_event?platform=RESTful
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 */

@Slf4j
public class AgoraTemplate {

	public final static String APPLICATION_JSON_VALUE = "application/json";
	public final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	public final static MediaType APPLICATION_JSON = MediaType.parse(APPLICATION_JSON_VALUE);
	public final static MediaType APPLICATION_JSON_UTF8 = MediaType.parse(APPLICATION_JSON_UTF8_VALUE);

    public static int TRY_MAX = 5;

	private static RtcTokenBuilder token = new RtcTokenBuilder();

	private ObjectMapper objectMapper;
	private OkHttpClient okhttp3Client;
	private AgoraProperties agoraProperties;
	private AgoraUserIdProvider userIdProvider;

	private final AgoraChannelManagerAsyncOperations channelOps = new AgoraChannelManagerAsyncOperations(this);
	private final AgoraCloudRecordingAsyncOperations cloudRecordingOps = new AgoraCloudRecordingAsyncOperations(this);

	public AgoraTemplate(AgoraProperties agoraProperties, ObjectMapper objectMapper, OkHttpClient okhttp3Client, AgoraUserIdProvider userIdProvider) {
		this.agoraProperties = agoraProperties;
		this.objectMapper = objectMapper;
		this.okhttp3Client = okhttp3Client;
		this.userIdProvider = userIdProvider;
	}

	public AgoraChannelManagerAsyncOperations opsForChannel() {
		return channelOps;
	}

	public AgoraCloudRecordingAsyncOperations opsForCloudRecording() {
		return cloudRecordingOps;
	}

	public String generateToken(String userId, String channelName) {
		return this.generateToken(userId, channelName, RtcTokenBuilder.Role.Role_Publisher);
	}

    public String generateToken(String userId, String channelName, RtcTokenBuilder.Role role) {
        int timestamp = (int)(System.currentTimeMillis() / 1000 + agoraProperties.getExpirationTimeInSeconds());
        log.info("{} >> Agora Token Expiration Time : {}s ", channelName, timestamp);
        String result = token.buildTokenWithUserAccount(agoraProperties.getAppId(), agoraProperties.getAppCertificate(),
        		channelName, userId, role, timestamp);
        log.info("{} >> Agora Token : {} << AppId:{}, AppCertificate: {}, Role : {}", channelName, result, agoraProperties.getAppId(), agoraProperties.getAppCertificate(), role);
        return result;
    }

    /**
     * restful请求认证
     */
    private String getAuthorizationHeader() {
        // 1、拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = agoraProperties.getLoginKey() + ":" + agoraProperties.getLoginSecret();
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 2、创建 authorization header
        return "Basic " + base64Credentials;
    }

    public <T> T readValue(String json, Class<T> cls) {
		try {
			return objectMapper.readValue(json, cls);
		} catch (IOException e) {
			log.error(e.getMessage());
			return BeanUtils.instantiateClass(cls);
		}
	}

    public <T extends AgoraResponse> T requestInvoke(String url, Object params, Class<T> cls) {
		long start = System.currentTimeMillis();
		T res = null;
		try {

	        String authorizationHeader = getAuthorizationHeader();
			String paramStr = objectMapper.writeValueAsString(params);
			log.info("Agora Request Authorization : {}, Param : {}", authorizationHeader, paramStr);

			RequestBody requestBody = RequestBody.create(APPLICATION_JSON_UTF8, paramStr);
			Request request = new Request.Builder().url(url)
                    .header("Authorization", authorizationHeader)
                    .header("Content-Type", APPLICATION_JSON_VALUE)
					.post(requestBody).build();

			try(Response response = okhttp3Client.newCall(request).execute();) {
				if (response.isSuccessful()) {
					String body = response.body().string();
					log.info("Agora Request Success : url : {}, params : {}, code : {}, body : {} , use time : {} ", url, params, response.code(), body , System.currentTimeMillis() - start);
					res = objectMapper.readValue(body, cls);
	            } else {
	            	log.error("Agora Request Failure : url : {}, params : {}, code : {}, message : {}, use time : {} ", url, params, response.code(), response.message(), System.currentTimeMillis() - start);
	            	res = BeanUtils.instantiateClass(cls);
				}
				res.setCode(response.code());
			}
		} catch (Exception e) {
			log.error("Agora Request Error : url : {}, params : {}, use time : {} ,  {}", url, params, e.getMessage(), System.currentTimeMillis() - start);
			res = BeanUtils.instantiateClass(cls);
			res.setCode(500);
		}
		return res;
	}

	public void requestAsyncInvoke(String url, Object params, Consumer<Response> consumer) {

		long start = System.currentTimeMillis();

		try {

			String authorizationHeader = getAuthorizationHeader();
			String paramStr = objectMapper.writeValueAsString(params);
			log.info("Agora Async Request Authorization : {}, Param : {}", authorizationHeader, paramStr);

			RequestBody requestBody = RequestBody.create(APPLICATION_JSON_UTF8, paramStr);
			Request request = new Request.Builder()
					.url(url)
					.header("Authorization", authorizationHeader)
                    .header("Content-Type", APPLICATION_JSON_VALUE)
					.post(requestBody).build();
			okhttp3Client.newCall(request).enqueue(new Callback() {

	            @Override
	            public void onFailure(Call call, IOException e) {
	            	log.error("Agora Async Request Failure : url : {}, params : {}, message : {}, use time : {} ", url, params, e.getMessage(), System.currentTimeMillis() - start);
	            }

	            @Override
	            public void onResponse(Call call, Response response) {
                	if (response.isSuccessful()) {
    					log.info("Agora Async Request Success : url : {}, params : {}, code : {}, message : {} , use time : {} ", url, params, response.code(), response.message(), System.currentTimeMillis() - start);
    					consumer.accept(response);
                    } else {
                    	log.error("Agora Async Request Failure : url : {}, params : {}, code : {}, message : {}, use time : {} ", url, params, response.code(), response.message(), System.currentTimeMillis() - start);
        			}
	            }

	        });
		} catch (Exception e) {
			log.error("Agora Async Request Error : url : {}, params : {}, message : {} , use time : {} ", url, params, e.getMessage(), System.currentTimeMillis() - start);
		}
	}

	/**
	 * 根据Agora频道名称获取用户id
	 *
	 * @param channel Agora频道名称
	 * @return 从Agora频道名称解析出来的用户ID
	 */
	public String getUserIdByChannel(String channel) {
		return userIdProvider.getUserIdByChannel(agoraProperties.getAppId(), channel);
	}

	/**
	 * 根据用户id获取Agora频道名称
	 *
	 * @param userId 用户ID
	 * @return 用户ID生成的Agora频道名称
	 */
	public String getChannelByUserId(String userId) {
		return userIdProvider.getChannelByUserId(agoraProperties.getAppId(), userId);
	}

	public AgoraProperties getAgoraProperties() {
		return agoraProperties;
	}

}
