package com.iogogogo.supervisord.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Created by tao.zeng on 2021/6/22.
 * <p>
 * http://supervisord.org/api.html#supervisor.rpcinterface.SupervisorNamespaceRPCInterface.getProcessInfo
 */
@Data
@ToString
public class SupervisordProcess {

    /**
     * The Name.
     */
    private String name;
    /**
     * The Group.
     */
    private String group;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Start.
     */
    private long start;
    /**
     * The Stop.
     */
    private long stop;
    /**
     * The Now.
     */
    private long now;
    /**
     * The Status.
     */
    private int status;
    /**
     * The Statename.
     */
    private String statename;
    /**
     * The Spawnerr.
     */
    private String spawnerr;
    /**
     * The Exitstatus.
     */
    private int exitstatus;
    /**
     * The Logfile.
     */
    private String logfile;
    /**
     * The Stdout logfile.
     */
    private String stdout_logfile;
    /**
     * The Stderr logfile.
     */
    private String stderr_logfile;
    /**
     * The Pid.
     */
    private int pid;

    /**
     * Instantiates a new Supervisord process.
     */
    public SupervisordProcess() {
    }

    /**
     * Instantiates a new Process.
     *
     * @param name the name
     */
    public SupervisordProcess(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Process.
     *
     * @param name  the name
     * @param group the group
     */
    public SupervisordProcess(String name, String group) {
        this(name);
        this.group = group;
    }
}
