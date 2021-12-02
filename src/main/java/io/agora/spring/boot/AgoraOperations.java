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

import java.io.IOException;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;

import io.agora.spring.boot.resp.AgoraResponse;
import lombok.extern.slf4j.Slf4j;

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
	
	protected <T extends AgoraResponse> T request(AgoraApiAddress address, String url, Object params, Class<T> cls) {
		T res =  getAgoraTemplate().requestInvoke(url, params, cls);
		if (res.isSuccess()) {
			log.info("Agora {} >> Success, url : {}, params : {}, Code : {}, Body : {}", address.getOpt(), url, params, res.getCode());
		} else {
			log.error("Agora {} >> Failure, url : {}, params : {}, Code : {}", address.getOpt(), url, params, res.getCode());
		}
		return res;
	}
	
	protected <T extends AgoraResponse> void asyncRequest(AgoraApiAddress address, String url, Object params, Class<T> cls, Consumer<T> consumer) {
		getAgoraTemplate().requestAsyncInvoke(url, params, (response) -> {
			if (response.isSuccessful()) {
				try {
					String body = response.body().string();
					T res = getAgoraTemplate().readValue(body, cls);
					if (res.isSuccess()) {
						log.info("Agora {} >> Success, url : {}, params : {}, Code : {}, Body : {}", address.getOpt(), url, params, res.getCode(), body);
					} else {
						log.error("Agora {} >> Failure, url : {}, params : {}, Code : {}", address.getOpt(), url, params, res.getCode());
					}
					consumer.accept(res);
				} catch (IOException e) {
					log.error("Agora {} >> Response Parse Error : {}", address.getOpt(), e.getMessage());
					T res = BeanUtils.instantiateClass(cls);
					consumer.accept(res);
				}
            } else {
            	T res = BeanUtils.instantiateClass(cls);
				consumer.accept(res);
            }
		});
	}
	
	public AgoraTemplate getAgoraTemplate() {
		return agoraTemplate;
	}
	
}
