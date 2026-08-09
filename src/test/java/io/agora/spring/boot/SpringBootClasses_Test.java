package io.agora.spring.boot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Spring Boot configuration classes.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class SpringBootClasses_Test {

    // --- AgoraProperties ---
    @Test
    public void testAgoraProperties() {
        AgoraProperties props = new AgoraProperties();
        props.setAppId("test-app-id");
        props.setAppCertificate("test-cert");
        props.setExpirationTimeInSeconds(7200);
        props.setLoginKey("login-key");
        props.setLoginSecret("login-secret");
        props.setOssRegion(7);
        props.setViewWidth(640);
        props.setViewHeight(480);
        assertEquals("test-app-id", props.getAppId());
        assertEquals("test-cert", props.getAppCertificate());
        assertEquals(7200, props.getExpirationTimeInSeconds());
        assertEquals("login-key", props.getLoginKey());
        assertEquals("login-secret", props.getLoginSecret());
        assertEquals(7, props.getOssRegion());
        assertEquals(640, props.getViewWidth());
        assertEquals(480, props.getViewHeight());
        assertEquals("agora", AgoraProperties.PREFIX);
        assertNotNull(props.toString());
    }

    // --- AgoraRecordingProperties ---
    @Test
    public void testAgoraRecordingProperties() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        assertNotNull(props.toString());
    }

    // --- AgoraApiAddress ---
    @Test
    public void testAgoraApiAddressValues() {
        AgoraApiAddress[] values = AgoraApiAddress.values();
        assertTrue(values.length > 0);
        for (AgoraApiAddress addr : values) {
            assertNotNull(addr.getOpt());
            assertNotNull(addr.getMethod());
            assertNotNull(addr.getUrl());
        }
    }

    @Test
    public void testAgoraApiAddressGetUrlWithArgs() {
        String url = AgoraApiAddress.ACQUIRE_RESOURCE_ID.getUrl("test-app-id");
        assertNotNull(url);
        assertTrue(url.contains("test-app-id"));
    }

    @Test
    public void testAgoraApiAddressMethods() {
        assertEquals(RequestMethod.POST, AgoraApiAddress.ACQUIRE_RESOURCE_ID.getMethod());
        assertEquals(RequestMethod.POST, AgoraApiAddress.START_CLOUD_RECORDING.getMethod());
        assertEquals(RequestMethod.GET, AgoraApiAddress.CHANNEL_USER_STATE.getMethod());
        assertEquals(RequestMethod.GET, AgoraApiAddress.CHANNEL_USER_LIST.getMethod());
    }

    // --- AgoraConstant ---
    @Test
    public void testAgoraConstant() {
        assertNotNull(AgoraConstant.URL_CHANNEL_USER);
        assertNotNull(AgoraConstant.URL_RULE);
        assertEquals("10", AgoraConstant.RECORDING_UID);
        assertEquals("video", AgoraConstant.VEIDO_PAHT);
    }

    // --- RequestMethod ---
    @Test
    public void testRequestMethodValues() {
        RequestMethod[] values = RequestMethod.values();
        assertTrue(values.length > 0);
        for (RequestMethod m : values) {
            assertNotNull(m.name());
        }
    }

    // --- AgoraUserIdProvider ---
    @Test
    public void testAgoraUserIdProvider() {
        AgoraUserIdProvider provider = new AgoraUserIdProvider() {};
        assertNotNull(provider);
    }

    // --- AgoraLocalRecordingConfiguration ---
    @Test
    public void testAgoraLocalRecordingConfiguration() {
        AgoraLocalRecordingConfiguration config = new AgoraLocalRecordingConfiguration();
        assertNotNull(config);
    }
}
