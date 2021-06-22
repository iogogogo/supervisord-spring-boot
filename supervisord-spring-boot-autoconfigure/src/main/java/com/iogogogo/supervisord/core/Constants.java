package com.iogogogo.supervisord.core;

/**
 * // API METHOD DEFINE:
 * supervisor.addProcessGroup
 * supervisor.clearAllProcessLogs
 * supervisor.clearProcessLog
 * supervisor.clearProcessLogs
 * supervisor.getAllConfigInfo
 * supervisor.getIdentification
 * supervisor.getSupervisorVersion
 * supervisor.readMainLog
 * supervisor.readProcessLog
 * supervisor.readProcessStderrLog
 * supervisor.readProcessStdoutLog
 * supervisor.reloadConfig
 * supervisor.removeProcessGroup
 * supervisor.sendProcessStdin
 * supervisor.sendRemoteCommEvent
 * supervisor.tailProcessLog
 * supervisor.tailProcessStderrLog
 * supervisor.tailProcessStdoutLog
 * system.listMethods
 * system.methodHelp
 * system.methodSignature
 * system.multicall
 * <p>
 * Created by tao.zeng on 2021/6/22.
 */
public interface Constants {
    /**
     * The constant FATAL.
     */
    int FATAL = 1;
    /**
     * The constant RUNNING.
     */
    int RUNNING = 2;
    /**
     * The constant RESTARTING.
     */
    int RESTARTING = 0;
    /**
     * The constant SHUTDOWN.
     */
    int SHUTDOWN = -1;
    /**
     * The constant SYSTEM_LIST_METHODS.
     */

    String SYSTEM_LIST_METHODS = "system.listMethods";
    /**
     * The constant SYSTEM_METHOD_HELP.
     */
    String SYSTEM_METHOD_HELP = "system.methodHelp";
    /**
     * The constant SYSTEM_METHOD_SIGNATURE.
     */
    String SYSTEM_METHOD_SIGNATURE = "system.methodSignature";
    /**
     * The constant SYSTEM_MULTI_CALL.
     */
    String SYSTEM_MULTI_CALL = "system.multicall";

    // PROCESS
    /**
     * The constant _GET_PID.
     */
    String _GET_PID = "getPID";
    /**
     * The constant _GET_API_VERSION.
     */
    String _GET_API_VERSION = "getAPIVersion";
    /**
     * The constant _GET_SUPERVISOR_VERSION.
     */
    String _GET_SUPERVISOR_VERSION = "getSupervisorVersion";
    /**
     * The constant _GET_IDENTIFICATION.
     */
    String _GET_IDENTIFICATION = "getIdentification";
    /**
     * The constant _GET_STATE.
     */
    String _GET_STATE = "getState";
    /**
     * The constant _READ_LOG.
     */
    String _READ_LOG = "readLog";
    /**
     * The constant _CLEAR_LOG.
     */
    String _CLEAR_LOG = "clearLog";
    /**
     * The constant _SHUTDOWN.
     */
    String _SHUTDOWN = "shutdown";
    /**
     * The constant _RESTART.
     */
    String _RESTART = "restart";
    /**
     * The constant _GET_PROCESS_INFO.
     */
    String _GET_PROCESS_INFO = "getProcessInfo";
    /**
     * The constant _GET_ALL_PROCESS_INFO.
     */
    String _GET_ALL_PROCESS_INFO = "getAllProcessInfo";
    /**
     * The constant _START_PROCESS.
     */
    String _START_PROCESS = "startProcess";
    /**
     * The constant _START_ALL_PROCESSES.
     */
    String _START_ALL_PROCESSES = "startAllProcesses";
    /**
     * The constant _START_PROCESSES_GROUP.
     */
    String _START_PROCESSES_GROUP = "startProcessGroup";
    /**
     * The constant _STOP_PROCESS.
     */
    String _STOP_PROCESS = "stopProcess";
    /**
     * The constant _STOP_PROCESSES_GROUP.
     */
    String _STOP_PROCESSES_GROUP = "stopProcessGroup";
    /**
     * The constant _STOP_ALL_PROCESSES.
     */
    String _STOP_ALL_PROCESSES = "stopAllProcesses";
    /**
     * The constant _SIGNAL_PROCESS.
     */
    String _SIGNAL_PROCESS = "signalProcess";
    /**
     * The constant _SIGNAL_PROCESSES_GROUP.
     */
    String _SIGNAL_PROCESSES_GROUP = "signalProcessGroup";
    /**
     * The constant _SIGNAL_ALL_PROCESSES.
     */
    String _SIGNAL_ALL_PROCESSES = "signalAllProcesses";
}
