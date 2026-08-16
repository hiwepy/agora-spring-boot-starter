package io.agora.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the optional local (on-premise) Agora recording
 * integration, bound to the {@code agora.recording.*} namespace. Property fields
 * are inherited from the framework-independent
 * {@link io.agora.cloud.AgoraRecordingProperties} POJO provided by agora-java-sdk.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = AgoraRecordingProperties.PREFIX)
public class AgoraRecordingProperties extends io.agora.cloud.AgoraRecordingProperties {

	/**
	 * Configuration prefix used by Spring Boot to bind properties.
	 */
	public static final String PREFIX = "agora.recording";

}
