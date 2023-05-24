package com.th.cenarius.commons.pattern.pipeline.filter.selector;

import com.google.common.collect.Lists;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@AllArgsConstructor
@NoArgsConstructor
public class LocalListBasedFilterSelector implements FilterSelector {

    private List<String> filterNames = Lists.newArrayList();

    @Override
    public boolean matchFilter(String classSimpleName) {
        return filterNames.stream().anyMatch(name -> name.equals(classSimpleName));
    }

    @Override
    public List<String> getFilterNames() {
        return filterNames;
    }

    public void addFilter(String filterName) {
        this.filterNames.add(filterName);
    }

    public void addFilters(List<String> filterNames) {
        this.filterNames.addAll(filterNames);
    }
}
