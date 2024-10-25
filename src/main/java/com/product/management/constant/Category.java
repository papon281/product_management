package com.product.management.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Category {
    ELECTRONICS(1),
    CLOTHING(2),
    BOOKS(3),
    FOOD(4),
    ;

    private final int type;

    Category(int type) {
        this.type = type;
    }

    public static Category fromValue(int value) {
        return switch (value) {
            case 1 -> ELECTRONICS;
            case 2 -> CLOTHING;
            case 3 -> BOOKS;
            case 4 -> FOOD;
            default -> null;
        };
    }

    @JsonCreator
    public static Category fromName(String name) {
        return switch (StringUtils.upperCase(name)) {
            case "ELECTRONICS" -> ELECTRONICS;
            case "CLOTHING" -> CLOTHING;
            case "BOOKS" -> BOOKS;
            case "FOOD" -> FOOD;
            default -> null;
        };
    }

    @JsonValue
    public String getName() {
        return toString();
    }

}