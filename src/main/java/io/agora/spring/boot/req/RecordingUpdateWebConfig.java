package io.agora.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Web recording parameter update.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RecordingUpdateWebConfig {

	/**
	 * 1、 截图周期（s），云端录制会按此周期定期截图。取值范围是 [1, 3600]，默认值 10。
	 */
	@JsonProperty("captureInterval")
	private Integer captureInterval = 10;

	/**
	 * 2、 由多个字符串组成的数组，指定截图的文件格式。目前只支持 ["jpg"]，即生成 JPG 截图文件
	 */
	@JsonProperty("fileType")
    private List<String> fileTypes = Arrays.asList("jpg");

}
