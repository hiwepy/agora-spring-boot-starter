package io.agora.spring.boot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Agora starter, bound to the {@code agora.*}
 * namespace. Property fields are inherited from the framework-independent
 * {@link io.agora.cloud.AgoraProperties} POJO provided by agora-java-sdk.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = AgoraProperties.PREFIX)
@Data
@EqualsAndHashCode(callSuper = true)
public class AgoraProperties extends io.agora.cloud.AgoraProperties {

	/**
	 * Configuration prefix used by Spring Boot to bind properties.
	 */
	public static final String PREFIX = "agora";

	/** Enable or disable the Agora starter (default {@code true}). */
	private boolean enabled = true;

}
