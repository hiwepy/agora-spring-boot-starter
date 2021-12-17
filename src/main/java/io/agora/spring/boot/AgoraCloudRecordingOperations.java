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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import io.agora.spring.boot.req.RecordingAppsCollectionConfig;
import io.agora.spring.boot.req.RecordingConfig;
import io.agora.spring.boot.req.RecordingExtensionServiceConfig;
import io.agora.spring.boot.req.RecordingFileConfig;
import io.agora.spring.boot.req.RecordingMode;
import io.agora.spring.boot.req.RecordingSnapshotConfig;
import io.agora.spring.boot.req.RecordingStorageConfig;
import io.agora.spring.boot.req.RecordingUpdateRtmpPublishConfig;
import io.agora.spring.boot.req.RecordingUpdateStreamSubscribe;
import io.agora.spring.boot.req.RecordingUpdateTranscodingConfig;
import io.agora.spring.boot.req.RecordingUpdateWebConfig;
import io.agora.spring.boot.req.TranscodingConfig;
import io.agora.spring.boot.resp.AcquireResourceResponse;
import io.agora.spring.boot.resp.AcquireResourceResponse.DataBody;
import io.agora.spring.boot.resp.CloudRecordingQueryResponse;
import io.agora.spring.boot.resp.CloudRecordingStartResponse;
import io.agora.spring.boot.resp.CloudRecordingStopResponse;
import io.agora.spring.boot.resp.CloudRecordingUpdateLayoutResponse;
import io.agora.spring.boot.resp.CloudRecordingUpdateResponse;

/**
 * 1、云端录制
 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful
 * https://docs.agora.io/cn/cloud-recording/cloud_recording_individual_mode?platform=RESTful
 */
public class AgoraCloudRecordingOperations extends AgoraOperations {

	protected final static RecordingConfig DEFAULT_RECORDING_CONFIG = new RecordingConfig(new TranscodingConfig());
	protected final static RecordingFileConfig DEFAULT_RECORDING_FILE_CONFIG = new RecordingFileConfig();

	public AgoraCloudRecordingOperations(AgoraTemplate agoraTemplate) {
		super(agoraTemplate);
	}

