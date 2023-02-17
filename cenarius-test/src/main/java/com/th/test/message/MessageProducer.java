package com.th.test.message;

import com.th.cenarius.web.message.TxMessageConst;
import com.th.cenarius.web.message.TxProcessContext;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
@Component
public class MessageProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendConfirmTxMessage() {

//        Message<ConfirmMessage> message = MessageBuilder
//                .withPayload(new ConfirmMessage(99998, "I am test Msg", "Log: test Msg"))
//                .setHeader(TxMessageConst.MESSAGE_TYPE, ConfirmMessage.class.getName())
//                .build();

        Message msg = TxMessageConst.buildTxMessage(new ConfirmMessage(99998, "I am Confirm test Msg", "Log: test Msg"));

        TxProcessContext context = new TxProcessContext(new Object());

        TransactionSendResult res = rocketMQTemplate.sendMessageInTransaction(
                "confirm_topic",
                msg,
                context);
    }
    public void sendSubmitTxMessage() {

        Message msg = TxMessageConst.buildTxMessage(new SubmitMessage(99999, "I am submit test Msg", "Submit Message Description：1"));

        TxProcessContext context = new TxProcessContext(new Object());

        TransactionSendResult res = rocketMQTemplate.sendMessageInTransaction(
                "submit_topic",
                msg,
                context);
    }


}
