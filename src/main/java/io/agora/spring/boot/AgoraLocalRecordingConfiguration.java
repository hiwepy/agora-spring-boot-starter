/**
 * Copyright (C) 2020 杭州快定网络股份有限公司 (http://kding.com).
 * All Rights Reserved.
 */
package io.agora.spring.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Optional auto-configuration for the local (on-premise) Agora recording SDK.
 * <p>Activates the {@link io.agora.recording.RecordingSDK} when the local
 * recording integration is required. The bean wiring is currently commented
 * out and left as an extension point.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({ AgoraRecordingProperties.class })
public class AgoraLocalRecordingConfiguration {

	/*
	@Bean(destroyMethod = "shutdown")
    public RecordingSDK recordingSdk(AgoraRecordingProperties recordingProperties) {
		return new RecordingSDK(recordingProperties.getLibPath());
	}*/

}
