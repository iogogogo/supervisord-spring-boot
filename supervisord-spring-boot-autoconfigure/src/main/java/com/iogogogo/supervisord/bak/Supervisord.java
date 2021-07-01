package com.iogogogo.supervisord.bak;

import com.iogogogo.supervisord.annotation.SupervisordEvent;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.util.BeanMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tao.zeng on 2021/6/21.
 */
// @SuppressWarnings("unchecked")
public class Supervisord implements SupervisordConstants {

    private final static Logger LOGGER = LoggerFactory.getLogger(Supervisord.class);

    private final static String DEFAULT_URL = "http://localhost:9001/RPC2";
    private final static String DEFAULT_NAMESPACE = "supervisor";
    private final static String DOT = ".";

    private String api = DEFAULT_URL;
    private String namespace = DEFAULT_NAMESPACE;
    private String proxyURL;
    private int proxyPort;

    private String username;
    private String password;

    /**
     * Connect supervisord.
     *
     * @return the supervisord
     */
    public static Supervisord connect() {
        return new Supervisord();
    }

    /**
     * Connect supervisord.
     *
     * @param apiUrl the api url
     * @return the supervisord
     */
    public static Supervisord connect(String apiUrl) {
        Supervisord me = new Supervisord();
        me.api = apiUrl;
        return me;
    }

    /**
     * this can be got by the api getIdentification(#link getIdentification()); but to save one http
     * request, better to set it static
     *
     * @param namespace the command prefix: default supervisor.
     * @return the
     * @see #getIdentification() #getIdentification()#getIdentification()
     */
    public Supervisord namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * Proxy supervisord.
     *
     * @param host the host
     * @param port the port
     * @return the supervisord
     */
    public Supervisord proxy(String host, int port) {
        this.proxyURL = host;
        this.proxyPort = port;
        return this;
    }

    /**
     * Auth supervisord.
     *
     * @param username the username
     * @param password the password
     * @return the supervisord
     */
    public Supervisord auth(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }


