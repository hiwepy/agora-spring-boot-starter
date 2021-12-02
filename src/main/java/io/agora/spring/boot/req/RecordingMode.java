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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 录制模式
 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#acquire-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B
 */
public enum RecordingMode {

	/**
	 * 单流模式：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
	 */
	INDIVIDUAL("individual", "分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件 "),
	/**
	 * 合流模式  ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
	 */
	MIX("mix", "（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件"),
	/**
	 *页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
	 */
	WEB("web", "将指定网页的页面内容和音频混合录制为一个音视频文件"),

	;

	private String name;

	private String desc;

	private static Logger log = LoggerFactory.getLogger(RecordingMode.class);

	private RecordingMode(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
 
	public static RecordingMode getByName(String name) {
		for (RecordingMode region : RecordingMode.values()) {
			if (region.getName().equalsIgnoreCase(name)) {
				return region;
			}
		}
		log.error("Cannot found RecordingMode with name '" + name + "'.");
		return RecordingMode.MIX;
	}

	public boolean equals(RecordingMode region) {
		return this.compareTo(region) == 0;
	}

}
