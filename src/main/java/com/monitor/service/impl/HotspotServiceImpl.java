package com.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.monitor.mapper.HotspotMapper;
import com.monitor.model.HotSpotModel;
import com.monitor.service.IHotspotService;
import com.monitor.po.Hotspot;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SGao
 * @since 2021-08-13
 */
@Service
public class HotspotServiceImpl extends ServiceImpl<HotspotMapper, Hotspot> implements IHotspotService {

    @Override
    public List<Hotspot> getHotspotBySceneId(HotSpotModel hotSpotModel) {
        LambdaQueryWrapper<Hotspot> wrapper = Wrappers.lambdaQuery(Hotspot.class)
                .eq(Hotspot::getSceneId, hotSpotModel.getSceneId());
        return this.baseMapper.selectList(wrapper);
    }
}
