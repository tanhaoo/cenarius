package com.th.cenarius.commons.pattern.pipeline.filter.selector;

import java.util.List;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public interface FilterSelector {

    boolean matchFilter(String classSimpleName);

    List<String> getFilterNames();
}
