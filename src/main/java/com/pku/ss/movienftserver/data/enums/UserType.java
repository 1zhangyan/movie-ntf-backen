/**
 * @(#)UserType.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.data.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

/**
 * @author zhangyan
 */
public enum UserType {

    AUDIENCEUSER(0,"普通用户"),
    THEATERUSER(1,"影院方"),
    MOVIEPRODUCERUSER(2,"制片方")
    ;

    private int value;

    private String name;

    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<UserType> findByInt(int value) {
        for (UserType item : UserType.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<UserType> findByString(String name) {
        for (UserType item : UserType.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    @JsonCreator
    public static UserType findNullableByString(String name) {
        for (UserType item : UserType.values()) {
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
