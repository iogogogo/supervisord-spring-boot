package com.iogogogo.supervisord.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Created by tao.zeng on 2021/6/21.
 */
@Data
@ConfigurationProperties(prefix = SupervisordProperties.SUPERVISORD_PREFIX)
public class SupervisordProperties {

    /**
     * The Supervisord prefix.
     */
    public static final String SUPERVISORD_PREFIX = "supervisord";

    /**
     * The Url.
     */
    private String url;

    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;

    /**
     * The sslEnable.
     */
    private boolean sslEnable = true;

    /**
     * The okHttp config
     */
    private OkHttp okHttp = new OkHttp();

    /**
     * The type Ok http.
     */
    @Data
    public static class OkHttp {

        /**
         * The Connect timeout.
         */
        private Duration connectTimeout = Duration.ofSeconds(60);

        /**
         * The Read timeout.
         */
        private Duration readTimeout = Duration.ofSeconds(60);

        /**
         * The Write timeout.
         */
        private Duration writeTimeout = Duration.ofSeconds(60);

        /**
         * The Call timeout.
         */
        private Duration callTimeout = Duration.ofSeconds(60);
    }
}
