package com.cdtn.computerstore.enums;

public class CartEnum {

    public enum Status {
        ACTIVE(1),
        ORDERED(2),
        INACTIVE(0);

        private final Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
