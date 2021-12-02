package io.agora.spring.boot.resp;

import lombok.Data;

/**
 * 声网返回参数
 *      详情参考文档： https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful#acquire-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B
 */
@Data
public class AgoraResponse2{

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * sid
     */
    private String sid;

}
