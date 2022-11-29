package com.th.cenarius.web.advice.base.customize.invokeRecord;

import com.alibaba.fastjson.JSON;
import com.th.cenarius.web.advice.base.BaseMethodAdviceHandler;
import com.th.cenarius.web.annotation.InvokeRecordAnno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/28
 */
@Component
@Slf4j
public class InvokeRecordHandler extends BaseMethodAdviceHandler<Object> {

    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        long costTime = System.currentTimeMillis() - startTime;

        log.warn("\n{} 执行结束，耗时={}ms，入参={}, 出参={}",
                methodDesc, costTime,
                JSON.toJSONString(args, true),
                JSON.toJSONString(result, true));
    }

    @Override
    protected String getMethodDesc(ProceedingJoinPoint point) {
        Method targetMethod = getTargetMethod(point);
        InvokeRecordAnno anno = targetMethod.getAnnotation(InvokeRecordAnno.class);
        String description = anno.value();
        if (description == null) {
            description = super.getMethodDesc(point);
        }
        return description;
    }
}
