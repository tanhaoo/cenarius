package com.th.test.app.event;

import com.th.cenarius.event.Event;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@AllArgsConstructor
public class OrderSubmitEventByUnknown implements Event<String> {

    private String unknown;

    @Override
    public String getTarget() {
        return unknown;
    }
}
