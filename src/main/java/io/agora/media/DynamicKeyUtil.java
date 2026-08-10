package io.agora.media;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Utility helpers shared by the dynamic key generators.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DynamicKeyUtil {

    static byte[] encodeHMAC(String key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        return encodeHMAC(key.getBytes(), message);
    }

    static byte[] encodeHMAC(byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(keySpec);
        return mac.doFinal(message);
    }

    static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
