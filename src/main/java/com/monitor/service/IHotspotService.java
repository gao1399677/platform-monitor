package com.monitor.service;

import com.monitor.model.HotSpotModel;
import com.monitor.po.Hotspot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SGao
 * @since 2021-08-13
 */
public interface IHotspotService extends IService<Hotspot> {

    List<Hotspot> getHotspotBySceneId(HotSpotModel hotSpotModel);
}
