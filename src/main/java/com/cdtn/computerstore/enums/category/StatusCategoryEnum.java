package com.cdtn.computerstore.enums.category;

public enum StatusCategoryEnum {
    ACTIVE(1),
    INACTIVE(0);

    private final Integer value;

    StatusCategoryEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
