package com.monitor.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CaptureDto {
    private String id;
    private String caputureName;
}
