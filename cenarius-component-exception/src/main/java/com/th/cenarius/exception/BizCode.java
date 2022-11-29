package com.th.cenarius.exception;

/**
 * @Author: Aaron
 * @Date: 2022/11/23
 */
public interface BizCode {
    /**
     * 获取错误码枚举值
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 获取错误详细信息
     *
     * @return 错误消息
     */
    String getMessage();

}
