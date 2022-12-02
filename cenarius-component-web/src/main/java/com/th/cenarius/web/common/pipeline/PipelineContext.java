package com.th.cenarius.web.common.pipeline;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2022/11/30
 */
@Getter
@Setter
public abstract class PipelineContext {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String errorMsg;

    public String getSimpleName() {
        return this.getClass().getSimpleName();
    }
}
