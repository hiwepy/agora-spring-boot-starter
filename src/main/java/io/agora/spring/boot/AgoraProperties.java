package io.agora.spring.boot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Agora starter, bound to the {@code agora.*}
 * namespace.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = AgoraProperties.PREFIX)
@Data
public class AgoraProperties {

	/**
	 * Configuration prefix used by Spring Boot to bind properties.
	 */
	public static final String PREFIX = "agora";

	/** Agora application id. */
	private String appId;
	/** Agora application certificate used to sign tokens. */
	private String appCertificate;
	/** Token validity in seconds (default {@code 3600}). */
	private int expirationTimeInSeconds = 3600;
	/** Agora REST API login key (required). */
	private String loginKey;
	/** Agora REST API login secret (required). */
	private String loginSecret;

	/** Recording region selector, e.g. {@code 7} Hong Kong, {@code 10} Singapore. */
	private Integer ossRegion;

	/** Agora video width in pixels. */
	private Integer viewWidth;

	/** Agora video height in pixels. */
	private Integer viewHeight;

}
