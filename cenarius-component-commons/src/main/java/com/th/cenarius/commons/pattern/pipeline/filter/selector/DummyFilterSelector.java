package com.th.cenarius.commons.pattern.pipeline.filter.selector;

import java.util.List;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public class DummyFilterSelector implements FilterSelector {
    @Override
    public boolean matchFilter(String filter) {
        return false;
    }

    @Override
    public List<String> getFilterNames() {
        return null;
    }
}
