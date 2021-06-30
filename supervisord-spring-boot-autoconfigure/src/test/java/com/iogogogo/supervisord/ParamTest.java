package com.iogogogo.supervisord;

import com.iogogogo.supervisord.core.ResponseParser;
import com.iogogogo.supervisord.exception.SupervisordException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by tao.zeng on 2021/6/30.
 */
@Slf4j
public class ParamTest {

    private static final String PARAMS_S = "<params>";
    private static final String PARAMS_E = "</params>";
    private static final String PARAM_S = "<param>";
    private static final String PARAM_E = "</param>";
    private static final String VALUE_S = "<value>";
    private static final String VALUE_E = "</value>";
    private static final String METHOD_CALL_S = "<methodCall>";
    private static final String METHOD_CALL_E = "</methodCall>";
    private static final String METHOD_NAME_S = "<methodName>";
    private static final String METHOD_NAME_E = "</methodName>";
    private static final String INT_S = "<int>";
    private static final String INT_E = "</int>";
    private static final String STRING_S = "<string>";
    private static final String STRING_E = "</string>";

    private String buildCall(String method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n")
                .append(METHOD_CALL_S)
                .append(METHOD_NAME_S).append(method).append(METHOD_NAME_E);
        if (params != null && params.length > 0) {
            sb.append(PARAMS_S);
            for (Object arg : params) {
                sb.append(PARAM_S).append(VALUE_S);
                if (arg instanceof Integer) {
                    sb.append(INT_S).append(arg).append(INT_E);
                } else {
                    sb.append(STRING_S).append(arg).append(STRING_E);
                }
                sb.append(VALUE_E).append(PARAM_E);
            }
            sb.append(PARAMS_E);
        }
        sb.append(METHOD_CALL_E);
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(buildCall("supervisor.system.listMethods"));
    }

    @Test
    public void test1() throws IOException, SupervisordException {
        OkHttpClient httpClient = new OkHttpClient();

        String param = "<?xml version=\"1.0\"?>" + "<methodCall><methodName>system.listMethods</methodName></methodCall>";

        Request request = new Request.Builder()
                .url("http://192.168.31.124:9001/RPC2")
                .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
                .post(RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_XML_VALUE), param.getBytes(StandardCharsets.UTF_8)))
                .build();

        Response response = httpClient.newCall(request).execute();

        String string = response.body().string();

        InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));

        Object parse = new ResponseParser().parse(inputStream);

        log.info("parse:{}", parse);

    }
}
