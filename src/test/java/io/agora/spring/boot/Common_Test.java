package io.agora.spring.boot;

import io.agora.recording.common.Common;
import io.agora.recording.common.Common.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for Common inner classes and enums.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class Common_Test {

    // --- ERROR_CODE_TYPE ---
    @Test
    public void testErrorCodeTypeValues() {
        ERROR_CODE_TYPE[] values = ERROR_CODE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(0, ERROR_CODE_TYPE.ERR_OK.getValue());
        assertEquals(1, ERROR_CODE_TYPE.ERR_FAILED.getValue());
        assertEquals(2, ERROR_CODE_TYPE.ERR_INVALID_ARGUMENT.getValue());
        assertEquals(3, ERROR_CODE_TYPE.ERR_INTERNAL_FAILED.getValue());
    }

    @Test
    public void testErrorCodeTypeGetByCode() {
        assertEquals(ERROR_CODE_TYPE.ERR_OK, ERROR_CODE_TYPE.getByCode(0));
        assertEquals(ERROR_CODE_TYPE.ERR_FAILED, ERROR_CODE_TYPE.getByCode(1));
        assertNull(ERROR_CODE_TYPE.getByCode(999));
    }

    // --- STAT_CODE_TYPE ---
    @Test
    public void testStatCodeTypeValues() {
        STAT_CODE_TYPE[] values = STAT_CODE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(0, STAT_CODE_TYPE.STAT_OK.getValue());
        assertEquals(1, STAT_CODE_TYPE.STAT_ERR_FROM_ENGINE.getValue());
        assertEquals(2, STAT_CODE_TYPE.STAT_ERR_ARS_JOIN_CHANNEL.getValue());
        assertEquals(3, STAT_CODE_TYPE.STAT_ERR_CREATE_PROCESS.getValue());
        assertEquals(4, STAT_CODE_TYPE.STAT_ERR_MIXED_INVALID_VIDEO_PARAM.getValue());
        assertEquals(5, STAT_CODE_TYPE.STAT_ERR_NULL_POINTER.getValue());
        assertEquals(6, STAT_CODE_TYPE.STAT_ERR_PROXY_SERVER_INVALID_PARAM.getValue());
        assertEquals(8, STAT_CODE_TYPE.STAT_POLL_ERR.getValue());
        assertEquals(16, STAT_CODE_TYPE.STAT_POLL_HANG_UP.getValue());
        assertEquals(32, STAT_CODE_TYPE.STAT_POLL_NVAL.getValue());
    }

    @Test
    public void testStatCodeTypeGetByCode() {
        assertEquals(STAT_CODE_TYPE.STAT_OK, STAT_CODE_TYPE.getByCode(0));
        assertEquals(STAT_CODE_TYPE.STAT_ERR_FROM_ENGINE, STAT_CODE_TYPE.getByCode(1));
        assertNull(STAT_CODE_TYPE.getByCode(999));
    }

    // --- LEAVE_PATH_CODE ---
    @Test
    public void testLeavePathCodeValues() {
        LEAVE_PATH_CODE[] values = LEAVE_PATH_CODE.values();
        assertTrue(values.length > 0);
        assertEquals(0, LEAVE_PATH_CODE.LEAVE_CODE_INIT.getValue());
        assertEquals(2, LEAVE_PATH_CODE.LEAVE_CODE_SIG.getValue());
        assertEquals(4, LEAVE_PATH_CODE.LEAVE_CODE_NO_USERS.getValue());
        assertEquals(8, LEAVE_PATH_CODE.LEAVE_CODE_TIMER_CATCH.getValue());
        assertEquals(16, LEAVE_PATH_CODE.LEAVE_CODE_CLIENT_LEAVE.getValue());
    }

    @Test
    public void testLeavePathCodeGetByCode() {
        assertEquals(LEAVE_PATH_CODE.LEAVE_CODE_INIT, LEAVE_PATH_CODE.getByCode(0));
        assertEquals(LEAVE_PATH_CODE.LEAVE_CODE_SIG, LEAVE_PATH_CODE.getByCode(2));
        assertNull(LEAVE_PATH_CODE.getByCode(999));
    }

    // --- REMOTE_STREAM_STATE ---
    @Test
    public void testRemoteStreamStateValues() {
        REMOTE_STREAM_STATE[] values = REMOTE_STREAM_STATE.values();
        assertTrue(values.length > 0);
        assertEquals(0, REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING.getValue());
        assertEquals(1, REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_STOPPED.getValue());
    }

    @Test
    public void testRemoteStreamStateGetByCode() {
        assertEquals(REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING, REMOTE_STREAM_STATE.getByCode(0));
        assertEquals(REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_STOPPED, REMOTE_STREAM_STATE.getByCode(1));
        assertNull(REMOTE_STREAM_STATE.getByCode(999));
    }

    // --- REMOTE_STREAM_STATE_CHANGED_REASON ---
    @Test
    public void testRemoteStreamStateChangedReasonValues() {
        REMOTE_STREAM_STATE_CHANGED_REASON[] values = REMOTE_STREAM_STATE_CHANGED_REASON.values();
        assertTrue(values.length > 0);
        assertEquals(0, REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED.getValue());
        assertEquals(1, REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STOPPED.getValue());
    }

    @Test
    public void testRemoteStreamStateChangedReasonGetByCode() {
        assertEquals(REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED, REMOTE_STREAM_STATE_CHANGED_REASON.getByCode(0));
        assertNull(REMOTE_STREAM_STATE_CHANGED_REASON.getByCode(999));
    }

    // --- WARN_CODE_TYPE ---
    @Test
    public void testWarnCodeTypeValues() {
        WARN_CODE_TYPE[] values = WARN_CODE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(103, WARN_CODE_TYPE.WARN_NO_AVAILABLE_CHANNEL.getValue());
        assertEquals(104, WARN_CODE_TYPE.WARN_LOOKUP_CHANNEL_TIMEOUT.getValue());
        assertEquals(105, WARN_CODE_TYPE.WARN_LOOKUP_CHANNEL_REJECTED.getValue());
        assertEquals(106, WARN_CODE_TYPE.WARN_OPEN_CHANNEL_TIMEOUT.getValue());
        assertEquals(107, WARN_CODE_TYPE.WARN_OPEN_CHANNEL_REJECTED.getValue());
        assertEquals(108, WARN_CODE_TYPE.WARN_RECOVERY_CORE_SERVICE_FAILURE.getValue());
    }

    @Test
    public void testWarnCodeTypeGetByCode() {
        assertEquals(WARN_CODE_TYPE.WARN_NO_AVAILABLE_CHANNEL, WARN_CODE_TYPE.getByCode(103));
        assertNull(WARN_CODE_TYPE.getByCode(999));
    }

    // --- CHANNEL_PROFILE_TYPE ---
    @Test
    public void testChannelProfileTypeValues() {
        CHANNEL_PROFILE_TYPE[] values = CHANNEL_PROFILE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(0, CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION.getValue());
        assertEquals(1, CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING.getValue());
    }

    @Test
    public void testChannelProfileTypeGetByCode() {
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION, CHANNEL_PROFILE_TYPE.getByCode(0));
        assertNull(CHANNEL_PROFILE_TYPE.getByCode(999));
    }

    // --- CONNECTION_STATE_TYPE ---
    @Test
    public void testConnectionStateTypeValues() {
        CONNECTION_STATE_TYPE[] values = CONNECTION_STATE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(1, CONNECTION_STATE_TYPE.CONNECTION_STATE_DISCONNECTED.getValue());
        assertEquals(2, CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTING.getValue());
        assertEquals(3, CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTED.getValue());
        assertEquals(4, CONNECTION_STATE_TYPE.CONNECTION_STATE_RECONNECTING.getValue());
        assertEquals(5, CONNECTION_STATE_TYPE.CONNECTION_STATE_FAILED.getValue());
    }

    @Test
    public void testConnectionStateTypeGetByCode() {
        assertEquals(CONNECTION_STATE_TYPE.CONNECTION_STATE_DISCONNECTED, CONNECTION_STATE_TYPE.getByCode(1));
        assertNull(CONNECTION_STATE_TYPE.getByCode(999));
    }

    // --- CONNECTION_CHANGED_REASON_TYPE ---
    @Test
    public void testConnectionChangedReasonTypeValues() {
        CONNECTION_CHANGED_REASON_TYPE[] values = CONNECTION_CHANGED_REASON_TYPE.values();
        assertTrue(values.length > 0);
        for (CONNECTION_CHANGED_REASON_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- USER_OFFLINE_REASON_TYPE ---
    @Test
    public void testUserOfflineReasonTypeValues() {
        USER_OFFLINE_REASON_TYPE[] values = USER_OFFLINE_REASON_TYPE.values();
        assertTrue(values.length > 0);
    }

    // --- REMOTE_VIDEO_STREAM_TYPE ---
    @Test
    public void testRemoteVideoStreamTypeValues() {
        REMOTE_VIDEO_STREAM_TYPE[] values = REMOTE_VIDEO_STREAM_TYPE.values();
        assertTrue(values.length > 0);
        for (REMOTE_VIDEO_STREAM_TYPE v : values) {
            assertNotNull(v.name());
            assertTrue(v.getValue() >= 0);
        }
    }

    // --- SERVICE_MODE ---
    @Test
    public void testServiceModeValues() {
        SERVICE_MODE[] values = SERVICE_MODE.values();
        assertTrue(values.length > 0);
    }

    // --- agora_log_level ---
    @Test
    public void testAgoraLogLevelValues() {
        agora_log_level[] values = agora_log_level.values();
        assertTrue(values.length > 0);
        for (agora_log_level v : values) {
            assertNotNull(v.name());
        }
    }

    // --- VIDEO_FORMAT_TYPE ---
    @Test
    public void testVideoFormatTypeValues() {
        VIDEO_FORMAT_TYPE[] values = VIDEO_FORMAT_TYPE.values();
        assertTrue(values.length > 0);
        for (VIDEO_FORMAT_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- AUDIO_FORMAT_TYPE ---
    @Test
    public void testAudioFormatTypeValues() {
        AUDIO_FORMAT_TYPE[] values = AUDIO_FORMAT_TYPE.values();
        assertTrue(values.length > 0);
        for (AUDIO_FORMAT_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- AUDIO_FRAME_TYPE ---
    @Test
    public void testAudioFrameTypeValues() {
        AUDIO_FRAME_TYPE[] values = AUDIO_FRAME_TYPE.values();
        assertTrue(values.length > 0);
        for (AUDIO_FRAME_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- MIXED_AV_CODEC_TYPE ---
    @Test
    public void testMixedAvCodecTypeValues() {
        MIXED_AV_CODEC_TYPE[] values = MIXED_AV_CODEC_TYPE.values();
        assertTrue(values.length > 0);
        for (MIXED_AV_CODEC_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- TRIGGER_MODE_TYPE ---
    @Test
    public void testTriggerModeTypeValues() {
        TRIGGER_MODE_TYPE[] values = TRIGGER_MODE_TYPE.values();
        assertTrue(values.length > 0);
        for (TRIGGER_MODE_TYPE v : values) {
            assertNotNull(v.name());
        }
    }

    // --- VIDEO_FRAME_TYPE (class, not enum) ---
    @Test
    public void testVideoFrameTypeValues() {
        Common common = new Common();
        VIDEO_FRAME_TYPE vft = common.new VIDEO_FRAME_TYPE();
        assertEquals(0, vft.VIDEO_FRAME_RAW_YUV);
        assertEquals(1, vft.VIDEO_FRAME_H264);
        assertEquals(2, vft.VIDEO_FRAME_JPG);
        assertEquals(3, vft.VIDEO_FRAME_H265);
        assertEquals(4, vft.VIDEO_JPG_FILE);
        assertEquals(0, vft.type);
        assertEquals(0, vft.getValue());
    }

    // --- Inner data classes (public constructors) ---
    @Test
    public void testVideoMixingLayout() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        layout.canvasWidth = 640;
        layout.canvasHeight = 480;
        layout.backgroundColor = "#C0C0C0";
        layout.regionCount = 2;
        layout.appData = "test";
        layout.appDataLength = 4;
        layout.keepLastFrame = 1;
        assertEquals(640, layout.canvasWidth);
        assertEquals(480, layout.canvasHeight);
        assertEquals("#C0C0C0", layout.backgroundColor);
        assertEquals(2, layout.regionCount);
        assertEquals("test", layout.appData);
        assertEquals(4, layout.appDataLength);
        assertEquals(1, layout.keepLastFrame);
    }

    @Test
    public void testVideoMixingLayoutRegion() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        VideoMixingLayout.Region region = layout.new Region();
        region.uid = 12345L;
        region.x = 0.1;
        region.y = 0.2;
        region.width = 0.5;
        region.height = 0.5;
        region.alpha = 0.8;
        region.renderMode = 1;
        assertEquals(12345L, region.uid);
        assertEquals(0.1, region.x, 0.001);
        assertEquals(0.2, region.y, 0.001);
        assertEquals(0.5, region.width, 0.001);
        assertEquals(0.5, region.height, 0.001);
        assertEquals(0.8, region.alpha, 0.001);
        assertEquals(1, region.renderMode);
    }

    @Test
    public void testVideoMixingLayoutWithRegions() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        VideoMixingLayout.Region[] regions = new VideoMixingLayout.Region[2];
        regions[0] = layout.new Region();
        regions[0].uid = 1;
        regions[1] = layout.new Region();
        regions[1].uid = 2;
        layout.regions = regions;
        layout.regionCount = 2;
        assertNotNull(layout.regions);
        assertEquals(2, layout.regions.length);
        assertEquals(1, layout.regions[0].uid);
        assertEquals(2, layout.regions[1].uid);
    }

    @Test
    public void testVideoMixingLayoutWithWatermarks() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        layout.literalWms = new LiteraWatermarkConfig[]{common.new LiteraWatermarkConfig()};
        layout.timestampWms = new TimestampWatermarkConfig[]{common.new TimestampWatermarkConfig()};
        layout.imageWms = new ImageWatermarkConfig[]{common.new ImageWatermarkConfig()};
        assertNotNull(layout.literalWms);
        assertNotNull(layout.timestampWms);
        assertNotNull(layout.imageWms);
    }

    @Test
    public void testRemoteVideoStats() {
        Common common = new Common();
        RemoteVideoStats stats = common.new RemoteVideoStats();
        stats.delay = 100;
        stats.width = 640;
        stats.height = 480;
        stats.receivedBitrate = 500;
        stats.decoderOutputFrameRate = 30;
        stats.rxStreamType = 0;
        assertEquals(100, stats.delay);
        assertEquals(640, stats.width);
        assertEquals(480, stats.height);
        assertEquals(500, stats.receivedBitrate);
        assertEquals(30, stats.decoderOutputFrameRate);
        assertEquals(0, stats.rxStreamType);
    }

    @Test
    public void testRemoteAudioStats() {
        Common common = new Common();
        RemoteAudioStats stats = common.new RemoteAudioStats();
        stats.quality = 1;
        stats.networkTransportDelay = 50;
        stats.jitterBufferDelay = 30;
        stats.audioLossRate = 2;
        assertEquals(1, stats.quality);
        assertEquals(50, stats.networkTransportDelay);
        assertEquals(30, stats.jitterBufferDelay);
        assertEquals(2, stats.audioLossRate);
    }

    @Test
    public void testRecordingStats() {
        Common common = new Common();
        RecordingStats stats = common.new RecordingStats();
        stats.duration = 120;
        stats.rxBytes = 1024;
        stats.rxKBitRate = 500;
        stats.rxAudioKBitRate = 64;
        stats.rxVideoKBitRate = 436;
        stats.lastmileDelay = 100;
        stats.userCount = 3;
        stats.cpuAppUsage = 10;
        stats.cpuTotalUsage = 30;
        assertEquals(120, stats.duration);
        assertEquals(1024, stats.rxBytes);
        assertEquals(500, stats.rxKBitRate);
        assertEquals(64, stats.rxAudioKBitRate);
        assertEquals(436, stats.rxVideoKBitRate);
        assertEquals(100, stats.lastmileDelay);
        assertEquals(3, stats.userCount);
        assertEquals(10, stats.cpuAppUsage);
        assertEquals(30, stats.cpuTotalUsage);
    }

    @Test
    public void testAudioVolumeInfo() {
        Common common = new Common();
        AudioVolumeInfo info = common.new AudioVolumeInfo();
        info.uid = 1000L;
        info.volume = 200;
        assertEquals(1000L, info.uid);
        assertEquals(200, info.volume);
    }

    @Test
    public void testLiteraWatermarkConfig() {
        Common common = new Common();
        LiteraWatermarkConfig config = common.new LiteraWatermarkConfig();
        config.wmLitera = "test watermark";
        config.fontFilePath = "/path/to/font.ttf";
        config.fontSize = 14;
        config.offsetX = 10;
        config.offsetY = 20;
        config.wmWidth = 200;
        config.wmHeight = 50;
        assertEquals("test watermark", config.wmLitera);
        assertEquals("/path/to/font.ttf", config.fontFilePath);
        assertEquals(14, config.fontSize);
        assertEquals(10, config.offsetX);
        assertEquals(20, config.offsetY);
        assertEquals(200, config.wmWidth);
        assertEquals(50, config.wmHeight);
    }

    @Test
    public void testTimestampWatermarkConfig() {
        Common common = new Common();
        TimestampWatermarkConfig config = common.new TimestampWatermarkConfig();
        config.fontSize = 12;
        config.offsetX = 5;
        config.offsetY = 10;
        config.wmWidth = 150;
        config.wmHeight = 30;
        assertEquals(12, config.fontSize);
        assertEquals(5, config.offsetX);
        assertEquals(10, config.offsetY);
        assertEquals(150, config.wmWidth);
        assertEquals(30, config.wmHeight);
    }

    @Test
    public void testImageWatermarkConfig() {
        Common common = new Common();
        ImageWatermarkConfig config = common.new ImageWatermarkConfig();
        config.imagePath = "/path/to/image.png";
        config.offsetX = 5;
        config.offsetY = 10;
        config.wmWidth = 100;
        config.wmHeight = 100;
        assertEquals("/path/to/image.png", config.imagePath);
        assertEquals(5, config.offsetX);
        assertEquals(10, config.offsetY);
        assertEquals(100, config.wmWidth);
        assertEquals(100, config.wmHeight);
    }

    @Test
    public void testVideoFrame() {
        Common common = new Common();
        VideoFrame frame = common.new VideoFrame();
        assertNotNull(frame);
        frame.rotation = 90;
        assertEquals(90, frame.rotation);
    }

    @Test
    public void testAudioFrame() {
        Common common = new Common();
        AudioFrame frame = common.new AudioFrame();
        assertNotNull(frame);
    }

    @Test
    public void testAudioPcmFrame() {
        Common common = new Common();
        AudioPcmFrame frame = common.new AudioPcmFrame(1000, 48000, 480);
        frame.pcmBuf = new byte[]{1, 2, 3};
        frame.pcmBufSize = 3;
        frame.channels = 1;
        frame.sample_bits = 16;
        frame.sample_rates = 48000;
        frame.samples = 480;
        frame.frame_ms = 1000;
        assertArrayEquals(new byte[]{1, 2, 3}, frame.pcmBuf);
        assertEquals(3, frame.pcmBufSize);
        assertEquals(1, frame.channels);
        assertEquals(16, frame.sample_bits);
        assertEquals(48000, frame.sample_rates);
        assertEquals(480, frame.samples);
        assertEquals(1000, frame.frame_ms);
    }

    @Test
    public void testAudioAacFrame() {
        Common common = new Common();
        AudioAacFrame frame = common.new AudioAacFrame(1000);
        frame.aacBuf = new byte[]{1, 2, 3};
        frame.aacBufSize = 3;
        frame.channels = 1;
        frame.bitrate = 64000;
        frame.frame_ms = 1000;
        assertArrayEquals(new byte[]{1, 2, 3}, frame.aacBuf);
        assertEquals(3, frame.aacBufSize);
        assertEquals(1, frame.channels);
        assertEquals(64000, frame.bitrate);
        assertEquals(1000, frame.frame_ms);
    }

    // --- Common class constructor ---
    @Test
    public void testCommonConstructor() {
        Common common = new Common();
        assertNotNull(common);
    }
}
