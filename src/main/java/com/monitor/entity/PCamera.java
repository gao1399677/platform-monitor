package com.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_camera")
public class PCamera implements Serializable {
    @TableId(value = "camera_id", type = IdType.INPUT)
    private Integer cameraId;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "station")
    private String station;

    @TableField(value = "id")
    private String id;

    @TableField(value = "port")
    private String port;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_CAMERA_ID = "camera_id";

    public static final String COL_NAME = "name";

    public static final String COL_STATION = "station";

    public static final String COL_ID = "id";

    public static final String COL_PORT = "port";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}