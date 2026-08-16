package io.agora.spring.boot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.agora.cloud.AgoraOkHttp3Template;
import io.agora.cloud.AgoraTemplate;
import io.agora.cloud.AgoraUserIdProvider;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot auto-configuration for the Agora RTC/RTM and cloud recording integration.
 * <p>
 * Registers an {@link AgoraOkHttp3Template} for calling the Agora REST API and an
 * {@link AgoraTemplate} facade exposing token generation and channel / recording
 * operations. Both beans rely on {@link AgoraProperties} bound to the
 * {@code agora.*} namespace.
 * </p>
 *
 * <h3>Configuration</h3>
 * <ul>
 *   <li>{@code agora.app-id} — Agora application id</li>
 *   <li>{@code agora.app-certificate} — Agora application certificate</li>
 *   <li>{@code agora.login-key} / {@code agora.login-secret} — REST API credentials (required)</li>
 *   <li>{@code agora.expiration-time-in-seconds} — token validity (default {@code 3600})</li>
 *   <li>{@code agora.oss-region} — recording region (e.g. {@code 7} Hong Kong, {@code 10} Singapore)</li>
 * </ul>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(AgoraTemplate.class)
@ConditionalOnProperty(prefix = AgoraProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({ AgoraProperties.class })
public class AgoraAutoConfiguration {

	/**
	 * Creates the {@link AgoraOkHttp3Template} used to call the Agora REST API.
	 * <p>Falls back to default {@link OkHttpClient} and {@link ObjectMapper}
	 * instances when none are provided by the application context.</p>
	 *
	 * @param okhttp3ClientProvider optional OkHttpClient provider
	 * @param objectMapperProvider  optional ObjectMapper provider
	 * @param agoraProperties       the bound {@code agora.*} properties
	 * @return a configured {@link AgoraOkHttp3Template}
	 */
	@Bean
	@ConditionalOnMissingBean
	public AgoraOkHttp3Template agoraOkHttp3Template(ObjectProvider<OkHttpClient> okhttp3ClientProvider,
													 ObjectProvider<ObjectMapper> objectMapperProvider,
													 AgoraProperties agoraProperties) {

		OkHttpClient okhttp3Client = okhttp3ClientProvider.getIfAvailable(() -> new OkHttpClient.Builder().build());

		ObjectMapper objectMapper = objectMapperProvider.getIfAvailable(() -> {
			ObjectMapper objectMapperDef = new ObjectMapper();
			objectMapperDef.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			objectMapperDef.enable(MapperFeature.USE_GETTERS_AS_SETTERS);
			objectMapperDef.enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
			objectMapperDef.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			objectMapperDef.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapperDef;
		});

		return new AgoraOkHttp3Template(okhttp3Client, objectMapper, agoraProperties);
	}

	/**
	 * Creates the {@link AgoraTemplate} facade.
	 * <p>Falls back to a no-op {@link AgoraUserIdProvider} when the application
	 * does not provide one.</p>
	 *
	 * @param agoraUserIdProvider  optional user id provider
	 * @param agoraOkHttp3Template the OkHttp template
	 * @param agoraProperties      the bound {@code agora.*} properties
	 * @return a configured {@link AgoraTemplate}
	 */
	@Bean
	@ConditionalOnMissingBean
	public AgoraTemplate agoraTemplate(ObjectProvider<AgoraUserIdProvider> agoraUserIdProvider,
									   AgoraOkHttp3Template agoraOkHttp3Template,
									   AgoraProperties agoraProperties) {
		return new AgoraTemplate(agoraUserIdProvider.getIfAvailable(() -> {
			return new AgoraUserIdProvider() {};
		}), agoraOkHttp3Template, agoraProperties );
	}

}
