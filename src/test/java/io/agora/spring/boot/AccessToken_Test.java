package io.agora.spring.boot;

import io.agora.media.AccessToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for AccessToken generation and parsing.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class AccessToken_Test {
    private String appId = "970CA35de60c44645bbae8a215061b33";
    private String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
    private String channelName = "7d72365eb983485397e3e3f9d460bdda";
    private String uid = "84498484";
    private int ts = 1111111;
    private int salt = 1;
    private int expireTimestamp = 1446455471;

    @Test
    public void testGenerateDynamicKey() throws Exception {
        AccessToken token = new AccessToken(appId, appCertificate, channelName, uid);
        token.message.ts = ts;
        token.message.salt = salt;
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
        String result = token.build();
        assertNotNull(result);
        assertTrue(result.startsWith(AccessToken.getVersion()));
        assertTrue(result.length() > AccessToken.getVersion().length());
    }

    @Test
    public void testAccessTokenWithIntUid() throws Exception {
        AccessToken key = new AccessToken(appId, appCertificate, channelName, uid);
        key.message.salt = salt;
        key.message.ts = ts;
        key.message.messages.put((short)AccessToken.Privileges.kJoinChannel.intValue, expireTimestamp);
        String result = key.build();
        assertNotNull(result);
        assertTrue(result.startsWith(AccessToken.getVersion()));
    }

    @Test
    public void testBuildWithInvalidAppId() throws Exception {
        AccessToken token = new AccessToken("invalid", appCertificate, channelName, uid);
        String result = token.build();
        assertEquals("", result);
    }

    @Test
    public void testBuildWithInvalidAppCertificate() throws Exception {
        AccessToken token = new AccessToken(appId, "invalid", channelName, uid);
        String result = token.build();
        assertEquals("", result);
    }

    @Test
    public void testAddPrivilege() throws Exception {
        AccessToken token = new AccessToken(appId, appCertificate, channelName, uid);
        token.addPrivilege(AccessToken.Privileges.kPublishAudioStream, expireTimestamp);
        token.addPrivilege(AccessToken.Privileges.kPublishVideoStream, expireTimestamp);
        token.addPrivilege(AccessToken.Privileges.kPublishDataStream, expireTimestamp);
        String result = token.build();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    @Test
    public void testGetVersion() {
        assertEquals("006", AccessToken.getVersion());
    }

    @Test
    public void testPrivileges() {
        assertEquals(1, AccessToken.Privileges.kJoinChannel.intValue);
        assertEquals(2, AccessToken.Privileges.kPublishAudioStream.intValue);
        assertEquals(3, AccessToken.Privileges.kPublishVideoStream.intValue);
        assertEquals(4, AccessToken.Privileges.kPublishDataStream.intValue);
        assertEquals(1000, AccessToken.Privileges.kRtmLogin.intValue);
    }
}
