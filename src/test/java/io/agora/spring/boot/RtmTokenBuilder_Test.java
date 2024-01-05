package io.agora.spring.boot;

import io.agora.media.AccessToken;
import io.agora.rtm.RtmTokenBuilder;
import io.agora.rtm.RtmTokenBuilder.Role;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RtmTokenBuilder_Test {
    private String appId = "";
    private String appCertificate = "";
    private String userId = "test_user";
    private int expireTimestamp = 1446455471;

    @Test
    public void testRtmTokenBuilderWithDefalutPriviledge() throws Exception {
    	RtmTokenBuilder builder = new RtmTokenBuilder();
    	String result = builder.buildToken(appId, appCertificate, userId, Role.Rtm_User, expireTimestamp);

    	RtmTokenBuilder tester = new RtmTokenBuilder();
    	tester.mTokenCreator = new AccessToken("", "", "", "");
    	tester.mTokenCreator.fromString(result);

    	assertEquals(builder.mTokenCreator.appId, tester.mTokenCreator.appId);
    	assertEquals(builder.mTokenCreator.crcChannelName, tester.mTokenCreator.crcChannelName);
    	assertEquals(builder.mTokenCreator.message.salt, tester.mTokenCreator.message.salt);
    }
}
