package com.iogogogo.supervisord.api;

import com.iogogogo.supervisord.base.BaseResult;
import com.iogogogo.supervisord.base.ResponseWrapper;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import com.iogogogo.supervisord.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tao.zeng on 2021/6/25.
 */
@Slf4j
@RestController
@RequestMapping("/rpc/" + SupervisordProperties.SUPERVISORD_PREFIX)
public class SupervisordRpcApi implements BaseResult {

    private final Supervisord supervisord;

    /**
     * Instantiates a new Supervisord api.
     *
     * @param supervisord the supervisord
     */
    public SupervisordRpcApi(Supervisord supervisord) {
        this.supervisord = supervisord;
    }

    /**
     * Proc flux.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the flux
     * @throws SupervisordException the supervisord exception
     */
    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseWrapper<ListUtils.PageWrapper<SupervisordProcess>> proc(@PathVariable("pageNo") int pageNo,
                                                                           @PathVariable("pageSize") int pageSize) throws SupervisordException {
        List<SupervisordProcess> processList = supervisord.getAllProcessInfo();
        ListUtils.PageWrapper<SupervisordProcess> pageWrapper = ListUtils.partition(pageNo, pageSize, processList);
        return ok(pageWrapper);
    }

    /**
     * Proc mono.
     *
     * @param procName the proc name
     * @return the mono
     * @throws SupervisordException the supervisord exception
     */
    @GetMapping("/{procName}")
    public ResponseWrapper<SupervisordProcess> proc(@PathVariable("procName") String procName) throws SupervisordException {
        return ok(supervisord.getProcessInfo(procName));
    }

    /**
     * Start proc mono.
     *
     * @param procName the proc name
     * @return the mono
     * @throws SupervisordException the supervisord exception
     */
    @GetMapping("/start/{procName}")
    public ResponseWrapper<Boolean> startProc(@PathVariable("procName") String procName) throws SupervisordException {
        return ok(supervisord.startProcess(procName, false));
    }

    /**
     * Stop proc mono.
     *
     * @param procName the proc name
     * @return the mono
     * @throws SupervisordException the supervisord exception
     */
    @GetMapping("/stop/{procName}")
    public ResponseWrapper<Boolean> stopProc(@PathVariable("procName") String procName) throws SupervisordException {
        return ok(supervisord.stopProcess(procName, false));
    }
}
