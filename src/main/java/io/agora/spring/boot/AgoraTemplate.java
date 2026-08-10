package io.agora.spring.boot;

import io.agora.media.RtcTokenBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;

/**
 * Primary facade for Agora operations.
 * <p>
 * Exposes RTC token generation, channel-user lookup helpers and dedicated
 * operation factories for channel management ({@link #opsForChannel()}) and
 * cloud recording ({@link #opsForCloudRecording()}).
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 * @see <a href="https://docs.agora.io/cn/Interactive%20Broadcast/rtc_channel_event?platform=RESTful">Agora channel events</a>
 */

@Slf4j
public class AgoraTemplate {

	/** JSON content type without charset. */
	public final static String APPLICATION_JSON_VALUE = "application/json";
	/** JSON content type with UTF-8 charset. */
	public final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	/** OkHttp media type for {@code application/json}. */
	public final static MediaType APPLICATION_JSON = MediaType.parse(APPLICATION_JSON_VALUE);
	/** OkHttp media type for {@code application/json;charset=UTF-8}. */
	public final static MediaType APPLICATION_JSON_UTF8 = MediaType.parse(APPLICATION_JSON_UTF8_VALUE);

	/** Maximum number of retry attempts for API calls. */
    public static int TRY_MAX = 5;

	private static RtcTokenBuilder token = new RtcTokenBuilder();

	/** Resolves user ids and channel names. */
	private AgoraUserIdProvider userIdProvider;
	/** Underlying OkHttp template used for REST calls. */
	private AgoraOkHttp3Template agoraOkHttp3Template;
	/** Bound Agora configuration. */
	private AgoraProperties agoraProperties;

	private final AgoraChannelManagerAsyncOperations channelOps = new AgoraChannelManagerAsyncOperations(this);
	private final AgoraCloudRecordingAsyncOperations cloudRecordingOps = new AgoraCloudRecordingAsyncOperations(this);

	/**
	 * Creates a new template bound to the supplied collaborators.
	 *
	 * @param userIdProvider       the user id provider
	 * @param agoraOkHttp3Template the OkHttp template
	 * @param agoraProperties      the bound Agora properties
	 */
	public AgoraTemplate(AgoraUserIdProvider userIdProvider, AgoraOkHttp3Template agoraOkHttp3Template, AgoraProperties agoraProperties) {
		this.userIdProvider = userIdProvider;
		this.agoraOkHttp3Template = agoraOkHttp3Template;
		this.agoraProperties = agoraProperties;
	}

	/**
	 * @return async channel management operations.
	 */
	public AgoraChannelManagerAsyncOperations opsForChannel() {
		return channelOps;
	}

	/**
	 * @return async cloud recording operations.
	 */
	public AgoraCloudRecordingAsyncOperations opsForCloudRecording() {
		return cloudRecordingOps;
	}

	/**
	 * Generates an RTC token for the given user account and channel with the
	 * {@code Publisher} role.
	 *
	 * @param userId      the user account (string)
	 * @param channelName the channel name
	 * @return the generated RTC token
	 */
	public String generateToken(String userId, String channelName) {
		return this.generateToken(userId, channelName, RtcTokenBuilder.Role.Role_Publisher);
	}

	/**
	 * Generates an RTC token for the given numeric uid and channel.
	 *
	 * @param userId      the numeric user id
	 * @param channelName the channel name
	 * @param role        the token role
	 * @return the generated RTC token
	 */
    public String generateToken(int userId, String channelName, RtcTokenBuilder.Role role) {
        int timestamp = (int)(System.currentTimeMillis() / 1000 + agoraProperties.getExpirationTimeInSeconds());
        log.info("{} >> Agora Token Expiration Time : {}s ", channelName, timestamp);
        String result = token.buildTokenWithUid(agoraProperties.getAppId(), agoraProperties.getAppCertificate(),
        		channelName, userId, role, timestamp);
        log.info("{} >> Agora Token : {} << AppId:{}, AppCertificate: {}, Role : {}", channelName, result, agoraProperties.getAppId(), agoraProperties.getAppCertificate(), role);
        return result;
    }

	/**
	 * Generates an RTC token for the given user account and channel.
	 *
	 * @param userId      the user account (string)
	 * @param channelName the channel name
	 * @param role        the token role
	 * @return the generated RTC token
	 */
	public String generateToken(String userId, String channelName, RtcTokenBuilder.Role role) {
		int timestamp = (int)(System.currentTimeMillis() / 1000 + agoraProperties.getExpirationTimeInSeconds());
		log.info("{} >> Agora Token Expiration Time : {}s ", channelName, timestamp);
		String result = token.buildTokenWithUserAccount(agoraProperties.getAppId(), agoraProperties.getAppCertificate(),
				channelName, userId, role, timestamp);
		log.info("{} >> Agora Token : {} << AppId:{}, AppCertificate: {}, Role : {}", channelName, result, agoraProperties.getAppId(), agoraProperties.getAppCertificate(), role);
		return result;
	}


	/**
	 * Resolves a user id for the given Agora channel name.
	 *
	 * @param channel the Agora channel name
	 * @return the user id resolved from the channel name
	 */
	public String getUserIdByChannel(String channel) {
		return userIdProvider.getUserIdByChannel(agoraProperties.getAppId(), channel);
	}

	/**
	 * Resolves an Agora channel name for the given user id.
	 *
	 * @param userId the user id
	 * @return the channel name generated for the user id
	 */
	public String getChannelByUserId(String userId) {
		return userIdProvider.getChannelByUserId(agoraProperties.getAppId(), userId);
	}

	/** @return the bound Agora properties. */
	public AgoraProperties getAgoraProperties() {
		return agoraProperties;
	}

	/** @return the underlying OkHttp template. */
	public AgoraOkHttp3Template getAgoraOkHttp3Template() {
		return agoraOkHttp3Template;
	}
}
