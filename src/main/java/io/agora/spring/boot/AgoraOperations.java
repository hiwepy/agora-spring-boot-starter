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
package io.agora.spring.boot;

import io.agora.spring.boot.resp.AgoraResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Tim 接口集成
 * https://cloud.tencent.com/document/product/269/42440
 */
@Slf4j
public abstract class AgoraOperations {

	public static final String PREFIX = "https://console.tim.qq.com";
	public static final String APPLICATION_JSON_VALUE = "application/json";
	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

	protected AgoraTemplate agoraTemplate;

	public AgoraOperations(AgoraTemplate agoraTemplate) {
		this.agoraTemplate = agoraTemplate;
	}

	/**
	 * 根据Agora频道名称获取用户id
	 *
	 * @param channel Agora频道名称
	 * @return 从Agora频道名称解析出来的用户ID
	 */
	protected String getUserIdByChannel(String channel) {
		return agoraTemplate.getUserIdByChannel(channel);
	}

	/**
	 * 根据用户id获取Agora频道名称
	 *
	 * @param userId 用户ID
	 * @return 用户ID生成的Agora频道名称
	 */
	protected String getChannelByUserId(String userId) {
		return agoraTemplate.getChannelByUserId(userId);
	}

	protected AgoraProperties getAgoraProperties() {
		return agoraTemplate.getAgoraProperties();
	}

	protected AgoraOkHttp3Template getAgoraOkHttp3Template(){
		return agoraTemplate.getAgoraOkHttp3Template();
	}

	protected <T extends AgoraResponse> T get(AgoraApiAddress address, String url, Class<T> cls) throws IOException {
		T res = getAgoraOkHttp3Template().get(url, cls);
		if (Objects.nonNull(res)) {
			log.info("Agora {} >> Success, url : {}, Code : {}, Body : {}", address.getOpt(), url, res.getCode());
		} else {
			log.error("Agora {} >> Failure, url : {}, Code : {}", address.getOpt(), url, res.getCode());
		}
		return res;
	}

	protected <T extends AgoraResponse> T post(AgoraApiAddress address, String url, Class<T> cls) throws IOException {
		T res = getAgoraOkHttp3Template().post(url, cls);
		if (Objects.nonNull(res)) {
			log.info("Agora {} >> Success, url : {}, Code : {}, Body : {}", address.getOpt(), url, res.getCode());
		} else {
			log.error("Agora {} >> Failure, url : {}, Code : {}", address.getOpt(), url, res.getCode());
		}
		return res;
	}

	protected <T extends AgoraResponse> T post(AgoraApiAddress address, String url, Map<String, Object> requestBody, Class<T> cls) throws IOException {
		T res = getAgoraOkHttp3Template().post(url, null, null , requestBody, cls);
		if (Objects.nonNull(res)) {
			log.info("Agora {} >> Success, url : {}, requestBody : {}, Code : {}, Body : {}", address.getOpt(), url, requestBody, res.getCode());
		} else {
			log.error("Agora {} >> Failure, url : {}, requestBody : {}, Code : {}", address.getOpt(), url, requestBody, res.getCode());
		}
		return res;
	}

	protected <T extends AgoraResponse> void asyncPost(AgoraApiAddress address, String url, Map<String, Object> bodyContent, Class<T> cls, Consumer<T> success) throws IOException {
		getAgoraOkHttp3Template().doAsyncRequest(url, AgoraOkHttp3Template.HttpMethod.POST, null,  null, bodyContent, success, null, cls);
	}

	public AgoraTemplate getAgoraTemplate() {
		return agoraTemplate;
	}

}
