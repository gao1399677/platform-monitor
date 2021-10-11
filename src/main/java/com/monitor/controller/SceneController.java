package com.monitor.controller;


import com.monitor.pano.service.SceneService;
import com.monitor.vo.Result;
import com.monitor.pano.model.SceneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SGao
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/scene")
@CrossOrigin(maxAge = 3600)
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @PostMapping("/createScene")
    public Result<String> createScene(SceneModel sceneModel,
                                      @RequestParam("file") MultipartFile multipartFile) {
        sceneModel.setSceneName(sceneModel.getSceneName());
        sceneModel.setProjectId(sceneModel.getProjectId());
        if (multipartFile.isEmpty()) {
            return Result.failed().msg("文件不存在");
        }
        sceneModel.setMultipartFile(multipartFile);
        sceneService.createScene(sceneModel);
        return Result.success();
    }

    @PostMapping("/deleteScene")
    public Result<String> deleteScene(@RequestBody SceneModel sceneModel) throws Exception {
        sceneService.deleteScene(sceneModel.getSceneId());
        return Result.success();
    }

}

