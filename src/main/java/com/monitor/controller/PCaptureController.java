package com.monitor.controller;

import com.monitor.entity.PCapture;
import com.monitor.service.PCaptureService;
import com.monitor.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PCaptureController {
    @Autowired
    private PCaptureService pCaptureService;

    @PostMapping({"/capture_camera"})
    public Result captureCamera(@RequestBody PCapture pCapture) {
        pCaptureService.getBaseMapper().insert(pCapture);
        return Result.success();
    }

}
