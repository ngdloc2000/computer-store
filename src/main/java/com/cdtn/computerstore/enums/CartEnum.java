package com.cdtn.computerstore.enums;

public class CartEnum {

    public enum Status {
        INACTIVE(0),
        ACTIVE(1),
        ORDERED(2);

        private final Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
