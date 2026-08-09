package io.agora.spring.boot;

import io.agora.media.AccessToken;
import io.agora.rtm.RtmTokenBuilder;
import io.agora.rtm.RtmTokenBuilder.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for RTM token builder.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class RtmTokenBuilder_Test {
    private String appId = "970CA35de60c44645bbae8a215061b33";
    private String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
    private String userId = "test_user";
    private int expireTimestamp = 1446455471;

    @Test
    public void testRtmTokenBuilderWithDefalutPriviledge() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        String result = builder.buildToken(appId, appCertificate, userId, Role.Rtm_User, expireTimestamp);
        assertNotNull(result);
        assertTrue(result.startsWith(AccessToken.getVersion()));
        assertTrue(result.length() > AccessToken.getVersion().length());

        RtmTokenBuilder tester = new RtmTokenBuilder();
        tester.mTokenCreator = new AccessToken("", "", "", "");
        tester.mTokenCreator.fromString(result);
        assertEquals(builder.mTokenCreator.appId, tester.mTokenCreator.appId);
        assertEquals(builder.mTokenCreator.crcChannelName, tester.mTokenCreator.crcChannelName);
        assertEquals(builder.mTokenCreator.message.salt, tester.mTokenCreator.message.salt);
    }

    @Test
    public void testSetPrivilege() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        builder.buildToken(appId, appCertificate, userId, Role.Rtm_User, expireTimestamp);
        builder.setPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
        assertNotNull(builder.mTokenCreator);
    }

    @Test
    public void testInitTokenBuilder() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        String result = builder.buildToken(appId, appCertificate, userId, Role.Rtm_User, expireTimestamp);

        RtmTokenBuilder newBuilder = new RtmTokenBuilder();
        newBuilder.mTokenCreator = new AccessToken("", "", "", "");
        boolean initResult = newBuilder.initTokenBuilder(result);
        assertTrue(initResult);
        assertNotNull(newBuilder.mTokenCreator);
    }

    @Test
    public void testRole() {
        assertNotNull(Role.Rtm_User);
        assertEquals("Rtm_User", Role.Rtm_User.name());
    }
}
