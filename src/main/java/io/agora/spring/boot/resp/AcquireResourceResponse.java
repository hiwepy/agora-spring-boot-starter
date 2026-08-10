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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;/**
 * Response of the {@code acquire} cloud recording request, carrying the resource id.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude( JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class AcquireResourceResponse extends AgoraResponse {

	/**
	 * Cloud recording resource ID. Use this resource ID to start a cloud recording. The resource ID is valid for 5 minutes; re-acquire it after it expires.
	 */
	@JsonProperty("resourceId")
	private String resourceId;

	/**
	 * 2、云端录制使用的频道名
	 */
	@JsonIgnore
	private String cname;
}
