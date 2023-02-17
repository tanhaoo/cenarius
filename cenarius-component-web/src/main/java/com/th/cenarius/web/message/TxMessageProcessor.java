package com.th.cenarius.web.message;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;

import java.lang.reflect.ParameterizedType;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
public interface TxMessageProcessor<T> {

    /**
     * Get Tx message type
     *
     * @return {@link Object}
     */
    default Class<T> getMessageType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    /**
     * Get  topic of the corresponding transaction message
     *
     * @return Topic
     */
    String getTopic();

    /**
     * Execute local transaction.
     *
     * @param message Message object {@link Object}
     * @param context Arg of message
     * @return {@link RocketMQLocalTransactionState}
     */
    RocketMQLocalTransactionState executeLocalTransaction(T message, TxProcessContext context);

    /**
     * Check local transaction
     *
     * @param message Message object {@link Object}
     * @return {@link RocketMQLocalTransactionState}
     */
    RocketMQLocalTransactionState checkLocalTransaction(T message);

}
