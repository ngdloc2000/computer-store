package com.cdtn.computerstore.enums;

import com.cdtn.computerstore.exception.StoreException;

import java.util.Objects;
import java.util.stream.Stream;

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

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Status.values())
                        .map(Status::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Category status not found with value " + value));
            }
            throw new StoreException("Category status is null");
        }
    }
}
