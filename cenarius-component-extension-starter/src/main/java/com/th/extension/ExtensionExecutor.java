package com.th.extension;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/6/1
 */
@Component
public class ExtensionExecutor {

    @Resource
    private ExtensionRepository extensionRepository;

    public <T> void executeVoid(Class<T> targetClz, String bizId, Consumer<T> exeFunction) {
        T targetBean = locate(targetClz, bizId);
        exeFunction.accept(targetBean);
    }

    public <T, R> R execute(Class<T> targetClz, String bizId, Function<T, R> exeFunction) {
        T targetBean = locate(targetClz, bizId);
        return exeFunction.apply(targetBean);
    }


    private <C> C locate(Class<C> extClz, String bizId) {
        C res = (C) extensionRepository.getExtMaps().get(new ExtensionCoordinate(extClz.getName(), bizId));

        if (Objects.isNull(res)) {
            String errMessage = "Can not find extension with ExtensionPoint: " +
                    extClz + " BizScenario: " + bizId;
            throw new RuntimeException(errMessage);
        }

        return res;
    }
}
