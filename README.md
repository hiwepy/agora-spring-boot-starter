# agora-spring-boot-starter
Agora starter for spring boot

### 组件简介

https://docs.agora.io/cn/documents

 > 基于 声网API 的 Spring Boot Starter 实现

1. 声网API封装： https://docs.agora.io/cn/rtc/restfulapi
2. 提供基础的工具对象

https://docs.agora.io/cn/Recording/recording_integrate_java?platform=Linux
https://docs.agora.io/cn/Recording/landing-page?platform=Linux

### 使用说明

##### 1、Spring Boot 项目添加 Maven 依赖

``` xml
<dependency>
	<groupId>com.github.hiwepy</groupId>
	<artifactId>agora-spring-boot-starter</artifactId>
	<version>1.0.0.RELEASE</version>
</dependency>
```


##### 2、在`application.yml`文件中增加如下配置

```yaml
################################################################################################################
###agora基本配置：
################################################################################################################
agora:
  app-id: xxxxxxxxxxxxxx
  app-certificate: xxxxxxxxxxxxxx
  expiration-time-in-seconds: 3600
  oss-region: 7
  login-key: xxxxxxxxxxxxxx
  login-secret: xxxxxxxxxxxxxx
  recording:
    channel-profile: CHANNEL_PROFILE_LIVE_BROADCASTING
    mix-resolution: 360,640,15,500
    #low-udp-port: 40000
    #high-udp-port: 41000
    log-level: 5
```

##### 3、使用示例

```java

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.agora.spring.boot.resp.AcquireResourceResponse;

@SpringBootApplication
public class AgoraApplication_Test {

    @Autowired
    private AgoraTemplate template;
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void testAcquireId() throws Exception {

        AcquireResourceResponse response =  template.opsForCloudRecording().acquireId("10000", "121212");
        System.out.println(objectMapper.writeValueAsString(response));

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AgoraApplication_Test.class, args);
    }

}
```

## Jeebiz 技术社区

Jeebiz 技术社区 **微信公共号**、**小程序**，欢迎关注反馈意见和一起交流，关注公众号回复「Jeebiz」拉你入群。

|公共号|小程序|
|---|---|
| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/qrcode_for_gh_1d965ea2dfd1_344.jpg)| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/gh_09d7d00da63e_344.jpg)|
