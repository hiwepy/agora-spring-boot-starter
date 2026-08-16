package io.agora.spring.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agora.cloud.AgoraOkHttp3Template;
import io.agora.cloud.AgoraTemplate;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AgoraAutoConfiguration} and
 * {@link AgoraLocalRecordingConfiguration}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class AgoraAutoConfigurationTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withUserConfiguration(AgoraAutoConfiguration.class);

	private final ApplicationContextRunner recordingContextRunner = new ApplicationContextRunner()
			.withUserConfiguration(AgoraLocalRecordingConfiguration.class);

	@Test
	public void shouldRegisterAgoraBeansWithDefaults() {
		contextRunner.run(context -> {
			assertThat(context).hasSingleBean(AgoraProperties.class);
			assertThat(context).hasSingleBean(AgoraOkHttp3Template.class);
			assertThat(context).hasSingleBean(AgoraTemplate.class);
			AgoraProperties properties = context.getBean(AgoraProperties.class);
			assertThat(properties.isEnabled()).isTrue();
			assertThat(properties.getExpirationTimeInSeconds()).isEqualTo(3600);
		});
	}

	@Test
	public void shouldBindAgoraProperties() {
		contextRunner.withPropertyValues(
				"agora.app-id=test-app-id",
				"agora.app-certificate=test-certificate",
				"agora.login-key=test-login-key",
				"agora.login-secret=test-login-secret",
				"agora.expiration-time-in-seconds=7200",
				"agora.oss-region=7",
				"agora.view-width=640",
				"agora.view-height=480").run(context -> {
			assertThat(context).hasSingleBean(AgoraTemplate.class);
			AgoraTemplate template = context.getBean(AgoraTemplate.class);
			assertThat(template.getAgoraProperties().getAppId()).isEqualTo("test-app-id");
			assertThat(template.getAgoraProperties().getAppCertificate()).isEqualTo("test-certificate");
			assertThat(template.getAgoraProperties().getLoginKey()).isEqualTo("test-login-key");
			assertThat(template.getAgoraProperties().getLoginSecret()).isEqualTo("test-login-secret");
			assertThat(template.getAgoraProperties().getExpirationTimeInSeconds()).isEqualTo(7200);
			assertThat(template.getAgoraProperties().getOssRegion()).isEqualTo(7);
			assertThat(template.getAgoraProperties().getViewWidth()).isEqualTo(640);
			assertThat(template.getAgoraProperties().getViewHeight()).isEqualTo(480);
		});
	}

	@Test
	public void shouldNotRegisterBeansWhenDisabled() {
		contextRunner.withPropertyValues("agora.enabled=false").run(context -> {
			assertThat(context).doesNotHaveBean(AgoraProperties.class);
			assertThat(context).doesNotHaveBean(AgoraOkHttp3Template.class);
			assertThat(context).doesNotHaveBean(AgoraTemplate.class);
		});
	}

	@Test
	public void shouldReuseExternalClientAndObjectMapper() {
		OkHttpClient externalClient = new OkHttpClient();
		ObjectMapper externalMapper = new ObjectMapper();
		contextRunner
				.withBean(OkHttpClient.class, () -> externalClient)
				.withBean(ObjectMapper.class, () -> externalMapper)
				.run(context -> {
					assertThat(context).hasSingleBean(AgoraOkHttp3Template.class);
					AgoraOkHttp3Template template = context.getBean(AgoraOkHttp3Template.class);
					assertThat(ReflectionTestUtils.getField(template, "okhttp3Client")).isSameAs(externalClient);
					assertThat(ReflectionTestUtils.getField(template, "objectMapper")).isSameAs(externalMapper);
				});
	}

	@Test
	public void shouldBindLocalRecordingProperties() {
		recordingContextRunner.withPropertyValues(
				"agora.recording.lib-path=/usr/local/agora/lib",
				"agora.recording.idle-limit-sec=120",
				"agora.recording.audio-only=true").run(context -> {
			assertThat(context).hasSingleBean(AgoraRecordingProperties.class);
			AgoraRecordingProperties properties = context.getBean(AgoraRecordingProperties.class);
			assertThat(properties.getLibPath()).isEqualTo("/usr/local/agora/lib");
			assertThat(properties.getIdleLimitSec()).isEqualTo(120);
			assertThat(properties.isAudioOnly()).isTrue();
		});
	}

}
