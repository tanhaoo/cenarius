package com.th.extension.order;

import com.th.extension.Extension;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/31
 */
@Component
@Slf4j
@Extension(bizId = "Local")
public class OrderLocalServiceImpl implements IOrderServiceExtTp, IOrderServiceExtPt {
    @Override
    public BigDecimal summary() {
        log.info(this.getClass().getSimpleName() + " Execute");
        return new BigDecimal("10");
    }

    @Override
    public void say() {
        log.info(this.getClass().getSimpleName() + " Execute Say");
    }
}
