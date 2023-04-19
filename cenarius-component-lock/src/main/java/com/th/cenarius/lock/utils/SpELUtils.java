package com.th.cenarius.lock.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
public class SpELUtils {

    /**
     * @param expressionString EL表达式
     * @param method           被执行方法
     * @param args             参数
     * @param c                期望返回类型
     * @param <T>
     * @return 通过反射拿到对象实例名，把它作为key add进StandardEvaluationContext，parseExpression就能解析
     * 例：
     * expressionString: #order.codes
     * method: submit(Order order)
     * <p>
     * 如果是想用Spring容器中的bean，可以用 #root.beanName 或 @beanName 来获取
     */
    public static <T> T parseExpression(String expressionString, Method method, Object[] args, Class<T> c) {
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
//        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArr = discoverer.getParameterNames(method);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < paramNameArr.length; ++i) {
            context.setVariable(paramNameArr[i], args[i]);
        }

        return parser.parseExpression(expressionString).getValue(context, c);
    }

    /**
     * 解析表达式
     * 传入bean的上下文环境，用 @beanName 就可以拿到bean
     * 传入上下文对象 rootObject, [property, doSomething()]
     * #property 是去EvaluationContext里取
     *
     * @param expressionString
     * @param c
     * @return
     */
    public static <T> T parseExpression(String expressionString, ApplicationContext context, Object rootObject, Class<T> c) {
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(context));
        evaluationContext.setRootObject(rootObject);

        return parser.parseExpression(expressionString).getValue(evaluationContext, c);
    }

    /**
     * 仅使用表达式、上下文
     *
     * @param expressionString 解析表达式
     * @param context          上下文环境
     * @param c                期望类型
     * @return 结果
     */
    public static <T> T parseExpression(String expressionString, ApplicationContext context, Class<T> c) {
        return parseExpression(expressionString, context, null, c);
    }

    /**
     * 仅使用表达式
     *
     * @param expressionString 解析表达式
     * @param c                期望类型
     * @return 结果
     */
    public static <T> T parseExpression(String expressionString, Class<T> c) {
        return parseExpression(expressionString, null, c);
    }

}
