package com.th.cenarius.web.common.pipeline;

/**
 * @Author: Aaron
 * @Date: 2022/11/30
 */
public interface ContextHandler<T extends PipelineContext> {

    /**
     * Handle context
     *
     * @param context context
     * @return result
     */
    boolean handle(T context);
}
