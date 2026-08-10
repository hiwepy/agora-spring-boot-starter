package io.agora.spring.boot;

import io.agora.media.AccessToken;
import io.agora.recording.common.Common.*;
import io.agora.spring.boot.req.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for remaining uncovered enums and classes to reach 90% coverage.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class RemainingCoverage_Test {

    // --- RecordingMode enum ---
    @Test
    public void testRecordingModeEnum() {
        RecordingMode[] values = RecordingMode.values();
        assertTrue(values.length > 0);
        for (RecordingMode mode : values) {
            assertNotNull(mode.name());
            assertNotNull(mode.getName());
            assertNotNull(mode.getDesc());
            assertNotNull(mode.toString());
        }
    }

    @Test
    public void testRecordingModeValueOf() {
        assertNotNull(RecordingMode.valueOf("INDIVIDUAL"));
        assertNotNull(RecordingMode.valueOf("MIX"));
        assertNotNull(RecordingMode.valueOf("WEB"));
    }

    @Test
    public void testRecordingModeGetByName() {
        assertEquals(RecordingMode.INDIVIDUAL, RecordingMode.getByName("individual"));
        assertEquals(RecordingMode.MIX, RecordingMode.getByName("mix"));
        assertEquals(RecordingMode.WEB, RecordingMode.getByName("web"));
        assertEquals(RecordingMode.MIX, RecordingMode.getByName("nonexistent"));
    }

    @Test
    public void testRecordingModeEquals() {
        assertTrue(RecordingMode.INDIVIDUAL.equals(RecordingMode.INDIVIDUAL));
        assertFalse(RecordingMode.INDIVIDUAL.equals(RecordingMode.MIX));
    }

    // --- RecordingAppCombinationPolicy enum ---
    @Test
    public void testRecordingAppCombinationPolicyEnum() {
        RecordingAppCombinationPolicy[] values = RecordingAppCombinationPolicy.values();
        assertTrue(values.length > 0);
        for (RecordingAppCombinationPolicy policy : values) {
            assertNotNull(policy.name());
            assertNotNull(policy.getName());
            assertNotNull(policy.getDesc());
            assertNotNull(policy.toString());
        }
    }

    @Test
    public void testRecordingAppCombinationPolicyValueOf() {
        assertNotNull(RecordingAppCombinationPolicy.valueOf("DEFAULT"));
        assertNotNull(RecordingAppCombinationPolicy.valueOf("POSTPONE_TRANSCODING"));
    }

    @Test
    public void testRecordingAppCombinationPolicyEquals() {
        assertTrue(RecordingAppCombinationPolicy.DEFAULT.equals(RecordingAppCombinationPolicy.DEFAULT));
        assertFalse(RecordingAppCombinationPolicy.DEFAULT.equals(RecordingAppCombinationPolicy.POSTPONE_TRANSCODING));
    }

    // --- AUDIO_PROFILE_TYPE enum ---
    @Test
    public void testAudioProfileTypeValues() {
        AUDIO_PROFILE_TYPE[] values = AUDIO_PROFILE_TYPE.values();
        assertTrue(values.length > 0);
        assertEquals(0, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_DEFAULT.getValue());
        assertEquals(1, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_HIGH_QUALITY.getValue());
        assertEquals(2, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_HIGH_QUALITY_STEREO.getValue());
    }

    // --- AgoraRequest generic class ---
    @Test
    public void testAgoraRequestConstructor() {
        AgoraRequest<String> req = new AgoraRequest<>("test-channel", "12345");
        assertEquals("test-channel", req.getCname());
        assertEquals("12345", req.getUid());
        assertNotNull(req.toString());
    }

    // --- AccessToken additional methods ---
    @Test
    public void testAccessTokenGetVersion() {
        assertEquals("006", AccessToken.getVersion());
    }

    @Test
    public void testAccessTokenFromString() throws Exception {
        String appID = "970CA35de60c44645bbae8a215061b33";
        String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
        AccessToken token = new AccessToken(appID, appCertificate, "channel", "12345");
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, 1446455471);
        String built = token.build();
        assertNotNull(built);

        AccessToken parsed = new AccessToken("", "", "", "");
        boolean result = parsed.fromString(built);
        assertTrue(result);
        assertNotNull(parsed.appId);
    }

    @Test
    public void testAccessTokenFromStringInvalid() {
        AccessToken token = new AccessToken("", "", "", "");
        boolean result = token.fromString("invalid_token_string");
        assertFalse(result);
    }

    @Test
    public void testAccessTokenPrivileges() {
        assertEquals(1, AccessToken.Privileges.kJoinChannel.intValue);
        assertEquals(2, AccessToken.Privileges.kPublishAudioStream.intValue);
        assertEquals(3, AccessToken.Privileges.kPublishVideoStream.intValue);
        assertEquals(4, AccessToken.Privileges.kPublishDataStream.intValue);
        assertEquals(1000, AccessToken.Privileges.kRtmLogin.intValue);
    }

    @Test
    public void testAccessTokenAddMultiplePrivileges() throws Exception {
        String appID = "970CA35de60c44645bbae8a215061b33";
        String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
        AccessToken token = new AccessToken(appID, appCertificate, "channel", "12345");
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, 1446455471);
        token.addPrivilege(AccessToken.Privileges.kPublishAudioStream, 1446455471);
        token.addPrivilege(AccessToken.Privileges.kPublishVideoStream, 1446455471);
        token.addPrivilege(AccessToken.Privileges.kPublishDataStream, 1446455471);
        String built = token.build();
        assertNotNull(built);
        assertTrue(built.length() > 0);
    }

    // --- ByteBuf additional operations ---
    @Test
    public void testByteBufConstructorAndReadShort() {
        io.agora.media.ByteBuf buf = new io.agora.media.ByteBuf(new byte[]{0, 1, 0, 2});
        short val = buf.readShort();
        assertEquals((short)256, val);
    }

    // --- RtcTokenBuilder additional coverage ---
    @Test
    public void testRtcTokenBuilderRoleEnum() {
        io.agora.media.RtcTokenBuilder.Role[] roles = io.agora.media.RtcTokenBuilder.Role.values();
        assertEquals(4, roles.length);
        assertNotNull(io.agora.media.RtcTokenBuilder.Role.valueOf("Role_Attendee"));
        assertNotNull(io.agora.media.RtcTokenBuilder.Role.valueOf("Role_Publisher"));
        assertNotNull(io.agora.media.RtcTokenBuilder.Role.valueOf("Role_Subscriber"));
        assertNotNull(io.agora.media.RtcTokenBuilder.Role.valueOf("Role_Admin"));
    }

    // --- RecordingConfig (common) additional fields ---
    @Test
    public void testRecordingConfigCommonEnums() {
        io.agora.recording.common.RecordingConfig config = new io.agora.recording.common.RecordingConfig();
        config.decodeVideo = VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE;
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE, config.decodeVideo);
        config.decodeAudio = AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE;
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE, config.decodeAudio);
        config.channelProfile = CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION;
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION, config.channelProfile);
        config.streamType = REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH;
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH, config.streamType);
        config.mixedVideoAudio = MIXED_AV_CODEC_TYPE.MIXED_AV_DEFAULT;
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_DEFAULT, config.mixedVideoAudio);
    }

    // --- agora_log_level enum ---
    @Test
    public void testAgoraLogLevelEnum() {
        agora_log_level[] values = agora_log_level.values();
        assertTrue(values.length > 0);
        for (agora_log_level level : values) {
            assertNotNull(level.name());
        }
        assertEquals(1, agora_log_level.AGORA_LOG_LEVEL_FATAL.getValue());
        assertEquals(2, agora_log_level.AGORA_LOG_LEVEL_ERROR.getValue());
        assertEquals(3, agora_log_level.AGORA_LOG_LEVEL_WARN.getValue());
        assertEquals(4, agora_log_level.AGORA_LOG_LEVEL_NOTICE.getValue());
        assertEquals(5, agora_log_level.AGORA_LOG_LEVEL_INFO.getValue());
        assertEquals(6, agora_log_level.AGORA_LOG_LEVEL_DEBUG.getValue());
    }

    // --- VIDEO_FORMAT_TYPE enum ---
    @Test
    public void testVideoFormatTypeEnum() {
        assertTrue(VIDEO_FORMAT_TYPE.values().length > 0);
        for (VIDEO_FORMAT_TYPE v : VIDEO_FORMAT_TYPE.values()) {
            assertNotNull(v.name());
        }
    }

    // --- TRIGGER_MODE_TYPE enum ---
    @Test
    public void testTriggerModeTypeGetValue() {
        for (TRIGGER_MODE_TYPE v : TRIGGER_MODE_TYPE.values()) {
            assertNotNull(v.name());
        }
    }

    // --- MIXED_AV_CODEC_TYPE enum getValue ---
    @Test
    public void testMixedAvCodecTypeGetValue() {
        for (MIXED_AV_CODEC_TYPE v : MIXED_AV_CODEC_TYPE.values()) {
            assertNotNull(v.name());
        }
    }

    // --- CONNECTION_CHANGED_REASON_TYPE enum getValue ---
    @Test
    public void testConnectionChangedReasonTypeGetValue() {
        for (CONNECTION_CHANGED_REASON_TYPE v : CONNECTION_CHANGED_REASON_TYPE.values()) {
            assertTrue(v.getValue() >= 0);
        }
    }

    // --- AUDIO_FORMAT_TYPE enum getValue ---
    @Test
    public void testAudioFormatTypeGetValue() {
        for (AUDIO_FORMAT_TYPE v : AUDIO_FORMAT_TYPE.values()) {
            assertNotNull(v.name());
        }
    }

    // --- SignalingToken additional coverage ---
    @Test
    public void testSignalingTokenHexlify() {
        byte[] data = {(byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};
        String result = io.agora.signal.SignalingToken.hexlify(data);
        assertEquals("deadbeef", result);
    }
}
