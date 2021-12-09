package com.monitor.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.dto.CaptureDto;
import com.monitor.entity.PCapture;
import com.monitor.mapper.PCaptureMapper;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PCaptureService extends ServiceImpl<PCaptureMapper, PCapture> {

    @Value("${monitor.url}")
    private String monitorUrl;
    public RestTemplate restTemplate = new RestTemplate();

    public void captureCamera(PCapture pCapture) {
        ResponseEntity<CaptureDto> response = restTemplate.postForEntity(monitorUrl.concat("platform/capture_camera"), new CaptureDto().setId(pCapture.getId()), CaptureDto.class);
        String caputureName = Optional.ofNullable(response).map(ResponseEntity::getBody).map(CaptureDto::getCaputureName).orElseThrow(IllegalReceiveException::new);
        this.save(pCapture.setCaputurename(caputureName));
    }
}

