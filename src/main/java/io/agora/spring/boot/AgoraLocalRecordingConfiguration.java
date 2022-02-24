/**
 * Copyright (C) 2020 杭州快定网络股份有限公司 (http://kding.com).
 * All Rights Reserved.
 */
package io.agora.spring.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ AgoraRecordingProperties.class})
public class AgoraLocalRecordingConfiguration {

	/*
	@Bean(destroyMethod = "shutdown")
    public RecordingSDK recordingSdk(AgoraRecordingProperties recordingProperties) {
		return new RecordingSDK(recordingProperties.getLibPath());
	}*/

}
