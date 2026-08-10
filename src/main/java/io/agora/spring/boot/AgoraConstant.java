package io.agora.spring.boot;

/**
 * Agora-specific constants such as endpoint URLs and default recording values.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public interface AgoraConstant {

    // Get all users in a channel
    // https://api.agora.io/dev/v1/channel/user/{appid}/{channelName}
    String URL_CHANNEL_USER = "https://api.agora.io/dev/v1/channel/user/{0}/{1}";

    // User kicking/ban rule endpoint
    // https://api.agora.io/dev/v1/kicking-rule
    String URL_RULE = "https://api.agora.io/dev/v1/kicking-rule";

    // Default UID used by recording requests
    String RECORDING_UID = "10";

    // Storage location /video/{{yyyy-MM-dd}}
    String VEIDO_PAHT = "video";
}
