/**
 * @(#)CopyrightType.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.data.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

/**
 * @author zhangyan
 */
public enum CopyrightType {
    COMPILERIGHT(0,"汇编权"),
    SCREENRIGHT(1,"放映权"),
    ADAPTATIONRIGHT(2,"改编权")
    ;

    private int value;

    private String name;

    CopyrightType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<CopyrightType> findByInt(int value) {
        for (CopyrightType item : CopyrightType.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<CopyrightType> findByString(String name) {
        for (CopyrightType item : CopyrightType.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    @JsonCreator
    public static CopyrightType findNullableByString(String name) {
        for (CopyrightType item : CopyrightType.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }

        return null;
    }

    @JsonValue
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.value;
    }
}
