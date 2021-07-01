package com.iogogogo.supervisord.configuration;

import com.iogogogo.supervisord.properties.SupervisordProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * Created by tao.zeng on 2021/6/30.
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = SupervisordProperties.SUPERVISORD_PREFIX, name = "url")
public class OkHttpConfiguration {

    private final SupervisordProperties supervisordProperties;

    public OkHttpConfiguration(SupervisordProperties supervisordProperties) {
        this.supervisordProperties = supervisordProperties;
    }

    @Bean
    public OkHttpClient httpClient() {
        SupervisordProperties.OkHttp okHttp = supervisordProperties.getOkHttp();

        String username = supervisordProperties.getUsername();
        String password = supervisordProperties.getPassword();

        OkHttpClient.Builder xBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(okHttp.getConnectTimeout())
                .callTimeout(okHttp.getCallTimeout())
                .readTimeout(okHttp.getReadTimeout())
                .writeTimeout(okHttp.getWriteTimeout())
                .hostnameVerifier((hostName, session) -> true)
                .retryOnConnectionFailure(true)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder()
                            .header("User-Agent", "Mozilla/5.0")
                            .header("Accept-Language", "en-US,en;q=0.5")
                            .header("Accept", "*/*")
                            .header("Content-Type", MediaType.APPLICATION_XML_VALUE)
                            .method(original.method(), original.body());

                    if (StringUtils.hasLength(username)) {
                        String auth = new String(Base64.getEncoder().encode(String.join(":", username, password)
                                .getBytes(StandardCharsets.UTF_8)));
                        builder.header("Authorization", "Basic " + auth);
                        log.debug("auth: {}", username);
                    }

                    return chain.proceed(builder.build());
                });

        if (!supervisordProperties.isSslEnable()) {
            TrustManager[] trustManagers = buildTrustManagers();
            xBuilder.sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0]);
        }

        return xBuilder.build();
    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @param trustAllCerts
     * @return
     */
    private SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }
}
