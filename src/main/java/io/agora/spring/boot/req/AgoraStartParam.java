package io.agora.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 开始参数
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
public class AgoraStartParam {

    private RecordingConfig recordingConfig;

    private RecordingFileConfig recordingFileConfig;

    private RecordingStorageConfig storageConfig;
}
