package com.th.cola.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Author: Aaron
 * @Date: 2023/7/25
 */
public class CreateTimeAndUpdateTimeAutoFillHandler implements MetaObjectHandler {
    private static final String FIELD_CREATE_TIME = "createTime";
    private static final String FIELD_UPDATE_TIME = "updateTime";

    public CreateTimeAndUpdateTimeAutoFillHandler() {
    }

    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.fillCreateTime(metaObject, now);
        this.fillUpdateTime(metaObject, now);
    }

    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.fillUpdateTime(metaObject, now);
    }

    private void fillCreateTime(MetaObject metaObject, LocalDateTime ts) {
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            this.setFieldValByName("createTime", ts, metaObject);
        }

    }

    private void fillUpdateTime(MetaObject metaObject, LocalDateTime ts) {
        this.setFieldValByName("updateTime", ts, metaObject);
    }

}
