package com.cdtn.computerstore.enums.product;

public enum StatusProductEnum {
    ACTIVE(1),
    INACTIVE(0);

    private final Integer value;

    StatusProductEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
