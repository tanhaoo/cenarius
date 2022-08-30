package com.th.cenarius.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: Aaron
 * @Date: 2022/3/2
 */
public interface EventListener<E extends Event> {

    /**
     * 事件处理入口
     *
     * @param event 事件
     */
    void onEvent(E event);

    /**
     * 判定是否支持处理当前事件。
     * 默认实现:
     * 判定事件参数的类型是否与处理器泛型参数指定的类型一致，如果一致则返回true，否则为false。
     *
     * @param event 需要处理的事件
     * @return true:是，false:否
     */
    default boolean support(Event event) {
        Type[] interfaces = this.getClass().getGenericInterfaces();
        for (Type type : interfaces) {
            if (type instanceof ParameterizedType) {
                Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
                return event.getClass().equals(actualType);
            }
        }
        return false;
    }
}
