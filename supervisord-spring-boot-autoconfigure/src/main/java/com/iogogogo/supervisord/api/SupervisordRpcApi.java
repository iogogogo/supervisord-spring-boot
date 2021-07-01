package com.iogogogo.supervisord.api;

import com.iogogogo.supervisord.base.BaseResult;
import com.iogogogo.supervisord.base.ResponseWrapper;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rpc/" + SupervisordProperties.SUPERVISORD_PREFIX)
public class SupervisordRpcApi implements BaseResult {

    private final Supervisord supervisord;

    public SupervisordRpcApi(Supervisord supervisord) {
        this.supervisord = supervisord;
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseWrapper<ListUtils.PageWrapper<SupervisordProcess>> proc(@PathVariable("pageNo") int pageNo,
                                                                           @PathVariable("pageSize") int pageSize) throws SupervisordException, IOException {
        List<SupervisordProcess> processList = supervisord.getAllProcessInfo();
        ListUtils.PageWrapper<SupervisordProcess> pageWrapper = ListUtils.partition(pageNo, pageSize, processList);
        return ok(pageWrapper);
    }

    @GetMapping("/{procName}")
    public ResponseWrapper<SupervisordProcess> proc(@PathVariable("procName") String procName) throws SupervisordException, IOException {
        return ok(supervisord.getProcessInfo(procName));
    }

    @PutMapping("/{procName}/{action}")
    public ResponseWrapper<Boolean> status(@PathVariable("procName") String procName,
                                              @PathVariable("action") String action) throws SupervisordException, IOException {
        switch (action) {
            case "start":
                return ok(supervisord.startProcess(procName, false));
            case "stop":
                return ok(supervisord.stopProcess(procName, false));
            default:
                return result(400, "action range [start,stop]", null);
        }
    }
}
