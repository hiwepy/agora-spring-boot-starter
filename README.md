<a id="readme-top"></a>

<div align="center">

# agora-spring-boot-starter

**Spring Boot Starter for agora**

[![Maven Central](https://img.shields.io/maven-central/v/io.github.easy4j/agora-spring-boot-starter)](https://github.com/easy-4-java/agora-spring-boot-starter)
[![Java](https://img.shields.io/badge/Java-17-orange)](#3-requirements-and-compatibility)
[![License](https://img.shields.io/badge/license-Apache-2.0-green)](https://www.apache.org/licenses/LICENSE-2.0)

[简体中文](./README.zh-CN.md) | [English](./README.md)

[Positioning](#1-positioning) · [Capabilities](#2-core-capabilities) ·
[Dependency](#5-dependency) · [Quick Start](#6-quick-start) ·
[Configuration](#7-configuration-reference) · [Versions](#9-version-lines-and-compatibility) ·
[Build](#10-build-and-test) · [License](#12-license)

</div>

---

> **Current Version**：`4.1.x.20260527-SNAPSHOT`<br>
> **JDK Baseline**：`17`<br>
> **Group ID**：`io.github.easy4j`<br>
> **Artifact ID**：`agora-spring-boot-starter`<br>
> **License**：Apache License 2.0<br>

## 1. Positioning

**agora-spring-boot-starter** is a Spring Boot starter that integrates **agora** for applications using agora. It provides auto-configuration, property binding, and ready-to-use beans so that applications can consume agora capabilities with minimal setup.

| Dimension | Description |
|---|---|
| Type | Spring Boot Starter |
| Consumers | Spring Boot applications using agora |
| Core Capabilities | auto-configuration, property binding, ready-to-use beans for agora |
| JDK | `17` |
| Coordinates | `io.github.easy4j:agora-spring-boot-starter:4.1.x.20260527-SNAPSHOT` |
| Config Prefix | `agora` |

## 2. Core Capabilities

| Capability | Status | Description |
|---|:---:|---|
| Auto-configuration | ✅ Stable | Registers agora beans automatically |
| Property Binding | ✅ Stable | Binds `agora.*` to `AgoraProperties` (inherited from agora-java-sdk POJO) |
| `AgoraOkHttp3Template` bean | ✅ Stable | Auto-registered via AgoraAutoConfiguration |

## 3. Requirements and Compatibility

| Dependency | Minimum | Evidence |
|---|---:|---|
| JDK | `17` | `pom.xml` |
| Spring Boot | `4.1.0-M4` | `pom.xml` parent |
| Maven | `3.6+` | Maven Enforcer |

## 4. Auto-configuration

The starter auto-configures the following beans:

| Bean | Condition | Missing Behavior |
|---|---|---|
| `AgoraOkHttp3Template` | classpath + property | not created |
| `AgoraTemplate` | classpath + property | not created |
| `AgoraProperties` | property | not bound |
| `AgoraRecordingProperties` | via `AgoraLocalRecordingConfiguration` | not bound |

`AgoraTemplate` / `AgoraOkHttp3Template` / `AgoraUserIdProvider` classes are provided by the
[`agora-java-sdk`](https://github.com/easy-4-java/agora-java-sdk) dependency (packages `io.agora.cloud`,
`io.agora.media`, `io.agora.recording`); the starter only registers beans and binds properties.

Auto-configuration registration:

- `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (Spring Boot 2.7+ / 3.x / 4.x)
- `META-INF/spring.factories` (Spring Boot 2.x legacy)

## 5. Dependency

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>agora-spring-boot-starter</artifactId>
    <version>4.1.x.20260527-SNAPSHOT</version>
</dependency>
```

The starter depends on [`agora-java-sdk`](https://github.com/easy-4-java/agora-java-sdk), which provides
the `AgoraTemplate` REST facade, token builders and the on-premise recording bridge. All SDK classes are
pulled in transitively — no extra coordinates needed.

## 6. Quick Start

### 6.1 Add dependency

Add the dependency above to your `pom.xml`.

### 6.2 Configure

```yaml
agora:
  enabled: true
```

### 6.3 Use the bean

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Then inject the auto-configured bean in your code:

```java
@Autowired
private AgoraOkHttp3Template agoraOkHttp3Template;
```

## 7. Configuration Reference

### 7.1 Config Prefix

`agora`

### 7.2 Configuration Items

| Property | Type | Default | Required | Description | Sensitive |
|---|---|---|:---:|---|:---:|
| `agora.enabled` | boolean | `true` | No | Enable the starter | No |
| `agora.app-id` | String | - | Yes | Agora application id | No |
| `agora.app-certificate` | String | - | Yes | Agora application certificate used to sign tokens | Yes |
| `agora.login-key` | String | - | Yes | Agora REST API login key | Yes |
| `agora.login-secret` | String | - | Yes | Agora REST API login secret | Yes |
| `agora.expiration-time-in-seconds` | int | `3600` | No | Token validity in seconds | No |
| `agora.oss-region` | Integer | - | No | Recording region, e.g. `7` Hong Kong, `10` Singapore | No |
| `agora.view-width` / `agora.view-height` | Integer | - | No | Recording video canvas size in pixels | No |
<!-- additional properties below -->

## 8. Version Lines and Compatibility

| Branch | JDK | Spring Boot | Component Version | Status |
|---|---:|---:|---|:---:|
| `2.3.x` / `2.7.x` | `8+` | 2.3.x / 2.7.x | `1.0.x` | Maintenance |
| `3.0.x` ~ `3.5.x` | `17` | 3.x | `2.0.x` | Maintenance |
| `4.0.x` / `4.1.x` | `17+` | 4.x | `3.0.x` | Active |

## 9. Build and Test

```bash
mvn clean verify
mvn -pl agora-spring-boot-starter -am test
```

## 10. Troubleshooting

| Symptom | Diagnosis | Resolution |
|---|---|---|
| Bean not created | Check auto-configuration report | Verify `agora.enabled=true` and classpath |
| `ClassNotFoundException` | Missing dependency | Add the required module |
| Version conflict | `mvn dependency:tree` | Use BOM for version alignment |

## 11. Contribution

1. Fork the repository.
2. Create a feature branch.
3. Run `mvn clean verify` before submitting.
4. Submit a pull request.

## 12. License

This project is licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

---

<div align="center">

[Back to top](#readme-top) · [Issues](https://github.com/easy-4-java/agora-spring-boot-starter/issues) · [Repository](https://github.com/easy-4-java/agora-spring-boot-starter)

</div>
