package io.agora.spring.boot;

import java.text.MessageFormat;

/**
 * https://docs.agora.io/cn/cloud-recording/cloud_recording_api_rest?platform=RESTful
 * https://docs.agora.io/cn/Video/channel_management_overview?platform=RESTful
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
public enum AgoraApiAddress {

	// ---------------- Cloud recording ------------------

	/** Acquire a cloud recording resource id. */
	ACQUIRE_RESOURCE_ID("Acquire cloud recording resource id", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/acquire"),
	/** Start a cloud recording session. */
	START_CLOUD_RECORDING("Start cloud recording", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/mode/{2}/start"),
	/** Update a cloud recording session. */
	UPDATE_CLOUD_RECORDING("Update cloud recording", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/updateLayout"),
	/** Update the mixed-stream layout of a cloud recording. */
	UPDATE_CLOUD_RECORDING_LAYOUT("Update mixed-stream layout", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/update"),
	/** Query the status of a cloud recording. */
	QUERY_CLOUD_RECORDING("Query cloud recording status", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/query"),
	/** Stop a cloud recording session. */
	STOP_CLOUD_RECORDING("Stop cloud recording", RequestMethod.POST,"https://api.agora.io/v1/apps/{0}/cloud_recording/resourceid/{1}/sid/{2}/mode/{3}/stop"),

	// ---------------- Project management ------------------

	/** Create a project. */
	PROJECT_POST("Create project", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/** Get a specific project. */
	PROJECT_GET("Get project", RequestMethod.POST,"https://api.agora.io/v1/project"),
	/** List all projects. */
	PROJECTS_GET("List projects", RequestMethod.POST,"https://api.agora.io/v1/projects"),
	/** Enable or disable a project. */
	PROJECT_STATUS_POST("Enable/disable project", RequestMethod.POST,"https://api.agora.io/v1/projects_status"),
	/** Get usage data for a specific project. */
	PROJECT_USAGE_GET("Get project usage", RequestMethod.POST,"https://api.agora.io/v3/usage"),
	/** Set the recording server IP. */
	RECORDING_CONFIG_POST("Set recording server IP", RequestMethod.POST,"https://api.agora.io/v1/recording_config"),
	/** Enable or disable the primary App certificate. */
	SIGNKEY_POST("Enable/disable App certificate", RequestMethod.POST,"https://api.agora.io/v1/signkey"),
	/** Reset the primary App certificate. */
	SIGNKEY_RESET_POST("Reset App certificate", RequestMethod.POST,"https://api.agora.io/v1/reset_signkey"),

	// ---------------- Kicking rules ------------------

	/** Create a kicking rule. */
	KICKING_RULE_POST("Create kicking rule", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/** List kicking rules. */
	KICKING_RULE_GET("List kicking rules", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/** Update the effective time of a kicking rule. */
	KICKING_RULE_PUT("Update kicking rule", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),
	/** Delete a kicking rule. */
	KICKING_RULE_DELETE("Delete kicking rule", RequestMethod.POST,"https://api.agora.io/v1/kicking-rule"),

	// ---------------- Online channel queries ------------------

	/** Query the state of a user in a channel. */
	CHANNEL_USER_STATE("Query user state", RequestMethod.GET,"https://api.agora.io/dev/v1/channel/user/property/{0}/{1}/{2}"),
	/** List the users in a channel. */
	CHANNEL_USER_LIST("List channel users", RequestMethod.GET,"https://api.agora.io/dev/v1/channel/user/{0}/{1}"),
	/** Paginated list of channels for a project. */
	CHANNEL_LIST("List project channels", RequestMethod.GET,"https://api.agora.io/dev/v1/channel/{0}"),

	 ;

	private String opt;

	private RequestMethod method;
	private String url;

    AgoraApiAddress(String opt, RequestMethod method,String url) {
		this.opt = opt;
		this.method = method;
		this.url = url;
	}

	public String getOpt() {
		return opt;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getUrl(Object ...args) {
		return MessageFormat.format(url, args);
	}

}
