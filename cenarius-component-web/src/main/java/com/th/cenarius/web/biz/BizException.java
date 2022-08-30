package com.th.cenarius.web.biz;

import lombok.Getter;

/**
 * @Author: Aaron
 * @Date: 2022/3/11
 */
public class BizException {

    /**
     * 获取错误码信息
     */
    @Getter
    private final BizCode errorCode;

    public BizException(BizCode errorCode) {
        this.errorCode = errorCode;
    }
}
