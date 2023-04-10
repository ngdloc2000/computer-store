package com.cdtn.computerstore.enums;

public class CategoryEnum {

    public enum Status {
        ACTIVE(1),
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
