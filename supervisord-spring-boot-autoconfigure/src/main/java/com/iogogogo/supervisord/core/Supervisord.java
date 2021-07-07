package com.iogogogo.supervisord.core;

import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.tuple.Tuple;
import com.iogogogo.supervisord.tuple.Tuple2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Supervisord extends Constant {

    List<String> listMethods() throws IOException, SupervisordException;

    String methodHelp(String method) throws IOException, SupervisordException;

    Object methodSignature() throws IOException, SupervisordException;

    Object multicall() throws IOException, SupervisordException;

    Boolean addProcessGroup() throws IOException, SupervisordException;

    List<SupervisordProcess> clearAllProcessLogs() throws IOException, SupervisordException;

    Boolean clearLog() throws IOException, SupervisordException;

    Object clearProcessLog() throws IOException, SupervisordException;

    Object clearProcessLogs() throws IOException, SupervisordException;

    String getAPIVersion() throws IOException, SupervisordException;

    Object getAllConfigInfo() throws IOException, SupervisordException;

    List<SupervisordProcess> getAllProcessInfo() throws IOException, SupervisordException;

    String getIdentification() throws IOException, SupervisordException;

    Integer getPID() throws IOException, SupervisordException;

    SupervisordProcess getProcessInfo(String procName) throws IOException, SupervisordException;

    Map<String, Object> getState() throws IOException, SupervisordException;

    String getSupervisorVersion() throws IOException, SupervisordException;

    String getVersion() throws IOException, SupervisordException;

    String readLog(int offset, int length) throws IOException, SupervisordException;

    Object readMainLog() throws IOException, SupervisordException;

    Object readProcessLog() throws IOException, SupervisordException;

    Object readProcessStderrLog() throws IOException, SupervisordException;

    Object readProcessStdoutLog() throws IOException, SupervisordException;

    Object reloadConfig() throws IOException, SupervisordException;

    Object removeProcessGroup() throws IOException, SupervisordException;

    Boolean restart() throws IOException, SupervisordException;

    Object sendProcessStdin() throws IOException, SupervisordException;

    Object sendRemoteCommEvent() throws IOException, SupervisordException;

    Object shutdown() throws IOException, SupervisordException;

    Object signalAllProcesses() throws IOException, SupervisordException;

    Object signalProcess() throws IOException, SupervisordException;

    Object signalProcessGroup() throws IOException, SupervisordException;

    Object startAllProcesses() throws IOException, SupervisordException;

    Boolean startProcess(String processName, boolean waitToStart) throws IOException, SupervisordException;

    Object startProcessGroup() throws IOException, SupervisordException;

    Object stopAllProcesses() throws IOException, SupervisordException;

    Boolean stopProcess(String processName, boolean waitToStop) throws IOException, SupervisordException;

    Object stopProcessGroup() throws IOException, SupervisordException;

    Object tailProcessLog() throws IOException, SupervisordException;

    Object tailProcessStderrLog() throws IOException, SupervisordException;

    Object tailProcessStdoutLog() throws IOException, SupervisordException;

    default String buildFullParam(final String method) {
        return String.join("", DEFAULT_NAMESPACE, DOT, method);
    }

    default Tuple2<String, String> buildParam(String method, Object... params) {
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

        return Tuple.of(method, sb.toString());
    }
}