    /**
     * Return the version of the RPC API used by supervisord
     *
     * @return string version version id
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String getAPIVersion() throws SupervisordException {
        return String.valueOf(new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_API_VERSION)));
    }

    /**
     * Return the version of the supervisor package in use by supervisord
     *
     * @return string version version id
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String getSupervisorVersion() throws SupervisordException {
        return String.valueOf(new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_SUPERVISOR_VERSION)));
    }

    /**
     * Return identifying string of supervisord
     *
     * @return string identifier identifying string
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String getIdentification() throws SupervisordException {
        return String.valueOf(new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_IDENTIFICATION)));
    }

    /**
     * {'statecode': 1, 'statename': 'RUNNING'} Return current state of supervisord as a struct
     *
     * @return struct A struct with keys int statecode, string statename
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Map<String, Object> getState() throws SupervisordException {
        return (Map<String, Object>) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_STATE));
    }

    /**
     * Return the PID of supervisord
     *
     * @return int PID
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public int getPID() throws SupervisordException {
        return (Integer) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_PID));
    }

    /**
     * Read length bytes from the main log starting at offset
     *
     * @param offset offset to start reading from
     * @param length number of bytes to read from the log.
     * @return string result Bytes of log* (#link http://supervisord.org/api.html#supervisor.rpcinterface.SupervisorNamespaceRPCInterface.readLog)
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String readLog(int offset, int length) throws SupervisordException {
        if (offset < 0) offset = 0;
        if (length < 0) length = 0;
        return String.valueOf(new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._READ_LOG), offset, length));
    }

    /**
     * Clear the main log.
     *
     * @return boolean result always returns True unless error
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean clearLog() throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._CLEAR_LOG));
    }

    /**
     * Shut down the supervisor process
     *
     * @return boolean result always returns True unless error
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean shutdown() throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._SHUTDOWN));
    }

    /**
     * Restart the supervisor process
     *
     * @return boolean result always return True unless error
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean restart() throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._RESTART));
    }


    /**
     * Get info about a process named name
     *
     * @param processName name The name of the process (or ‘group:name’) @return struct result A                    structure containing data about the process
     * @return string {http://supervisord.org/api.html#supervisor.rpcinterface.SupervisorNamespaceRPCInterface.getProcessInfo}
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public SupervisordProcess getProcessInfo(String processName) throws SupervisordException {
        Object result = new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_PROCESS_INFO), processName);
        return BeanMapUtils.mapToBean((Map<String, Object>) result, SupervisordProcess.class);
    }

    /**
     * Get info about all processes
     *
     * @return array result An array of process status results
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    @SuppressWarnings("unchecked")
    public List<SupervisordProcess> getAllProcessInfo() throws SupervisordException {
        Object[] call = (Object[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._GET_ALL_PROCESS_INFO));
        return Stream.of(call)
                .filter(x -> x instanceof Map)
                .map(x -> (Map<String, Object>) x)
                .map(x -> BeanMapUtils.mapToBean(x, SupervisordProcess.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Start a process
     *
     * @param processName name Process name (or group:name, or group:*)
     * @param waitToStart wait Wait for process to be fully started
     * @return boolean result Always true unless error
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean startProcess(String processName, boolean waitToStart) throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._START_PROCESS), processName, waitToStart);
    }

    /**
     * Start all processes listed in the configuration file
     *
     * @param waitToStart wait Wait for each process to be fully started
     * @return array result An array of process status info structs
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Object[] startAllProcesses(boolean waitToStart) throws SupervisordException {
        return (Object[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._START_ALL_PROCESSES), waitToStart);
    }

    /**
     * Start all processes in the group named ‘name’
     *
     * @param groupName   name The group name
     * @param waitToStart wait Wait for each process to be fully started
     * @return array result An array of process status info structs
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Object[] startProcessGroup(String groupName, boolean waitToStart) throws SupervisordException {
        return (Object[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._START_PROCESSES_GROUP), groupName, waitToStart);
    }

    /**
     * Stop a process named by name
     *
     * @param processName name The name of the process to stop (or ‘group:name’)
     * @param waitToStop  wait Wait for the process to be fully stopped
     * @return boolean result Always return True unless error
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean stopProcess(String processName, boolean waitToStop) throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._STOP_PROCESS), processName, waitToStop);
    }

    /**
     * Stop all processes in the process group named ‘name’
     *
     * @param groupName  name The group name
     * @param waitToStop wait Wait for each process to be fully stopped
     * @return array result An array of process status info structs
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Object stopProcessGroup(String groupName, boolean waitToStop) throws SupervisordException {
        return new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._STOP_PROCESSES_GROUP), groupName, waitToStop);
    }

    /**
     * Stop all processes in the process list
     *
     * @param waitToStop wait Wait for each process to be fully stopped
     * @return array result An array of process status info structs
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Object[] stopAllProcesses(boolean waitToStop) throws SupervisordException {
        return (Object[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._STOP_ALL_PROCESSES), waitToStop);
    }

    /**
     * Send an arbitrary UNIX signal to the process named by name
     *
     * @param processName name Name of the process to signal (or ‘group:name’)
     * @param signal      signal Signal to send, as name (‘HUP’) or number (‘1’)
     * @return the boolean
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public boolean signalProcess(String processName, Signal signal) throws SupervisordException {
        return (Boolean) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._SIGNAL_PROCESS), processName, signal.name());
    }

    /**
     * Send a signal to all processes in the group named ‘name’
     *
     * @param groupName name The group name
     * @param signal    signal Signal to send, as name (‘HUP’) or number (‘1’)
     * @return array boolean [ ]
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Boolean[] signalProcessGroup(String groupName, Signal signal) throws SupervisordException {
        return (Boolean[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._SIGNAL_PROCESSES_GROUP), groupName, signal.name());
    }


    /**
     * Send a signal to all processes in the process list
     *
     * @param signal signal Signal to send, as name (‘HUP’) or number (‘1’)
     * @return array An array of process status info structs
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Boolean[] signalAllProcesses(Signal signal) throws SupervisordException {
        return (Boolean[]) new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._SIGNAL_ALL_PROCESSES), signal.name());
    }

    /**
     * Send a string of chars to the stdin of the process name. If non-7-bit data is sent (unicode),
     * it is encoded to utf-8 before being sent to the process’ stdin. If chars is not a string or
     * is not unicode, raise INCORRECT_PARAMETERS. If the process is not running, raise NOT_RUNNING.
     * If the process’ stdin cannot accept input (e.g. it was closed by the child process), raise
     * NO_FILE.
     *
     * @param processName name The process name to send to (or ‘group:name’)
     * @param chars       The character data to send to the process
     * @return boolean result Always return True unless error
     */
//TODO
    public boolean sendProcessStdin(String processName, String chars) {
        return true;
    }


    /**
     * Send an event that will be received by event listener subprocesses subscribing to the
     * RemoteCommunicationEvent.
     *
     * @param type type String for the “type” key in the event header
     * @param data Data for the event body
     * @return boolean Always return True unless error
     */
//TODO
    public boolean sendRemoteCommEvent(String type, String data) {
        return true;
    }

    /**
     * Reload configuration
     *
     * @return boolean result always return True unless error
     */
//TODO
    public boolean reloadConfig() {
        return true;
    }


    /**
     * Update the config for a running process from config file.
     *
     * @param name name name of process group to add
     * @return boolean result true if successful
     */
//TODO
    public boolean addProcessGroup(String name) {
        return true;
    }


    /**
     * Remove a stopped process from the active configuration.
     *
     * @param name name of process group to remove
     * @return boolean result Indicates whether the removal was successful
     */
//TODO
    public boolean removeProcessGroup(String name) {
        return true;
    }

