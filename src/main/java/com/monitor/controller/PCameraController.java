package com.monitor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.monitor.entity.PCamera;
import com.monitor.service.PCameraService;
import com.monitor.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.Optional;

@RestController
public class PCameraController {
    @Autowired
    private PCameraService pCameraService;

    @PostMapping({"/register_camera"})
    public Result registerCamera(@RequestBody PCamera pCamera) {
        pCameraService.save(pCamera);
        return Result.success();
    }

    @PostMapping({"/edit_camera"})
    public Result editCamera(@RequestBody PCamera pCamera) {
        pCameraService.updateById(pCamera);
        return Result.success();
    }

    @PostMapping({"/delete_camera"})
    public Result deleteCamera(@RequestBody PCamera pCamera) {
        LambdaQueryWrapper<PCamera> wrapper = Wrappers.lambdaQuery(PCamera.class)
                .eq(PCamera::getId, pCamera.getId());
        int delete = pCameraService.getBaseMapper().delete(wrapper);
        Optional.of(delete).filter(d -> d == 1).orElseThrow(() -> new ValidationException("更新失败"));
        return Result.success();
    }

}
