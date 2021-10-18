package com.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_capture")
public class PCapture implements Serializable {
    @TableId(value = "capture_id", type = IdType.AUTO)
    private Long captureId;

    @TableField(value = "camera_id")
    private Long cameraId;

    @TableField(value = "caputureName")
    private String caputurename;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_CAPTURE_ID = "capture_id";

    public static final String COL_CAMERA_ID = "camera_id";

    public static final String COL_CAPUTURENAME = "caputureName";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}