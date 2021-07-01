package com.iogogogo.supervisord.core.rpc;

import com.iogogogo.supervisord.annotation.SupervisordEvent;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import com.iogogogo.supervisord.tuple.Tuple2;
import com.iogogogo.supervisord.util.BeanMapUtils;
import com.iogogogo.supervisord.util.XmlParse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tao.zeng on 2021/6/30.
 */
@Slf4j
public class SupervisordRpc implements Supervisord {

    private final OkHttpClient httpClient;

    private final SupervisordProperties supervisordProperties;

    /**
     * Instantiates a new Supervisord rpc.
     *
     * @param httpClient            the http client
     * @param supervisordProperties the supervisord properties
     */
    public SupervisordRpc(OkHttpClient httpClient, SupervisordProperties supervisordProperties) {
        this.httpClient = httpClient;
        this.supervisordProperties = supervisordProperties;
    }

    @Override
    @SupervisordEvent
    public List<String> listMethods() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(System._LIST_METHODS);
        Object[] call = XmlParse.parse(call(param));
        return Stream.of(call).map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    @SupervisordEvent
    public String methodHelp(String method) throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(System._METHOD_HELP);
        return XmlParse.parse(call(param));
    }

    @Override
    @SupervisordEvent
    public Object methodSignature() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object multicall() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object addProcessGroup() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object clearAllProcessLogs() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object clearLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object clearProcessLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object clearProcessLogs() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public String getAPIVersion() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_API_VERSION));
        return XmlParse.parse(call(param));
    }

    @SupervisordEvent
    @Override
    public Object getAllConfigInfo() throws IOException, SupervisordException {
        return null;
    }


    @SupervisordEvent
    @Override
    public Integer getPID() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_PID));
        return XmlParse.parse(call(param));
    }

    @Override
    @SupervisordEvent
    public SupervisordProcess getProcessInfo(String procName) throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_PROCESS_INFO), procName);
        Map<String, Object> map = XmlParse.parse(call(param));
        return BeanMapUtils.mapToBean(map, SupervisordProcess.class);
    }

    @Override
    @SupervisordEvent
    public Map<String, Object> getState() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_STATE));
        return XmlParse.parse(call(param));
    }

    @Override
    @SupervisordEvent
    public String getSupervisorVersion() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_SUPERVISOR_VERSION));
        return XmlParse.parse(call(param));
    }

    @Override
    @SupervisordEvent
    public String getVersion() throws IOException, SupervisordException {
        return getAPIVersion();
    }

    /**
     * Read length bytes from the main log starting at offset
     * <p>
     * offset offset to start reading from. @param int length number of bytes to read from the log. @return string result Bytes of log
     * <p>
     * It can either return the entire log, a number of characters from the tail of the log, or a slice of the log specified by the offset and length parameters:
     *
     * @param offset offset to start reading from
     * @param length number of bytes to read from the log
     * @return string result Bytes of log
     * @throws IOException
     * @throws SupervisordException
     */
    @Override
    @SupervisordEvent
    public String readLog(int offset, int length) throws IOException, SupervisordException {
        if (offset < 0) offset = 0;
        if (length < 0) length = 0;
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._READ_LOG), offset, length);
        return XmlParse.parse(call(param));
    }

    @SupervisordEvent
    @Override
    public Object readMainLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object readProcessLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object readProcessStderrLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object readProcessStdoutLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object reloadConfig() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object removeProcessGroup() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Boolean restart() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._RESTART));
        return XmlParse.parse(call(param));
    }

    @SupervisordEvent
    @Override
    public Object sendProcessStdin() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object sendRemoteCommEvent() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object shutdown() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object signalAllProcesses() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object signalProcess() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object signalProcessGroup() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object startAllProcesses() throws IOException, SupervisordException {
        return null;
    }

    @Override
    @SupervisordEvent
    public Boolean startProcess(String processName, boolean waitToStart) throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._START_PROCESS), processName, waitToStart);
        return XmlParse.parse(call(param));
    }

    @SupervisordEvent
    @Override
    public Object startProcessGroup() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object stopAllProcesses() throws IOException, SupervisordException {
        return null;
    }

    @Override
    @SupervisordEvent
    public Boolean stopProcess(String processName, boolean waitToStop) throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._STOP_PROCESS), processName, waitToStop);
        return XmlParse.parse(call(param));
    }

    @SupervisordEvent
    @Override
    public Object stopProcessGroup() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object tailProcessLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object tailProcessStderrLog() throws IOException, SupervisordException {
        return null;
    }

    @SupervisordEvent
    @Override
    public Object tailProcessStdoutLog() throws IOException, SupervisordException {
        return null;
    }

    @Override
    @SupervisordEvent
    @SuppressWarnings("unchecked")
    public List<SupervisordProcess> getAllProcessInfo() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_ALL_PROCESS_INFO));
        Object[] call = XmlParse.parse(call(param));
        return Stream.of(call)
                .filter(x -> x instanceof Map)
                .map(x -> (Map<String, Object>) x)
                .map(x -> BeanMapUtils.mapToBean(x, SupervisordProcess.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    @SupervisordEvent
    public String getIdentification() throws IOException, SupervisordException {
        Tuple2<String, String> param = buildParam(buildFullParam(Supervisor._GET_IDENTIFICATION));
        return XmlParse.parse(call(param));
    }


    /**
     * Call string.
     *
     * @param param the param
     * @return the string
     * @throws IOException          the io exception
     * @throws SupervisordException the supervisord exception
     */
    private String call(Tuple2<String, String> param) throws IOException, SupervisordException {
        log.debug("supervisord param:\n{}", param);

        RequestBody requestBody = RequestBody
                .create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_XML_VALUE),
                        param._2().getBytes(StandardCharsets.UTF_8));

        Request request = new Request.Builder()
                .url(supervisordProperties.getUrl())
                .post(requestBody)
                .build();

        Response response = httpClient.newCall(request).execute();

        log.info("supervisord {} response code: {}", param._1, response.code());

        ResponseBody responseBody = response.body();

        if (response.isSuccessful() && Objects.nonNull(responseBody)) {
            String resp = responseBody.string();
            log.debug("supervisord {} response: {}", param._1, resp);
            return resp;
        } else {
            throw new SupervisordException(response.message());
        }
    }
}
