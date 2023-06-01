package com.th.extension;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: Aaron
 * @Date: 2023/6/1
 */
@AllArgsConstructor
@EqualsAndHashCode(of = {"extensionPointClzName", "bizId"})
@ToString
public class ExtensionCoordinate {

    private final String extensionPointClzName;

    private final String bizId;


}
