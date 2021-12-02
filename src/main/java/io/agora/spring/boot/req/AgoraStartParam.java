package io.agora.spring.boot.req;

import lombok.Data;

/**
 * 开始参数
 */
@Data
public class AgoraStartParam {

    private RecordingConfig recordingConfig;

    private RecordingFileConfig recordingFileConfig;

    private RecordingStorageConfig storageConfig;
}
