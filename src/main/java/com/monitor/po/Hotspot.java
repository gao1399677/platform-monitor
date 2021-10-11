package com.monitor.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author SGao
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("p_hotspot")
public class Hotspot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "hotspot_id", type = IdType.AUTO)
    private Integer hotspotId;

    private Integer sceneId;

    private String hotspotName;

    private String hotspotUrl;

    private BigDecimal hotspotAth;

    private BigDecimal hotspotAtv;

    private Integer hotspotDistorted;


}
