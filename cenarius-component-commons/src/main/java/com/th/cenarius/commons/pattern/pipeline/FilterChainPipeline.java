package com.th.cenarius.commons.pattern.pipeline;

import com.th.cenarius.commons.pattern.pipeline.filter.BizFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.DefaultFilterChain;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@Slf4j
public class FilterChainPipeline<T extends BizFilter> {

    private DefaultFilterChain head;

    private DefaultFilterChain tail;

    public FilterChainPipeline(String desc) {
        log.info("Register " + desc + " Pipeline");
    }

    public FilterChainPipeline addFilter(T filter) {
        DefaultFilterChain newChain = new DefaultFilterChain(filter);
        if (head == null) {
            this.head = newChain;
            this.tail = newChain;
        } else {
            this.tail.setNext(newChain);
            this.tail = newChain;
        }

        return this;
    }

    public FilterChainPipeline addFilter(String desc, T filter) {
        log.info("Add filter :{}, desc: {}", filter.getClass().getSimpleName(), desc);
        return addFilter(filter);
    }

    public DefaultFilterChain getFilterChain() {
        return this.head;
    }
}
