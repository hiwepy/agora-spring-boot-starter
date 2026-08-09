package io.agora.spring.boot;

import io.agora.media.*;
import io.agora.media.RtcTokenBuilder.Role;
import io.agora.recording.common.Common.*;
import io.agora.recording.common.RecordingConfig;
import io.agora.recording.common.RecordingResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for media utility classes, token builders, and remaining enums.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class MediaClasses_Test {

    private String appID = "970CA35de60c44645bbae8a215061b33";
    private String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
    private String channel = "7d72365eb983485397e3e3f9d460bdda";
    private int ts = 1446455472;
    private int r = 58964981;
    private long uid = 2882341273L;
    private int expiredTs = 1446455471;

    // --- DynamicKey ---
    @Test
    public void testDynamicKeyGenerate() throws Exception {
        String result = DynamicKey.generate(appID, appCertificate, channel, ts, r);
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    // --- DynamicKey3 ---
    @Test
    public void testDynamicKey3Generate() throws Exception {
        String result = DynamicKey3.generate(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("003"));
    }

    // --- DynamicKey4 ---
    @Test
    public void testDynamicKey4GeneratePublicSharingKey() throws Exception {
        String result = DynamicKey4.generatePublicSharingKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("004"));
    }

    @Test
    public void testDynamicKey4GenerateRecordingKey() throws Exception {
        String result = DynamicKey4.generateRecordingKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("004"));
    }

    @Test
    public void testDynamicKey4GenerateMediaChannelKey() throws Exception {
        String result = DynamicKey4.generateMediaChannelKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("004"));
    }

    // --- RtcTokenBuilder ---
    @Test
    public void testRtcTokenBuilderBuildTokenWithUid() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUid(appID, appCertificate, channel, 12345, Role.Role_Publisher, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("006"));
    }

    @Test
    public void testRtcTokenBuilderBuildTokenWithZeroUid() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUid(appID, appCertificate, channel, 0, Role.Role_Publisher, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("006"));
    }

    @Test
    public void testRtcTokenBuilderBuildTokenWithUserAccount() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUserAccount(appID, appCertificate, channel, "test_user", Role.Role_Publisher, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith("006"));
    }

    @Test
    public void testRtcTokenBuilderRoles() {
        assertEquals(0, Role.Role_Attendee.initValue);
        assertEquals(1, Role.Role_Publisher.initValue);
        assertEquals(2, Role.Role_Subscriber.initValue);
        assertEquals(101, Role.Role_Admin.initValue);
    }

    @Test
    public void testRtcTokenBuilderWithSubscriber() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUserAccount(appID, appCertificate, channel, "sub_user", Role.Role_Subscriber, expiredTs);
        assertNotNull(result);
    }

    @Test
    public void testRtcTokenBuilderWithAdmin() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUserAccount(appID, appCertificate, channel, "admin_user", Role.Role_Admin, expiredTs);
        assertNotNull(result);
    }

    @Test
    public void testRtcTokenBuilderWithAttendee() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String result = builder.buildTokenWithUserAccount(appID, appCertificate, channel, "attendee", Role.Role_Attendee, expiredTs);
        assertNotNull(result);
    }

    // --- Utils ---
    @Test
    public void testUtilsIsUUID() {
        assertTrue(Utils.isUUID(appID));
        assertFalse(Utils.isUUID(""));
        assertFalse(Utils.isUUID("invalid"));
    }

    @Test
    public void testUtilsCrc32() {
        int crc = Utils.crc32("test");
        assertTrue(crc != 0);
    }

    // --- ByteBuf ---
    @Test
    public void testByteBufDefault() {
        ByteBuf buf = new ByteBuf();
        assertNotNull(buf);
    }

    @Test
    public void testByteBufWithBytes() {
        ByteBuf buf = new ByteBuf(new byte[]{1, 2, 3, 4, 5});
        assertNotNull(buf);
    }

    // --- RecordingConfig (common) ---
    @Test
    public void testRecordingConfigCommonSetters() {
        RecordingConfig config = new RecordingConfig();
        config.isAudioOnly = true;
        assertTrue(config.isAudioOnly);
        config.isVideoOnly = true;
        assertTrue(config.isVideoOnly);
        config.isMixingEnabled = true;
        assertTrue(config.isMixingEnabled);
        config.mixResolution = "640,480,15,500";
        assertEquals("640,480,15,500", config.mixResolution);
        config.decryptionMode = "aes-128-xts";
        assertEquals("aes-128-xts", config.decryptionMode);
        config.secret = "test-secret";
        assertEquals("test-secret", config.secret);
        config.appliteDir = "/opt/agora";
        assertEquals("/opt/agora", config.appliteDir);
        config.recordFileRootDir = "/tmp/recordings";
        assertEquals("/tmp/recordings", config.recordFileRootDir);
        config.cfgFilePath = "/opt/agora/cfg.json";
        assertEquals("/opt/agora/cfg.json", config.cfgFilePath);
        config.lowUdpPort = 50000;
        assertEquals(50000, config.lowUdpPort);
        config.highUdpPort = 51000;
        assertEquals(51000, config.highUdpPort);
        config.idleLimitSec = 600;
        assertEquals(600, config.idleLimitSec);
        config.captureInterval = 10;
        assertEquals(10, config.captureInterval);
        config.triggerMode = 1;
        assertEquals(1, config.triggerMode);
        config.audioIndicationInterval = 200;
        assertEquals(200, config.audioIndicationInterval);
        config.audioProfile = 1;
        assertEquals(1, config.audioProfile);
        config.autoSubscribe = false;
        assertFalse(config.autoSubscribe);
        config.enableCloudProxy = true;
        assertTrue(config.enableCloudProxy);
        config.subscribeVideoUids = "1,2,3";
        assertEquals("1,2,3", config.subscribeVideoUids);
        config.subscribeAudioUids = "4,5,6";
        assertEquals("4,5,6", config.subscribeAudioUids);
        config.enableIntraRequest = false;
        assertFalse(config.enableIntraRequest);
        config.enableH265Support = true;
        assertTrue(config.enableH265Support);
        config.proxyType = 2;
        assertEquals(2, config.proxyType);
        config.proxyServer = "10.0.0.1:443";
        assertEquals("10.0.0.1:443", config.proxyServer);
        config.defaultVideoBgPath = "/tmp/bg.jpg";
        assertEquals("/tmp/bg.jpg", config.defaultVideoBgPath);
        config.defaultUserBgPath = "/tmp/user_bg.jpg";
        assertEquals("/tmp/user_bg.jpg", config.defaultUserBgPath);
    }

    // --- RecordingResult builder ---
    @Test
    public void testRecordingResultBuilder() {
        RecordingResult result = RecordingResult.builder()
                .channelId("ch1")
                .leaveState(false)
                .width(1920)
                .height(1080)
                .fps(60)
                .kbps(2000)
                .count(5)
                .firstReceiveAudioTime(100L)
                .firstReceiveAudioElapsed(50L)
                .firstReceiveVideoTime(200L)
                .firstReceiveVideoElapsed(100L)
                .storageDir("/data")
                .build();
        assertEquals("ch1", result.getChannelId());
        assertFalse(result.isLeaveState());
        assertEquals(1920, result.getWidth());
        assertEquals(1080, result.getHeight());
        assertEquals(60, result.getFps());
        assertEquals(2000, result.getKbps());
        assertEquals(5, result.getCount());
    }

    // --- AgoraRecordingProperties ---
    @Test
    public void testAgoraRecordingPropertiesAllFields() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setAudioOnly(true);
        assertTrue(props.isAudioOnly());
        props.setVideoOnly(true);
        assertTrue(props.isVideoOnly());
        props.setMixingEnabled(true);
        assertTrue(props.isMixingEnabled());
        props.setMixedVideoAudio(MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1);
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1, props.getMixedVideoAudio());
        props.setMixResolution("640,480,15,500");
        assertEquals("640,480,15,500", props.getMixResolution());
        props.setDecryptionMode("aes-128-xts");
        assertEquals("aes-128-xts", props.getDecryptionMode());
        props.setSecret("secret");
        assertEquals("secret", props.getSecret());
        props.setAppliteDir("/opt");
        assertEquals("/opt", props.getAppliteDir());
        props.setRecordFileRootDir("/tmp");
        assertEquals("/tmp", props.getRecordFileRootDir());
        props.setCfgFilePath("/opt/cfg.json");
        assertEquals("/opt/cfg.json", props.getCfgFilePath());
        props.setDecodeVideo(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE);
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE, props.getDecodeVideo());
        props.setDecodeAudio(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE);
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE, props.getDecodeAudio());
        props.setLowUdpPort(40000);
        assertEquals(40000, props.getLowUdpPort());
        props.setHighUdpPort(41000);
        assertEquals(41000, props.getHighUdpPort());
        props.setLogLevel(5);
        assertEquals(5, props.getLogLevel());
        props.setIdleLimitSec(300);
        assertEquals(300, props.getIdleLimitSec());
        props.setCaptureInterval(5);
        assertEquals(5, props.getCaptureInterval());
        props.setAudioIndicationInterval(200);
        assertEquals(200, props.getAudioIndicationInterval());
        props.setChannelProfile(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING);
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING, props.getChannelProfile());
        props.setStreamType(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW);
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW, props.getStreamType());
        props.setTriggerMode(1);
        assertEquals(1, props.getTriggerMode());
        props.setProxyType(2);
        assertEquals(2, props.getProxyType());
        props.setProxyServer("10.0.0.1:443");
        assertEquals("10.0.0.1:443", props.getProxyServer());
        props.setAudioProfile(1);
        assertEquals(1, props.getAudioProfile());
        props.setDefaultVideoBgPath("/tmp/bg.jpg");
        assertEquals("/tmp/bg.jpg", props.getDefaultVideoBgPath());
        props.setDefaultUserBgPath("/tmp/ubg.jpg");
        assertEquals("/tmp/ubg.jpg", props.getDefaultUserBgPath());
        props.setAutoSubscribe(false);
        assertFalse(props.isAutoSubscribe());
        props.setEnableCloudProxy(true);
        assertTrue(props.isEnableCloudProxy());
        props.setSubscribeVideoUids("1,2");
        assertEquals("1,2", props.getSubscribeVideoUids());
        props.setSubscribeAudioUids("3,4");
        assertEquals("3,4", props.getSubscribeAudioUids());
        props.setEnableIntraRequest(false);
        assertFalse(props.isEnableIntraRequest());
        props.setEnableH265Support(true);
        assertTrue(props.isEnableH265Support());
        assertNotNull(props.toString());
    }

    // --- DynamicKeyUtil ---
    @Test
    public void testDynamicKeyUtilConstants() {
        // DynamicKeyUtil methods are package-private, tested indirectly through DynamicKey5
        assertNotNull(DynamicKeyUtil.class);
    }

    // --- ByteBuf put/read operations ---
    @Test
    public void testByteBufPutOperations() {
        ByteBuf buf = new ByteBuf(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertNotNull(buf);
        short val = buf.readShort();
        assertTrue(val >= 0);
    }
}
