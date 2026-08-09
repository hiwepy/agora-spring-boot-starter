package io.agora.spring.boot;

import io.agora.signal.SignalingToken;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SignalingToken generation.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class SignalingToken_Test {

    @Test
    public void testSignalingToken() throws NoSuchAlgorithmException {
        String appId = "970CA35de60c44645bbae8a215061b33";
        String certificate = "5cfd2fd1755d40ecb72977518be15d3b";
        String account = "test_account";
        int expiredTsInSeconds = 1446455471;
        String result = SignalingToken.getToken(appId, certificate, account, expiredTsInSeconds);
        assertNotNull(result);
        assertTrue(result.startsWith("1:"));
        assertTrue(result.contains(appId));
        assertTrue(result.contains(String.valueOf(expiredTsInSeconds)));
    }

    @Test
    public void testHexlify() {
        byte[] data = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};
        String result = SignalingToken.hexlify(data);
        assertEquals("cafebabe", result);
    }

    @Test
    public void testHexlifyEmpty() {
        byte[] data = {};
        String result = SignalingToken.hexlify(data);
        assertEquals("", result);
    }
}
