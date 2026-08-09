package io.agora.spring.boot;

import io.agora.media.DynamicKey5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for DynamicKey5 token generation.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class DynamicKey5_Test {
    private String appID   = "970CA35de60c44645bbae8a215061b33";
    private String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
    private String channel  = "7d72365eb983485397e3e3f9d460bdda";
    private int ts = 1446455472;
    private int r = 58964981;
    private long uid = 2882341273L;
    private int expiredTs=1446455471;

    @Test
    public void testGeneratePublicSharingKey() throws Exception {
        String result = DynamicKey5.generatePublicSharingKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith(DynamicKey5.version));
        assertTrue(result.length() > DynamicKey5.version.length());
    }

    @Test
    public void testGenerateRecordingKey() throws Exception {
        String result = DynamicKey5.generateRecordingKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith(DynamicKey5.version));
        assertTrue(result.length() > DynamicKey5.version.length());
    }

    @Test
    public void testGenerateMediaChannelKey() throws Exception {
        String result = DynamicKey5.generateMediaChannelKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        assertNotNull(result);
        assertTrue(result.startsWith(DynamicKey5.version));
        assertTrue(result.length() > DynamicKey5.version.length());
    }

    @Test
    public void testInChannelPermission() throws Exception {
        String generatedNoUpload = DynamicKey5.generateInChannelPermissionKey(appID, appCertificate, channel, ts, r, uid, expiredTs, DynamicKey5.noUpload);
        assertNotNull(generatedNoUpload);
        assertTrue(generatedNoUpload.startsWith(DynamicKey5.version));

        String generatedAudioVideoUpload = DynamicKey5.generateInChannelPermissionKey(appID, appCertificate, channel, ts, r, uid, expiredTs, DynamicKey5.audioVideoUpload);
        assertNotNull(generatedAudioVideoUpload);
        assertTrue(generatedAudioVideoUpload.startsWith(DynamicKey5.version));

        assertNotEquals(generatedNoUpload, generatedAudioVideoUpload);
    }

    @Test
    public void testFromString() throws Exception {
        String key = DynamicKey5.generateMediaChannelKey(appID, appCertificate, channel, ts, r, uid, expiredTs);
        DynamicKey5 dk5 = new DynamicKey5();
        assertTrue(dk5.fromString(key));
        assertNotNull(dk5.content);
    }

    @Test
    public void testFromStringInvalidVersion() {
        DynamicKey5 dk5 = new DynamicKey5();
        assertFalse(dk5.fromString("000invalid"));
    }
}
