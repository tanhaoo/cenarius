package com.th.cenarius.codegen.context;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * @Author: Aaron
 * @Date: 2022/8/30
 */
public final class ProcessingEnvironmentHolder {

    public static final ThreadLocal<ProcessingEnvironment> ENVIRONMENT = new ThreadLocal<>();

    public static void setEnvironment(ProcessingEnvironment pe) {
        ENVIRONMENT.set(pe);
    }

    public static ProcessingEnvironment getEnvironment() {
        return ENVIRONMENT.get();
    }
}
