package com.th.cenaruis.exception;

/**
 * @Author: Aaron
 * @Date: 2022/11/23
 */
public class BizException extends RuntimeException {

    private final BizCode errorCode;

    private String customMsg = null;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     */
    public BizException(BizCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param customMsg 自定义详细错误信息
     */
    public BizException(BizCode errorCode, String customMsg) {
        this.errorCode = errorCode;
        this.customMsg = customMsg;
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public String getCode() {
        return this.errorCode.getCode();
    }

    /**
     * 获取错误原因
     *
     * @return 错误原因
     */
    @Override
    public String getMessage() {
        return this.errorCode.getMessage();
    }

    /**
     * 获取自定义错误信息
     *
     * @return 自定义错误信息
     */
    public String getCustomMsg() {
        return customMsg;
    }
}