    ////////////////////

    /**
     * Read length bytes from name’s stdout log starting at offset
     *
     * @param name   the name of the process (or ‘group:name’)
     * @param offset offset to start reading from.
     * @param length number of bytes to read from the log.
     * @return string result Bytes of log
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String readProcessStdoutLog(String name, int offset, int length) throws SupervisordException {
        return String.valueOf(new SimpleXMLRPC().call(buildFullMethodCall(SupervisordConstants._READ_PROCESS_STDOUT_LOG), name, offset, length));
    }

    /**
     * Read length bytes from name’s stderr log starting at offset
     *
     * @param name   the name of the process (or ‘group:name’)
     * @param offset offset to start reading from.
     * @param length number of bytes to read from the log.
     * @return string result Bytes of log
     */
//TODO
    public boolean readProcessStderrLog(String name, int offset, int length) {
        return true;
    }

    /**
     * Provides a more efficient way to tail the (stdout) log than readProcessStdoutLog(). Use
     * readProcessStdoutLog() to read chunks and tailProcessStdoutLog() to tail.
     * <p>
     * Requests (length) bytes from the (name)’s log, starting at (offset). If the total log size is
     * greater than (offset + length), the overflow flag is set and the (offset) is automatically
     * increased to position the buffer at the end of the log. If less than (length) bytes are
     * available, the maximum number of available bytes will be returned. (offset) returned is
     * always the last offset in the log +1.
     *
     * @param name   the name of the process (or ‘group:name’)
     * @param offset offset to start reading from
     * @param length maximum number of bytes to return
     * @return array result [string bytes, int offset, bool overflow]
     */
//TODO
    public boolean tailProcessStdoutLog(String name, int offset, int length) {
        return true;
    }

    /**
     * Provides a more efficient way to tail the (stderr) log than readProcessStderrLog(). Use
     * readProcessStderrLog() to read chunks and tailProcessStderrLog() to tail.
     * <p>
     * Requests (length) bytes from the (name)’s log, starting at (offset). If the total log size is
     * greater than (offset + length), the overflow flag is set and the (offset) is automatically
     * increased to position the buffer at the end of the log. If less than (length) bytes are
     * available, the maximum number of available bytes will be returned. (offset) returned is
     * always the last offset in the log +1.
     *
     * @param name   the name of the process (or ‘group:name’)
     * @param offset offset to start reading from
     * @param length maximum number of bytes to return
     * @return array result [string bytes, int offset, bool overflow]
     */
//TODO
    public boolean tailProcessStderrLog(String name, int offset, int length) {
        return true;
    }

    /**
     * Clear the stdout and stderr logs for the named process and reopen them.
     *
     * @param name The name of the process (or ‘group:name’)
     * @return result Always True unless error
     */
//TODO
    public boolean clearProcessLogs(String name) {
        return true;
    }

    /**
     * Clear all process log files
     *
     * @return the boolean
     */
//TODO
    public boolean clearAllProcessLogs() {
        return true;
    }

    /**
     * List methods list.
     *
     * @return the list
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public List<String> listMethods() throws SupervisordException {
        Object[] call = (Object[]) new SimpleXMLRPC().call(SupervisordConstants.SYSTEM_LIST_METHODS);
        return Stream.of(call).map(String::valueOf).collect(Collectors.toList());
    }

    /**
     * Method help string.
     *
     * @param method the method
     * @return the string
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public String methodHelp(String method) throws SupervisordException {
        return String.valueOf(new SimpleXMLRPC().call(SupervisordConstants.SYSTEM_METHOD_HELP, buildFullMethodCall(method)));
    }

    /**
     * Method signature object [ ].
     *
     * @param method the method
     * @return the object [ ]
     * @throws SupervisordException the supervisord exception
     */
    @SupervisordEvent
    public Object[] methodSignature(String method) throws SupervisordException {
        return (Object[]) new SimpleXMLRPC().call(SupervisordConstants.SYSTEM_METHOD_SIGNATURE);
    }

    /**
     * Multicall.
     *
     * @param calls the calls
     */
// TODO
    void multicall(Object[] calls) {
    }

    ////

    /**
     * build a full method call with namespace prefix
     *
     * @param method method name
     * @return rpc
     */
    private String buildFullMethodCall(final String method) {
        return String.join("", namespace, DOT, method);
    }

    private class SimpleXMLRPC {
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

        /**
         * Call object.
         *
         * @param methodName the method name
         * @param args       the args
         * @return the object
         * @throws SupervisordException the supervisord exception
         */
        Object call(String methodName, Object... args) throws SupervisordException {
            return post(buildCall(methodName, args));
        }

        private Object post(String xml) throws SupervisordException {
            return null;
        }

        /**
         * Build Request
         *
         * @param method method to call
         * @param params arg list
         * @return xml desc
         */
        private String buildCall(String method, Object... params) {

            return null;
        }
    }
}
