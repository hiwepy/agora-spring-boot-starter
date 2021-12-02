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
package io.agora.spring.boot.req;

/**
 * 应用设置
 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#appsCollection
 */
public enum RecordingAppCombinationPolicy {

	/**
	 * 合流模式  ：（默认）除延时转码外，均选用此种方式
	 */
	DEFAULT("default", "（默认）除延时转码外，均选用此种方式"),
	/**
	 *如需延时转码，则选用此种方式
	 */
	POSTPONE_TRANSCODING("postpone_transcoding", "如需延时转码，则选用此种方式"),

	;

	private String name;

	private String desc;

	private RecordingAppCombinationPolicy(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}

	public boolean equals(RecordingAppCombinationPolicy region) {
		return this.compareTo(region) == 0;
	}

}
