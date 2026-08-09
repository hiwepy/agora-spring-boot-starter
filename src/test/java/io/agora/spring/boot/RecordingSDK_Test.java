package io.agora.spring.boot;

import io.agora.recording.RecordingSDK;
import io.agora.recording.RecordingEventHandler;
import io.agora.recording.common.Common.*;
import io.agora.recording.common.RecordingConfig;
import io.agora.recording.common.RecordingEngineProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for RecordingSDK non-native methods.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class RecordingSDK_Test {

    private RecordingSDK sdk;

    @BeforeEach
    public void setup() {
        // This will fail to load native library gracefully
        sdk = new RecordingSDK("nonexistent_path/");
    }

    @Test
    public void testConstructorWithNull() {
        RecordingSDK s = new RecordingSDK(null);
        assertNotNull(s);
    }

    @Test
    public void testConstructorWithPath() {
        RecordingSDK s = new RecordingSDK("/nonexistent/");
        assertNotNull(s);
    }

    @Test
    public void testRegisterAndUnregisterObserver() {
        RecordingEventHandler handler = new RecordingEventHandler() {
            @Override public String getChannel() { return "test-channel"; }
            @Override public void onLeaveChannel(int reason) {}
            @Override public void onError(int error, int stat_code) {}
            @Override public void onWarning(int warn) {}
            @Override public void onJoinChannelSuccess(String channelId, long uid) {}
            @Override public void onRejoinChannelSuccess(String channelId, long uid) {}
            @Override public void onConnectionStateChanged(CONNECTION_STATE_TYPE state, CONNECTION_CHANGED_REASON_TYPE reason) {}
            @Override public void onRemoteAudioStats(long uid, RemoteAudioStats stats) {}
            @Override public void onRemoteVideoStats(long uid, RemoteVideoStats stats) {}
            @Override public void onRecordingStats(RecordingStats stats) {}
            @Override public void onUserOffline(long uid, int reason) {}
            @Override public void onUserJoined(long uid, String recordingDir) {}
            @Override public void onLocalUserRegistered(long uid, String userAccount) {}
            @Override public void onUserInfoUpdated(long uid, String userAccount) {}
            @Override public void onRemoteVideoStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onRemoteAudioStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void audioFrameReceived(long uid, AudioFrame frame) {}
            @Override public void videoFrameReceived(long uid, int type, VideoFrame frame, int rotation) {}
            @Override public void onActiveSpeaker(long uid) {}
            @Override public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {}
            @Override public void onConnectionLost() {}
            @Override public void onConnectionInterrupted() {}
            @Override public void onAudioVolumeIndication(AudioVolumeInfo[] infos) {}
            @Override public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {}
            @Override public void onFirstRemoteAudioFrame(long uid, int elapsed) {}
            @Override public void recordingPathCallBack(String path) {}
        };

        sdk.registerOberserver(handler);
        assertEquals(handler, sdk.getRegisterOberserver("test-channel"));
        sdk.unRegisterOberserver(handler);
        assertNull(sdk.getRegisterOberserver("test-channel"));
    }

    @Test
    public void testRegisterObserverOverwrite() {
        RecordingEventHandler handler1 = new RecordingEventHandler() {
            @Override public String getChannel() { return "ch1"; }
            @Override public void onLeaveChannel(int reason) {}
            @Override public void onError(int error, int stat_code) {}
            @Override public void onWarning(int warn) {}
            @Override public void onJoinChannelSuccess(String channelId, long uid) {}
            @Override public void onRejoinChannelSuccess(String channelId, long uid) {}
            @Override public void onConnectionStateChanged(CONNECTION_STATE_TYPE state, CONNECTION_CHANGED_REASON_TYPE reason) {}
            @Override public void onRemoteAudioStats(long uid, RemoteAudioStats stats) {}
            @Override public void onRemoteVideoStats(long uid, RemoteVideoStats stats) {}
            @Override public void onRecordingStats(RecordingStats stats) {}
            @Override public void onUserOffline(long uid, int reason) {}
            @Override public void onUserJoined(long uid, String recordingDir) {}
            @Override public void onLocalUserRegistered(long uid, String userAccount) {}
            @Override public void onUserInfoUpdated(long uid, String userAccount) {}
            @Override public void onRemoteVideoStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onRemoteAudioStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void audioFrameReceived(long uid, AudioFrame frame) {}
            @Override public void videoFrameReceived(long uid, int type, VideoFrame frame, int rotation) {}
            @Override public void onActiveSpeaker(long uid) {}
            @Override public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {}
            @Override public void onConnectionLost() {}
            @Override public void onConnectionInterrupted() {}
            @Override public void onAudioVolumeIndication(AudioVolumeInfo[] infos) {}
            @Override public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {}
            @Override public void onFirstRemoteAudioFrame(long uid, int elapsed) {}
            @Override public void recordingPathCallBack(String path) {}
        };
        RecordingEventHandler handler2 = new RecordingEventHandler() {
            @Override public String getChannel() { return "ch1"; }
            @Override public void onLeaveChannel(int reason) {}
            @Override public void onError(int error, int stat_code) {}
            @Override public void onWarning(int warn) {}
            @Override public void onJoinChannelSuccess(String channelId, long uid) {}
            @Override public void onRejoinChannelSuccess(String channelId, long uid) {}
            @Override public void onConnectionStateChanged(CONNECTION_STATE_TYPE state, CONNECTION_CHANGED_REASON_TYPE reason) {}
            @Override public void onRemoteAudioStats(long uid, RemoteAudioStats stats) {}
            @Override public void onRemoteVideoStats(long uid, RemoteVideoStats stats) {}
            @Override public void onRecordingStats(RecordingStats stats) {}
            @Override public void onUserOffline(long uid, int reason) {}
            @Override public void onUserJoined(long uid, String recordingDir) {}
            @Override public void onLocalUserRegistered(long uid, String userAccount) {}
            @Override public void onUserInfoUpdated(long uid, String userAccount) {}
            @Override public void onRemoteVideoStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onRemoteAudioStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void audioFrameReceived(long uid, AudioFrame frame) {}
            @Override public void videoFrameReceived(long uid, int type, VideoFrame frame, int rotation) {}
            @Override public void onActiveSpeaker(long uid) {}
            @Override public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {}
            @Override public void onConnectionLost() {}
            @Override public void onConnectionInterrupted() {}
            @Override public void onAudioVolumeIndication(AudioVolumeInfo[] infos) {}
            @Override public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {}
            @Override public void onFirstRemoteAudioFrame(long uid, int elapsed) {}
            @Override public void recordingPathCallBack(String path) {}
        };

        sdk.registerOberserver(handler1);
        sdk.registerOberserver(handler2);
        assertEquals(handler2, sdk.getRegisterOberserver("ch1"));
    }

    @Test
    public void testUnregisterByChannelName() {
        RecordingEventHandler handler = new RecordingEventHandler() {
            @Override public String getChannel() { return "ch2"; }
            @Override public void onLeaveChannel(int reason) {}
            @Override public void onError(int error, int stat_code) {}
            @Override public void onWarning(int warn) {}
            @Override public void onJoinChannelSuccess(String channelId, long uid) {}
            @Override public void onRejoinChannelSuccess(String channelId, long uid) {}
            @Override public void onConnectionStateChanged(CONNECTION_STATE_TYPE state, CONNECTION_CHANGED_REASON_TYPE reason) {}
            @Override public void onRemoteAudioStats(long uid, RemoteAudioStats stats) {}
            @Override public void onRemoteVideoStats(long uid, RemoteVideoStats stats) {}
            @Override public void onRecordingStats(RecordingStats stats) {}
            @Override public void onUserOffline(long uid, int reason) {}
            @Override public void onUserJoined(long uid, String recordingDir) {}
            @Override public void onLocalUserRegistered(long uid, String userAccount) {}
            @Override public void onUserInfoUpdated(long uid, String userAccount) {}
            @Override public void onRemoteVideoStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onRemoteAudioStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void audioFrameReceived(long uid, AudioFrame frame) {}
            @Override public void videoFrameReceived(long uid, int type, VideoFrame frame, int rotation) {}
            @Override public void onActiveSpeaker(long uid) {}
            @Override public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {}
            @Override public void onConnectionLost() {}
            @Override public void onConnectionInterrupted() {}
            @Override public void onAudioVolumeIndication(AudioVolumeInfo[] infos) {}
            @Override public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {}
            @Override public void onFirstRemoteAudioFrame(long uid, int elapsed) {}
            @Override public void recordingPathCallBack(String path) {}
        };

        sdk.registerOberserver(handler);
        sdk.unRegisterOberserver("ch2");
        assertNull(sdk.getRegisterOberserver("ch2"));
    }

    @Test
    public void testGetRegisterObserverNotFound() {
        assertNull(sdk.getRegisterOberserver("nonexistent"));
    }

    @Test
    public void testLeaveChannelWithZeroHandle() {
        assertFalse(sdk.leaveChannel());
    }

    @Test
    public void testSetVideoMixingLayoutWithZeroHandle() {
        io.agora.recording.common.Common common = new io.agora.recording.common.Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        assertEquals(-1, sdk.setVideoMixingLayout(layout));
    }

    @Test
    public void testUpdateWatermarkConfigsWithZeroHandle() {
        assertEquals(-1, sdk.updateWatermarkConfigs(null, null, null));
    }

    @Test
    public void testUpdateSubscribeVideoUidsWithZeroHandle() {
        assertEquals(-1, sdk.updateSubscribeVideoUids(new int[]{1, 2}));
    }

    @Test
    public void testUpdateSubscribeAudioUidsWithZeroHandle() {
        assertEquals(-1, sdk.updateSubscribeAudioUids(new int[]{1, 2}));
    }

    @Test
    public void testStartServiceWithZeroHandle() {
        assertEquals(-1, sdk.startService());
    }

    @Test
    public void testStopServiceWithZeroHandle() {
        assertEquals(-1, sdk.stopService());
    }

    @Test
    public void testGetPropertiesWithZeroHandle() {
        assertNull(sdk.getProperties());
    }

    @Test
    public void testSetUserBackgroundWithZeroHandle() {
        assertEquals(-1, sdk.setUserBackground(123, "bg.jpg"));
    }

    @Test
    public void testSetLogLevelWithZeroHandle() {
        sdk.setLogLevel(5); // should not throw
    }

    @Test
    public void testGetUidByUserAccountWithZeroHandle() {
        assertEquals(0, sdk.getUidByUserAccount("test"));
    }

    @Test
    public void testGetUserAccountByUidWithZeroHandle() {
        assertEquals("", sdk.getUserAccountByUid(123));
    }

    @Test
    public void testShutdown() {
        sdk.shutdown(); // should not throw
    }

    @Test
    public void testMaxUserAccountLength() {
        assertEquals(256, RecordingSDK.MAX_USER_ACCOUNT_LENGTH);
    }
}
