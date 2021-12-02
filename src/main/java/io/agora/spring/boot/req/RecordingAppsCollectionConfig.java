package io.agora.spring.boot.req;

import lombok.Data;

/**
 * 应用设置
 */
@Data
public class RecordingAppsCollectionConfig {

    /**
     *  combinationPolicy：（选填）String 类型，各个云端录制应用的组合方式。
     *  "default"：（默认）除延时转码外，均选用此种方式。
     *  "postpone_transcoding"：如需延时转码，则选用此种方式。
     */
    private String combinationPolicy = "default";

}
