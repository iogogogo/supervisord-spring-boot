package com.iogogogo.supervisord;

import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by tao.zeng on 2021/6/22.
 */
@Slf4j
public class SupervisordTest {

    private final static String SUPERVISORD_URL = "http://192.168.31.124:9001/RPC2";

    private Supervisord supervisord;

    @Before
    public void doBefore() {
        supervisord = Supervisord.connect(SUPERVISORD_URL);
    }

    @After
    public void doAfter() {
    }

    @Test
    public void getAPIVersion() throws SupervisordException {
        log.info("getAPIVersion:{}", supervisord.getAPIVersion());
    }

    @Test
    public void getSupervisorVersion() throws SupervisordException {
        log.info("getSupervisorVersion:{}", supervisord.getSupervisorVersion());
    }

    @Test
    public void getIdentification() throws SupervisordException {
        String identification = supervisord.getIdentification();
        log.info("getIdentification:{}", identification);
    }

    @Test
    public void getState() throws SupervisordException {
        Map<String, Object> stateMap = supervisord.getState();
        stateMap.forEach((k, v) -> log.info("getState k:{}  v:{}", k, v));
    }

    @Test
    public void getPID() throws SupervisordException {
        int pid = supervisord.getPID();
        log.info("getPID:{}", pid);
    }

    @Test
    public void readLog() throws SupervisordException {
        String readLog = supervisord.readLog(100, 1000);
        log.info("readLog:{}", readLog);
    }

    @Test
    public void clearLog() {
    }

    @Test
    public void shutdown() {
    }

    @Test
    public void restart() {
    }

    @Test
    public void getProcessInfo() throws Exception {
        SupervisordProcess process = supervisord.getProcessInfo("logstash_test");
        log.info("getProcessInfo:{}", process);
    }

    @Test
    public void getAllProcessInfo() throws SupervisordException {
        List<SupervisordProcess> allProcessInfo = supervisord.getAllProcessInfo();
        for (SupervisordProcess proc : allProcessInfo) {
            log.info("getAllProcessInfo:{}", proc);
        }
    }

    @Test
    public void startProcess() throws SupervisordException {
        boolean startProcess = supervisord.startProcess("logstash_test", false);
        log.info("startProcess:{}", startProcess);
    }

    @Test
    public void startAllProcesses() {
    }

    @Test
    public void startProcessGroup() {
    }

    @Test
    public void stopProcess() throws SupervisordException {
        boolean stopProcess = supervisord.stopProcess("logstash_test", false);
        log.info("stopProcess:{}", stopProcess);
    }

    @Test
    public void stopProcessGroup() {
    }

    @Test
    public void stopAllProcesses() {
    }

    @Test
    public void signalProcess() {
    }

    @Test
    public void signalProcessGroup() {
    }

    @Test
    public void signalAllProcesses() {
    }

    @Test
    public void sendProcessStdin() {
    }

    @Test
    public void sendRemoteCommEvent() {
    }

    @Test
    public void reloadConfig() {
    }

    @Test
    public void addProcessGroup() {
    }

    @Test
    public void removeProcessGroup() {
    }

    @Test
    public void readProcessStdoutLog() throws SupervisordException {
        String stdoutLog = supervisord.readProcessStdoutLog("logstash_test", 0, 10240);
        System.out.println(stdoutLog);
    }

    @Test
    public void readProcessStderrLog() {
    }

    @Test
    public void tailProcessStdoutLog() {
    }

    @Test
    public void tailProcessStderrLog() {
    }

    @Test
    public void clearProcessLogs() {
    }

    @Test
    public void clearAllProcessLogs() {
    }

    @Test
    public void listMethods() throws SupervisordException {
        List<String> methods = supervisord.listMethods();
        for (String method : methods) {
            log.info(method);
        }
    }

    @Test
    public void methodHelp() throws SupervisordException {
        String help = supervisord.methodHelp("getProcessInfo");
        log.info("help:{}", help);
    }

    @Test
    public void methodSignature() {
    }

    @Test
    public void multicall() {
    }

}
