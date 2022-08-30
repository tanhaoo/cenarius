package com.th.test.app.event.listener;

import com.th.cenarius.event.EventListener;
import com.th.test.app.event.OrderSubmitEventById;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Component
public class OrderSubmitEventListenerById implements EventListener<OrderSubmitEventById> {

    @Override
    public void onEvent(OrderSubmitEventById event) {
        System.err.println(event.getTarget());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1/0);
    }
}
