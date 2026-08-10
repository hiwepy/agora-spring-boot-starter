package io.agora.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Base response envelope for all Agora REST API calls.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class AgoraResponse {

	/**
	 * Response status code; 200 means success, any other value means failure
	 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#status
	 */
	@JsonProperty("Code")
	private int code;

	/**
	 * 本次请求的状态，true 请求成功，false 预留
	 * https://docs.agora.io/cn/Video/rtc_channel_management_restfulapi?platform=RESTful#%E6%9F%A5%E8%AF%A2%E7%94%A8%E6%88%B7%E7%8A%B6%E6%80%81
	 */
	@JsonProperty("success")
	private boolean success;

	public boolean isSuccess() {
		return code == 200 || success == true;
	}

}
