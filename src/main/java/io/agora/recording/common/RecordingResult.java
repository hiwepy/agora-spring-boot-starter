package io.agora.recording.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordingResult {

    /**
     * 当前对象录制的频道名称
     */
    private String channelId;
    private boolean leaveState;
    private int width = 0;
    private int height = 0;
    private int fps = 0;
    private int kbps = 0;
    private int count = 0;
    private String storageDir;

}
