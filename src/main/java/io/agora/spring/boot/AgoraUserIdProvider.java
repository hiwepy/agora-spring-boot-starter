/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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

/**
 * Strategy for mapping between Agora channel names and application user ids.
 * <p>The default implementation is an identity mapping, suitable when channel
 * names and user ids coincide.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public interface AgoraUserIdProvider {

	/**
	 * Resolves a user id for the given channel.
	 *
	 * @param appid   the Agora application id
	 * @param channel the channel name
	 * @return the resolved user id (defaults to the channel name)
	 */
	default String getUserIdByChannel(String appid, String channel)  {
		return channel;
	}

	/**
	 * Resolves a channel name for the given user id.
	 *
	 * @param appid  the Agora application id
	 * @param userId the user id
	 * @return the resolved channel name (defaults to the user id)
	 */
	default String getChannelByUserId(String appid, String userId) {
		return userId;
	}

}
