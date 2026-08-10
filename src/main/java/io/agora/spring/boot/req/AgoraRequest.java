package io.agora.spring.boot.req;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Generic envelope for Agora REST API request bodies.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
@Data
public class AgoraRequest<T>{

    /**
     * 频道号
     */
    private String cname;

    /**
     * 录制用户号
     */
    private String uid;

    public AgoraRequest(String cname, String uid) {
        this.cname = cname;
        this.uid = uid;
    }

    private T clientRequest;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
