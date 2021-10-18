package com.monitor.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.mapper.PCaptureMapper;
import com.monitor.entity.PCapture;

@Service
public class PCaptureService extends ServiceImpl<PCaptureMapper, PCapture> {

}

