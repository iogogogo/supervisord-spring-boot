package com.iogogogo.supervisord.core;

/**
 * Created by tao.zeng on 2021/6/30.
 */
public interface Constant {

    /**
     * The constant DEFAULT_NAMESPACE.
     */
    String DEFAULT_NAMESPACE = "supervisor";
    /**
     * The constant DOT.
     */
    String DOT = ".";

    /**
     * The constant PARAMS_S.
     */
    String PARAMS_S = "<params>";
    /**
     * The constant PARAMS_E.
     */
    String PARAMS_E = "</params>";
    /**
     * The constant PARAM_S.
     */
    String PARAM_S = "<param>";
    /**
     * The constant PARAM_E.
     */
    String PARAM_E = "</param>";
    /**
     * The constant VALUE_S.
     */
    String VALUE_S = "<value>";
    /**
     * The constant VALUE_E.
     */
    String VALUE_E = "</value>";
    /**
     * The constant METHOD_CALL_S.
     */
    String METHOD_CALL_S = "<methodCall>";
    /**
     * The constant METHOD_CALL_E.
     */
    String METHOD_CALL_E = "</methodCall>";
    /**
     * The constant METHOD_NAME_S.
     */
    String METHOD_NAME_S = "<methodName>";
    /**
     * The constant METHOD_NAME_E.
     */
    String METHOD_NAME_E = "</methodName>";
    /**
     * The constant INT_S.
     */
    String INT_S = "<int>";
    /**
     * The constant INT_E.
     */
    String INT_E = "</int>";
    /**
     * The constant STRING_S.
     */
    String STRING_S = "<string>";
    /**
     * The constant STRING_E.
     */
    String STRING_E = "</string>";

    /**
     * The interface Supervisor.
     */
    interface Supervisor {
        /**
         * The constant _ADD_PROCESS_GROUP.
         */
        String _ADD_PROCESS_GROUP = "addProcessGroup";
        /**
         * The constant _CLEAR_ALL_PROCESS_LOGS.
         */
        String _CLEAR_ALL_PROCESS_LOGS = "clearAllProcessLogs";
        /**
         * The constant _CLEAR_LOG.
         */
        String _CLEAR_LOG = "clearLog";
        /**
         * The constant _CLEAR_PROCESS_LOG.
         */
        String _CLEAR_PROCESS_LOG = "clearProcessLog";
        /**
         * The constant _CLEAR_PROCESS_LOGS.
         */
        String _CLEAR_PROCESS_LOGS = "clearProcessLogs";
        /**
         * The constant _API_VERSION.
         */
        String _GET_API_VERSION = "getAPIVersion";
        /**
         * The constant _ALL_CONFIG_INFO.
         */
        String _GET_ALL_CONFIG_INFO = "getAllConfigInfo";
        /**
         * The constant _ALL_PROCESS_INFO.
         */
        String _GET_ALL_PROCESS_INFO = "getAllProcessInfo";
        /**
         * The constant _IDENTIFICATION.
         */
        String _GET_IDENTIFICATION = "getIdentification";
        /**
         * The constant _PID.
         */
        String _GET_PID = "getPID";
        /**
         * The constant _PROCESS_INFO.
         */
        String _GET_PROCESS_INFO = "getProcessInfo";
        /**
         * The constant _STATE.
         */
        String _GET_STATE = "getState";
        /**
         * The constant _SUPERVISOR_VERSION.
         */
        String _GET_SUPERVISOR_VERSION = "getSupervisorVersion";
        /**
         * The constant _VERSION.
         */
        String _GET_VERSION = "getVersion";
        /**
         * The constant _READ_LOG.
         */
        String _READ_LOG = "readLog";
        /**
         * The constant _READ_MAIN_LOG.
         */
        String _READ_MAIN_LOG = "readMainLog";
        /**
         * The constant _READ_PROCESS_LOG.
         */
        String _READ_PROCESS_LOG = "readProcessLog";
        /**
         * The constant _READ_PROCESS_STDERR_LOG.
         */
        String _READ_PROCESS_STDERR_LOG = "readProcessStderrLog";
        /**
         * The constant _READ_PROCESS_STDOUT_LOG.
         */
        String _READ_PROCESS_STDOUT_LOG = "readProcessStdoutLog";
        /**
         * The constant _RELOAD_CONFIG.
         */
        String _RELOAD_CONFIG = "reloadConfig";
        /**
         * The constant _REMOVE_PROCESS_GROUP.
         */
        String _REMOVE_PROCESS_GROUP = "removeProcessGroup";
        /**
         * The constant _RESTART.
         */
        String _RESTART = "restart";
        /**
         * The constant _SEND_PROCESS_STDIN.
         */
        String _SEND_PROCESS_STDIN = "sendProcessStdin";
        /**
         * The constant _SEND_REMOTE_COMM_EVENT.
         */
        String _SEND_REMOTE_COMM_EVENT = "sendRemoteCommEvent";
        /**
         * The constant _SHUTDOWN.
         */
        String _SHUTDOWN = "shutdown";
        /**
         * The constant _SIGNAL_ALL_PROCESSES.
         */
        String _SIGNAL_ALL_PROCESSES = "signalAllProcesses";
        /**
         * The constant _SIGNAL_PROCESS.
         */
        String _SIGNAL_PROCESS = "signalProcess";
        /**
         * The constant _SIGNAL_PROCESS_GROUP.
         */
        String _SIGNAL_PROCESS_GROUP = "signalProcessGroup";
        /**
         * The constant _START_ALL_PROCESSES.
         */
        String _START_ALL_PROCESSES = "startAllProcesses";
        /**
         * The constant _START_PROCESS.
         */
        String _START_PROCESS = "startProcess";
        /**
         * The constant _START_PROCESS_GROUP.
         */
        String _START_PROCESS_GROUP = "startProcessGroup";
        /**
         * The constant _STOP_ALL_PROCESSES.
         */
        String _STOP_ALL_PROCESSES = "stopAllProcesses";
        /**
         * The constant _STOP_PROCESS.
         */
        String _STOP_PROCESS = "stopProcess";
        /**
         * The constant _STOP_PROCESS_GROUP.
         */
        String _STOP_PROCESS_GROUP = "stopProcessGroup";
        /**
         * The constant _TAIL_PROCESS_LOG.
         */
        String _TAIL_PROCESS_LOG = "tailProcessLog";
        /**
         * The constant _TAIL_PROCESS_STDERR_LOG.
         */
        String _TAIL_PROCESS_STDERR_LOG = "tailProcessStderrLog";
        /**
         * The constant _TAIL_PROCESS_STDOUT_LOG.
         */
        String _TAIL_PROCESS_STDOUT_LOG = "tailProcessStdoutLog";
    }

    /**
     * The interface System.
     */
    interface System {
        /**
         * The constant _LIST_METHODS.
         */
        String _LIST_METHODS = "system.listMethods";
        /**
         * The constant _METHOD_HELP.
         */
        String _METHOD_HELP = "system.methodHelp";
        /**
         * The constant _METHOD_SIGNATURE.
         */
        String _METHOD_SIGNATURE = "system.methodSignature";
        /**
         * The constant _MULTICALL.
         */
        String _MULTICALL = "system.multicall";
    }
}
