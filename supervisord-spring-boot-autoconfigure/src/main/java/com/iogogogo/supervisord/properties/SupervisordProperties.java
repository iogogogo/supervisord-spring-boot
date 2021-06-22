package com.iogogogo.supervisord.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tao.zeng on 2021/6/21.
 */
@Data
@ConfigurationProperties(prefix = SupervisordProperties.SUPERVISORD_PREFIX)
public class SupervisordProperties {

    /**
     * The Supervisord prefix.
     */
    static final String SUPERVISORD_PREFIX = "supervisord";

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
}
