package io.agora.media;/**
 * Extended {@link Packable} helpers used by the Agora token builders.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
