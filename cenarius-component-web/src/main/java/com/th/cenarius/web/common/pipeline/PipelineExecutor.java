package com.th.cenarius.web.common.pipeline;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/30
 */
@Slf4j
public class PipelineExecutor implements ApplicationContextAware, InitializingBean {

    private Map<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> contextMaps;

    private Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? super PipelineContext>>> routeMaps;

    private ApplicationContext applicationContext;

    public PipelineExecutor(Map<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> contextMaps) {
        this.contextMaps = contextMaps;
    }

    private Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? super PipelineContext>>> generateRouteMaps(Map<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> contextClassMaps) {
        return contextClassMaps.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, this::getHandlerBean));
    }

    public List<? extends ContextHandler<? super PipelineContext>> getHandlerBean(Map.Entry<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> entry) {
        return entry.getValue()
                .stream()
                .map(item -> (ContextHandler<PipelineContext>) applicationContext.getBean(item))
                .collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        routeMaps = generateRouteMaps(contextMaps);
    }

    public boolean acceptSync(PipelineContext context) {
        Assert.notNull(context, "Pipeline context is required");

        Class<? extends PipelineContext> contextType = context.getClass();
        List<? extends ContextHandler<? super PipelineContext>> contextHandlers = routeMaps.get(contextType);

        if (CollectionUtils.isEmpty(contextHandlers)) {
            log.error("Pipeline {} is null ", contextType);
            return false;
        }

        boolean lastSuccess = true;

        for (ContextHandler<? super PipelineContext> handler : contextHandlers) {
            try {
                lastSuccess = handler.handle(context);
            } catch (Throwable ex) {
                lastSuccess = false;
                log.error("[{}] occur error，handler={}", context.getSimpleName(), handler.getClass().getSimpleName(), ex);
            }

            if (!lastSuccess) {
                break;
            }
        }

        return lastSuccess;
    }

    @Resource
    private ThreadPoolTaskExecutor pipelineThreadPool;

    public void acceptAsync(PipelineContext context, BiConsumer<PipelineContext, Boolean> callback) {
        pipelineThreadPool.execute(() -> {
            boolean success = acceptSync(context);

            if (callback != null) {
                callback.accept(context, success);
            }
        });
    }

}
