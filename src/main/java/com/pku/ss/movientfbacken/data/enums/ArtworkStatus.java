/**
 * @(#)ArtworkStatus.java, 8月 15, 2021.
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
public enum ArtworkStatus {
    SELLING(0,"销售中"),
    SELLOUT(1,"售罄"),
    BIDDING(2,"竞拍中"),
    BIDDINGEND(3,"竞拍结束")
    ;

    private int value;

    private String name;

    ArtworkStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<ArtworkStatus> findByInt(int value) {
        for (ArtworkStatus item : ArtworkStatus.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<ArtworkStatus> findByString(String name) {
        for (ArtworkStatus item : ArtworkStatus.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    @JsonCreator
    public static ArtworkStatus findNullableByString(String name) {
        for (ArtworkStatus item : ArtworkStatus.values()) {
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
