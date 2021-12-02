package io.agora.spring.boot.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 响应结果
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class AgoraResponse {

	/**
	 * 响应状态码，200表示成功，非200表示失败
	 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#status
	 */
	@JsonProperty("Code")
	private int code;

	public boolean isSuccess() {
		return code == 200;
	}
	
}
