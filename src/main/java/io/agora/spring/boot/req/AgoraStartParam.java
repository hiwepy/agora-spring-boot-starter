package io.agora.spring.boot.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Parameters required to start an Agora operation.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
public class AgoraStartParam {

    private RecordingConfig recordingConfig;

    private RecordingFileConfig recordingFileConfig;

    private RecordingStorageConfig storageConfig;
}