	/**
	 * 1、获取云端录制资源ID
	 * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#acquire：获取云端录制资源的-api
	 * @param userId 业务用户ID
	 * @param uid 字符串内容为云端录制服务在频道内使用的 UID，用于标识该录制服务，例如"527841"。需满足以下条件：
	 * a、取值范围 1 到 (232-1)，不可设置为 0。
	 * b、不能与当前频道内的任何 UID 重复。
	 * c、云端录制不支持 String 用户 ID（User Account），请确保该字段引号内为整型 UID，且频道内所有用户均使用整型 UID。
	 * @return 操作结果
	 */
	public AcquireResourceResponse acquireId(String userId, String uid) {

		String cnameString = getChannelByUserId(userId);
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", cnameString)
				.put("uid", uid)
				.build();

        String reqUrl = AgoraApiAddress.ACQUIRE_RESOURCE_ID.getUrl(getAgoraProperties().getAppId());
        AcquireResourceResponse resp = super.request(AgoraApiAddress.ACQUIRE_RESOURCE_ID, reqUrl, requestBody, AcquireResourceResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new AcquireResourceResponse.DataBody());
		}
        resp.getData().setCname(cnameString);
        return resp;
	}

	/**
	 * 1、获取云端录制资源ID
	 * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#acquire：获取云端录制资源的-api
	 * @param userId 业务用户ID
	 * @param uid 字符串内容为云端录制服务在频道内使用的 UID，用于标识该录制服务，例如"527841"。需满足以下条件：
	 * a、取值范围 1 到 (232-1)，不可设置为 0。
	 * b、不能与当前频道内的任何 UID 重复。
	 * c、云端录制不支持 String 用户 ID（User Account），请确保该字段引号内为整型 UID，且频道内所有用户均使用整型 UID。
	 * @param region 用于限定云端录制服务访问的区域。一旦指定了访问区域，云端录制服务将不会访问指定区域以外的服务器。区域可设为："CN"（中国区），"AP"（除中国大陆以外的亚洲区域），"EU"（欧洲），"NA"（北美）。
	 * @param expiredHour 单位为小时，用于设置云端录制 RESTful API 的调用时效，从成功开启云端录制并获得 sid （录制 ID）后开始计算。
	 * @param scene 云端录制资源使用场景
	 * 0：（默认）实时音视频录制。设置该场景后，录制服务会将录制文件实时上传至你指定的第三方云存储。
	 * 1：页面录制。
	 * 2：延时转码。设置该场景后，录制服务会在录制后 24 小时内对录制文件进行转码生成 MP4 文件，并将 MP4 文件上传至你指定的第三方云存储（不支持七牛云）。该场景仅适用于单流录制模式。你需要同时在 start 方法中设置 appsCollection 参数
	 * @return 操作结果
	 */
	public AcquireResourceResponse acquireId(String userId, String uid, String region, int expiredHour, int scene) {

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("region", region);
		hashMap.put("resourceExpiredHour", expiredHour);
		hashMap.put("scene", scene);

		String cnameString = getChannelByUserId(userId);
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", cnameString)
				.put("uid", uid)
				.put("clientRequest", hashMap)
				.build();

        String reqUrl = AgoraApiAddress.ACQUIRE_RESOURCE_ID.getUrl(getAgoraProperties().getAppId());
        AcquireResourceResponse resp = super.request(AgoraApiAddress.ACQUIRE_RESOURCE_ID, reqUrl, requestBody, AcquireResourceResponse.class);
        if(Objects.isNull(resp.getData())) {
        	resp.setData(new AcquireResourceResponse.DataBody());
        }
        resp.getData().setCname(cnameString);
        return resp;
	}

	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param storageConfig  第三方云存储的设置
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId, RecordingStorageConfig storageConfig) {
		return this.startRecording(channelName, uid, resourceId, RecordingMode.MIX, null, null, null, null, storageConfig, null);
	}

	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId,
													  RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig) {
		return this.startRecording(channelName, uid, resourceId, RecordingMode.MIX, null, recordingConfig, recordingFileConfig, null, storageConfig, null);
	}

	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId, RecordingMode mode,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig) {
		return this.startRecording(channelName, uid, resourceId, mode, null, recordingConfig, recordingFileConfig, null, storageConfig, null);
	}


	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
     * @param mode 录制模式，支持以下几种录制模式：
     * a、单流模式individual：分开录制频道内每个 UID 的音频流和视频流，每个 UID 均有其对应的音频文件和视频文件。
     * b、合流模式 mix ：（默认模式）频道内所有 UID 的音视频混合录制为一个音视频文件。
     * c、页面录制模式 web：将指定网页的页面内容和音频混合录制为一个音视频文件。
     * @param appsCollection  应用设置
     * @param recordingConfig  媒体流订阅、转码、输出音视频属性的设置
     * @param recordingFileConfig  录制文件的设置
     * @param storageConfig  第三方云存储的设置
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
			RecordingStorageConfig storageConfig) {
		return this.startRecording(channelName, uid, resourceId, mode, appsCollection, recordingConfig, recordingFileConfig, null, storageConfig, null);
	}

	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
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
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
		    RecordingSnapshotConfig snapshotConfig,
			RecordingStorageConfig storageConfig) {
		return this.startRecording(channelName, uid, resourceId, mode, appsCollection, recordingConfig, recordingFileConfig, snapshotConfig, storageConfig, null);
	}

	/**
     * 2、开始云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#start：开始云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
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
     * @return 云端录制操作结果
     */
	public CloudRecordingStartResponse startRecording(String channelName, String uid, String resourceId, RecordingMode mode,
			RecordingAppsCollectionConfig appsCollection,
			RecordingConfig recordingConfig,
		    RecordingFileConfig recordingFileConfig,
		    RecordingSnapshotConfig snapshotConfig,
			RecordingStorageConfig storageConfig,
			RecordingExtensionServiceConfig extensionServiceConfig) {

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("token", "");

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
        CloudRecordingStartResponse resp = super.request(AgoraApiAddress.START_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingStartResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new CloudRecordingStartResponse.DataBody());
		}
        return resp;
	}

	/**
     * 3、更新云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#update：更新云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
     * @param streamSubscribe  用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
     * @return 更新云端录制制操作结果
     */
	public CloudRecordingUpdateResponse updateMixRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode, RecordingUpdateStreamSubscribe streamSubscribe) {
		return this.updateRecording(channelName, uid, resourceId, sid, RecordingMode.MIX, streamSubscribe, null, null);
	}

	/**
     * 3、更新云端录制
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#update：更新云端录制的-api
     * @param channelName  待录制的频道名
     * a、非页面录制模式下，该参数用于设置待录制的频道名
     * b、对于页面录制，该参数用于区分录制进程。字符串长度不得超过 128 字节
     * @param uid  字符串内容为云端录制服务使用的 UID，用于标识该录制服务，需要和你在 acquire 请求中输入的 UID 相同
     * @param resourceId  通过 acquire 请求获取的 resource ID
	 * @param sid 录制 ID。成功开始云端录制后，会得到一个 sid （录制 ID)。该 ID 是一次录制周期的唯一标识
     * @param streamSubscribe  用于更新订阅名单。仅适用于单流录制模式 individual和合流录制模式 mix
     * @return 更新云端录制操作结果
     */
	public CloudRecordingUpdateResponse updateIndividualRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode, RecordingUpdateStreamSubscribe streamSubscribe) {
        return this.updateRecording(channelName, uid, resourceId, sid, RecordingMode.INDIVIDUAL, streamSubscribe, null, null);
	}

	/**
     * 3、更新云端录制
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
     * @return 更新云端录制操作结果
     */
	public CloudRecordingUpdateResponse updateRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
		    RecordingUpdateStreamSubscribe streamSubscribe,
			RecordingUpdateWebConfig webRecordingConfig,
		    RecordingUpdateRtmpPublishConfig rtmpPublishConfig) {

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
        CloudRecordingUpdateResponse resp = super.request(AgoraApiAddress.UPDATE_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingUpdateResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new CloudRecordingUpdateResponse.DataBody());
		}
        return resp;
	}

	/**
     * 4、更新合流布局
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
     * @return 更新合流布局操作结果
     */
	public CloudRecordingUpdateLayoutResponse updateLayout(String channelName, String uid, String resourceId, String sid, RecordingMode mode,
			RecordingUpdateTranscodingConfig transcodingConfig) {

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", transcodingConfig)
				.build();

        String reqUrl = AgoraApiAddress.UPDATE_CLOUD_RECORDING_LAYOUT.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        CloudRecordingUpdateLayoutResponse resp = super.request(AgoraApiAddress.UPDATE_CLOUD_RECORDING_LAYOUT, reqUrl, requestBody, CloudRecordingUpdateLayoutResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new CloudRecordingUpdateLayoutResponse.DataBody());
		}
        return resp;
	}

	/**
     * 5、查询云端录制状态
     * API：https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#query：查询云端录制状态的-api
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
     * @return 云端录制状态结果
     */
	public CloudRecordingQueryResponse queryRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode) {
        String reqUrl = AgoraApiAddress.QUERY_CLOUD_RECORDING.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        CloudRecordingQueryResponse resp = super.request(AgoraApiAddress.QUERY_CLOUD_RECORDING, reqUrl, Maps.newHashMap(), CloudRecordingQueryResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new CloudRecordingQueryResponse.DataBody());
		}
        return resp;
	}

	/**
     * 5、停止云端录制
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
     * @return 停止云端录制操作结果
     */
	public CloudRecordingStopResponse stopRecording(String channelName, String uid, String resourceId, String sid, RecordingMode mode, boolean asyncStop) {

		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("cname", channelName)
				.put("uid", uid)
				.put("clientRequest", ImmutableMap.of("async_stop", asyncStop))
				.build();

		String reqUrl = AgoraApiAddress.STOP_CLOUD_RECORDING.getUrl(getAgoraProperties().getAppId(), resourceId, sid, mode.getName());
        CloudRecordingStopResponse resp = super.request(AgoraApiAddress.STOP_CLOUD_RECORDING, reqUrl, requestBody, CloudRecordingStopResponse.class);
		if(Objects.isNull(resp.getData())) {
			resp.setData(new CloudRecordingStopResponse.DataBody());
		}
        return resp;
	}


}
