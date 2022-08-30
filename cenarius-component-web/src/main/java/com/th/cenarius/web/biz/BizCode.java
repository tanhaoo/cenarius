package com.th.cenarius.web.biz;

/**
 * @Author: Aaron
 * @Date: 2022/3/11
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
