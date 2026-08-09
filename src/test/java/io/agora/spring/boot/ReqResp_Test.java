package io.agora.spring.boot;

import io.agora.recording.common.RecordingEngineProperties;
import io.agora.recording.common.RecordingResult;
import io.agora.spring.boot.req.*;
import io.agora.spring.boot.resp.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for request/response POJOs and recording common classes.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ReqResp_Test {

    // --- RecordingResult ---
    @Test
    public void testRecordingResult() {
        RecordingResult result = RecordingResult.builder()
                .channelId("test-channel")
                .leaveState(true)
                .width(640)
                .height(480)
                .fps(30)
                .kbps(500)
                .count(1)
                .firstReceiveAudioTime(1000L)
                .firstReceiveAudioElapsed(100L)
                .firstReceiveVideoTime(2000L)
                .firstReceiveVideoElapsed(200L)
                .storageDir("/tmp")
                .build();
        assertEquals("test-channel", result.getChannelId());
        assertTrue(result.isLeaveState());
        assertEquals(640, result.getWidth());
        assertNotNull(result.toString());
    }

    // --- RecordingEngineProperties ---
    @Test
    public void testRecordingEngineProperties() {
        RecordingEngineProperties props = new RecordingEngineProperties();
        assertNull(props.getStorageDir());
    }

    // --- req POJOs: instantiate and call toString to exercise Lombok ---
    @Test
    public void testAgoraStartParam() {
        AgoraStartParam p = new AgoraStartParam();
        assertNotNull(p.toString());
    }

    @Test
    public void testAgoraStopParam() {
        AgoraStopParam p = new AgoraStopParam();
        assertNotNull(p.toString());
    }

    @Test
    public void testReqRecordingConfig() {
        RecordingConfig c = new RecordingConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testTranscodingConfig() {
        TranscodingConfig c = new TranscodingConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingFileConfig() {
        RecordingFileConfig c = new RecordingFileConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingStorageConfig() {
        RecordingStorageConfig c = new RecordingStorageConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingSnapshotConfig() {
        RecordingSnapshotConfig c = new RecordingSnapshotConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingExtensionServiceConfig() {
        RecordingExtensionServiceConfig c = new RecordingExtensionServiceConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingAppsCollectionConfig() {
        RecordingAppsCollectionConfig c = new RecordingAppsCollectionConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testTranscodingLayoutConfig() {
        TranscodingLayoutConfig c = new TranscodingLayoutConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testTranscodingBackgroundConfig() {
        TranscodingBackgroundConfig c = new TranscodingBackgroundConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingUpdateTranscodingConfig() {
        RecordingUpdateTranscodingConfig c = new RecordingUpdateTranscodingConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingUpdateWebConfig() {
        RecordingUpdateWebConfig c = new RecordingUpdateWebConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingUpdateRtmpPublishConfig() {
        RecordingUpdateRtmpPublishConfig c = new RecordingUpdateRtmpPublishConfig();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingUpdateStreamSubscribe() {
        RecordingUpdateStreamSubscribe c = new RecordingUpdateStreamSubscribe();
        assertNotNull(c.toString());
    }

    @Test
    public void testRecordingModeValues() {
        RecordingMode[] values = RecordingMode.values();
        assertTrue(values.length > 0);
    }

    @Test
    public void testRecordingAppCombinationPolicyValues() {
        RecordingAppCombinationPolicy[] values = RecordingAppCombinationPolicy.values();
        assertTrue(values.length > 0);
    }

    // --- resp POJOs ---
    @Test
    public void testAcquireResourceResponse() {
        AcquireResourceResponse r = new AcquireResourceResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingStartResponse() {
        CloudRecordingStartResponse r = new CloudRecordingStartResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingStopResponse() {
        CloudRecordingStopResponse r = new CloudRecordingStopResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingQueryResponse() {
        CloudRecordingQueryResponse r = new CloudRecordingQueryResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingServiceResponse() {
        CloudRecordingServiceResponse r = new CloudRecordingServiceResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingServiceFile() {
        CloudRecordingServiceFile f = new CloudRecordingServiceFile();
        assertNotNull(f.toString());
    }

    @Test
    public void testCloudRecordingUpdateLayoutResponse() {
        CloudRecordingUpdateLayoutResponse r = new CloudRecordingUpdateLayoutResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingUpdateResponse() {
        CloudRecordingUpdateResponse r = new CloudRecordingUpdateResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testCloudRecordingSubServiceStatus() {
        CloudRecordingSubServiceStatus s = new CloudRecordingSubServiceStatus();
        assertNotNull(s.toString());
    }

    @Test
    public void testCloudRecordingExtensionServiceState() {
        CloudRecordingExtensionServiceState s = new CloudRecordingExtensionServiceState();
        assertNotNull(s.toString());
    }

    @Test
    public void testChannelUserStateResponse() {
        ChannelUserStateResponse r = new ChannelUserStateResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testChannelUserListResponse() {
        ChannelUserListResponse r = new ChannelUserListResponse();
        assertNotNull(r.toString());
    }

    @Test
    public void testAgoraResponse() {
        AgoraResponse r = new AgoraResponse();
        r.setCode(200);
        r.setSuccess(true);
        assertEquals(200, r.getCode());
        assertTrue(r.isSuccess());
        assertNotNull(r.toString());
    }
}
