package com.th.cenarius.web.message;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
public class TxMessageConst {
    public static final String ROCKETMQ_TOPIC = "rocketmq_TOPIC";

    public static final String MESSAGE_TYPE = "message_type";

    public static Message buildTxMessage(Object message) {
        return MessageBuilder
                .withPayload(message)
                .setHeader(TxMessageConst.MESSAGE_TYPE, message.getClass().getName())
                .build();
    }
}
