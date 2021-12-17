package io.agora.spring.boot;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.agora.spring.boot.req.RecordingConfig;
import io.agora.spring.boot.req.RecordingFileConfig;
import io.agora.spring.boot.req.RecordingStorageConfig;
import io.agora.spring.boot.resp.AcquireResourceResponse;
import okhttp3.OkHttpClient;

public class AgoraTemplate_Test {

	ObjectMapper objectMapper = new ObjectMapper();
	OkHttpClient okhttp3Client = new OkHttpClient.Builder().build();
	AgoraProperties properties = new AgoraProperties();
	AgoraTemplate template;

	@Before
	public void setup() {
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		properties.setAppId("");
		properties.setAppCertificate("");
		properties.setLoginKey("");
		properties.setLoginSecret("");
		template = new AgoraTemplate(properties, objectMapper, okhttp3Client, new AgoraUserIdProvider() {});

	}

	@Test
	public void testAcquireId() throws Exception {

		AcquireResourceResponse response =  template.opsForCloudRecording().acquireId("10000", "121212");
		System.out.println(objectMapper.writeValueAsString(response));

	}

	@Test
    public void testStartRecording() throws Exception {

		String token = template.generateToken("10000", "121212");

		AcquireResourceResponse response =  template.opsForCloudRecording().acquireId("10000", "121212");
		System.out.println(objectMapper.writeValueAsString(response));

		String channelName = response.getCname();
		String resourceId = response.getResourceId();
		String uid = "121212";

		RecordingConfig recordingConfig = new RecordingConfig();
		RecordingFileConfig recordingFileConfig = new RecordingFileConfig();

		 // 阿里云存储桶
        RecordingStorageConfig storageConfig = new RecordingStorageConfig();
        storageConfig.setVendor(2); // 2代表产商为阿里云
        storageConfig.setRegion(7);
        storageConfig.setAccessKey("");
        storageConfig.setSecretKey("");
        storageConfig.setBucket("");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        storageConfig.setFileNamePrefix(Arrays.asList(AgoraConstant.VEIDO_PAHT, formatter.format(new Date())));

    	template.opsForCloudRecording().startRecording(channelName, uid, token, resourceId, recordingConfig, recordingFileConfig, storageConfig);

    }

}
