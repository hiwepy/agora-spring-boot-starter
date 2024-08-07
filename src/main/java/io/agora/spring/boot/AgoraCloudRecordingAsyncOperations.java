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

import com.google.common.collect.ImmutableMap;
import io.agora.spring.boot.req.*;
import io.agora.spring.boot.resp.CloudRecordingStartResponse;
import io.agora.spring.boot.resp.CloudRecordingStopResponse;
import io.agora.spring.boot.resp.CloudRecordingUpdateLayoutResponse;
import io.agora.spring.boot.resp.CloudRecordingUpdateResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class AgoraCloudRecordingAsyncOperations extends AgoraCloudRecordingOperations {

	public AgoraCloudRecordingAsyncOperations(AgoraTemplate agoraTemplate) {
		super(agoraTemplate);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param storageConfig  第三方云存储的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId, RecordingStorageConfig storageConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {
		this.asyncStartRecording(channelName, uid, token, resourceId, RecordingMode.MIX, null, null, null, null, storageConfig, null, consumer);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId,
									RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {
		this.asyncStartRecording(channelName, uid, token, resourceId, RecordingMode.MIX, null, recordingConfig, recordingFileConfig, null, storageConfig, null, consumer);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId, RecordingMode mode,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {
		this.asyncStartRecording(channelName, uid, token, resourceId, mode, null, recordingConfig, recordingFileConfig, null, storageConfig, null, consumer);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param appsCollection  应用设置
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {
		this.asyncStartRecording(channelName, uid, token, resourceId, mode, appsCollection, recordingConfig, recordingFileConfig, null, storageConfig, null, consumer);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param appsCollection  应用设置
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param snapshotConfig 截图周期、截图文件的设置
     * @param storageConfig  第三方云存储的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
		    RecordingSnapshotConfig snapshotConfig,
			RecordingStorageConfig storageConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {
		this.asyncStartRecording(channelName, uid, token, resourceId, mode, appsCollection, recordingConfig, recordingFileConfig, snapshotConfig, storageConfig, null, consumer);
	}

	/**
     * 2、开始云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
	 * @param token 用于鉴权的动态密钥。如果你的项目已启用 App 证书，则务必在该参数中传入你项目的动态密钥。
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param appsCollection  应用设置
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param snapshotConfig 截图周期、截图文件的设置
     * @param storageConfig  第三方云存储的设置
     * @param extensionServiceConfig 扩展服务的设置，目前包括阿里云视频点播服务和页面录制的设置
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStartRecording(String channelName, String uid, String token, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
		    RecordingSnapshotConfig snapshotConfig,
			RecordingStorageConfig storageConfig,
			RecordingExtensionServiceConfig extensionServiceConfig,
			Consumer<CloudRecordingStartResponse> consumer) throws IOException {

		HashMap<String, Object> hashMap = new HashMap<>();
		if(StringUtils.hasText(token)){
			hashMap.put("token", token);
		}

		// 2、应用设置
		if(Objects.isNull(appsCollection)) {
			appsCollection = new RecordingAppsCollectionConfig();
		}
		hashMap.put("appsCollection", appsCollection);
		// 3、媒体流订阅、转码、输出音视频属性的设置
		if(Objects.isNull(recordingConfig)) {
			recordingConfig = DEFAULT_RECORDING_CONFIG;
		}
		hashMap.put("recordingConfig", recordingConfig);
		// 4、录制文件的设置
		if(Objects.isNull(recordingFileConfig)) {
			recordingFileConfig = DEFAULT_RECORDING_FILE_CONFIG;
		}
		hashMap.put("recordingFileConfig", recordingFileConfig);
		// 5、截图周期、截图文件的设置
		if(Objects.nonNull(snapshotConfig)) {
			hashMap.put("snapshotConfig", snapshotConfig);
		}
		// 6、第三方云存储的设置
		hashMap.put("storageConfig", storageConfig);
		// 7、扩展服务的设置，目前包括阿里云视频点播服务和页面录制的设置
		if(Objects.nonNull(extensionServiceConfig)) {
			hashMap.put("extensionServiceConfig", extensionServiceConfig);
		}

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", hashMap)
				.build();

        String reqUrl = AgoraApiAddress.START_CLOUD_RECORDING.getUrl(getAgoraProperties().getAppId(), resourceId, mode.getName());
        super.asyncPost(AgoraApiAddress.START_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingStartResponse.class, consumer);
	}

	/**
     * 3、更新云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#update：更新云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
	 * @param mode 录制模式，支持以下几种录制模式：
	 * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
	 * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
	 * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param streamSubscribe  用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncUpdateMixRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
			RecordingUpdateStreamSubscribe streamSubscribe,
			Consumer<CloudRecordingUpdateResponse> consumer) throws IOException {
		this.asyncUpdateRecording(channelName, uid, resourceId, sid, RecordingMode.MIX, streamSubscribe, null, null, consumer);
	}

	/**
     * 3、更新云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#update：更新云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
	 * @param mode 录制模式，支持以下几种录制模式：
	 * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
	 * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
	 * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param streamSubscribe  用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncUpdateIndividualRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
			RecordingUpdateStreamSubscribe streamSubscribe,
			Consumer<CloudRecordingUpdateResponse> consumer) throws IOException {
        this.asyncUpdateRecording(channelName, uid, resourceId, sid, RecordingMode.INDIVIDUAL, streamSubscribe, null, null, consumer);
	}

	/**
     * 3、更新云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#update：更新云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param streamSubscribe  用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
     * @param webRecordingConfig 用于更新页面录制参数。仅适用于页面录制模式 web
     * @param rtmpPublishConfig  用于更新页面录制并推流到 CDN 的参数。仅适用于页面录制模式 web
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncUpdateRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
		    RecordingUpdateStreamSubscribe streamSubscribe,
			RecordingUpdateWebConfig webRecordingConfig,
		    RecordingUpdateRtmpPublishConfig rtmpPublishConfig,
			Consumer<CloudRecordingUpdateResponse> consumer) throws IOException {

		HashMap<String, Object> hashMap = new HashMap<>();
		// 1、用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
		if(Objects.nonNull(streamSubscribe)) {
			hashMap.put("streamSubscribe", streamSubscribe);
		}
		// 2、用于更新页面录制参数。仅适用于页面录制模式 web
		if(Objects.nonNull(webRecordingConfig)) {
			hashMap.put("webRecordingConfig", webRecordingConfig );
		}
		// 3、用于更新页面录制并推流到 CDN 的参数。仅适用于页面录制模式 web
		if(Objects.nonNull(webRecordingConfig)) {
			hashMap.put("webRecordingConfig", webRecordingConfig);
		}

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", hashMap)
				.build();

        String reqUrl = AgoraApiAddress.UPDATE_CLOUD_RECORDING.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        super.asyncPost(AgoraApiAddress.UPDATE_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingUpdateResponse.class, consumer);
	}

	/**
     * 4、更新合流布局（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#updatelayout：更新合流布局的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param transcodingConfig  用于更新合流布局的参数
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncUpdateLayout(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
			RecordingUpdateTranscodingConfig transcodingConfig,
			Consumer<CloudRecordingUpdateLayoutResponse> consumer) throws IOException {

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", transcodingConfig)
				.build();

        String reqUrl = AgoraApiAddress.UPDATE_CLOUD_RECORDING_LAYOUT.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        super.asyncPost(AgoraApiAddress.UPDATE_CLOUD_RECORDING_LAYOUT, reqUrl, requestBody, CloudRecordingUpdateLayoutResponse.class, consumer);
	}

	/**
     * 5、停止云端录制（异步请求）
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#stop：停止云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param asyncStop 设置 stop 方法是否为异步调用。
     * true：异步。调用 stop 后立即收到响应。
     * false：同步。调用 stop 后，需等待所有录制文件上传至第三方云存储方可收到响应。（默认）
	 * @param consumer 响应处理回调函数
	 * @throws IOException 异常
     */
	public void asyncStopRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode, boolean asyncStop, Consumer<CloudRecordingStopResponse> consumer) throws IOException {

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", ImmutableMap.of("async_stop", asyncStop))
				.build();

		String reqUrl = AgoraApiAddress.STOP_CLOUD_RECORDING.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        this.asyncPost(AgoraApiAddress.STOP_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingStopResponse.class, consumer);
	}

}
