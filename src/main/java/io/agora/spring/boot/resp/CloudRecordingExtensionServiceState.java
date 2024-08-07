/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.agora.spring.boot.resp;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CloudRecordingExtensionServiceState {

	/**
	 * 1、扩展服务类型。 
	 * "aliyun_vod_service" 代表阿里云视频点播服务
	 * "web_recorder_service" 代表页面录制
	 * "rtmp_publish_service" 代表页面录制并推流到 CDN
	 */
	@JsonProperty("serviceName")
	private String serviceName;
	
	/**
	 * 2、该扩展服务的状态信息
	 */
	@JsonProperty("payload")
	private JSONObject payload;
	
}
