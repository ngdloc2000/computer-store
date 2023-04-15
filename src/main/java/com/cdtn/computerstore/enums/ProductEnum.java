package com.cdtn.computerstore.enums;

import com.cdtn.computerstore.dto.enums.SelectOptionResponse;
import com.cdtn.computerstore.exception.StoreException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductEnum {

    public enum Brand {
        DELL(1, "Dell"),
        APPLE(2, "Apple"),
        ASUS(3, "Asus"),
        ACER(4, "Acer"),
        MSI(5, "MSI"),
        LG(6, "LG"),
        LENOVO(7, "Lenovo"),
        HUAWEI(8, "Huawei"),
        HP(9, "HP"),
        SAMSUNG(10, "Samsung"),
        PANASONIC(11, "Panasonic"),
        TOSHIBA(12, "Toshiba"),
        AMD(13, "AMD"),
        INTEL(14, "INTEL");

        private final Integer value;
        private final String name;

        Brand(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Brand.values())
                        .map(Brand::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Brand not found with value " + value));
            }
            throw new StoreException("Brand value is null");
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Brand.values())
                        .filter(brand -> brand.getValue().equals(value))
                        .map(Brand::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Brand not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(Brand.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }
    }

    public enum Color {
        RED(1, "Đỏ"),
        WHITE(2, "Trắng"),
        BLACK(3, "Đen"),
        BROWN(4, "Nâu");

        private final Integer value;
        private final String name;

        Color(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Color.values())
                        .map(Color::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Color not found with value " + value));
            }
            return null;
        }

        public static List<SelectOptionResponse> getList() {
            return Stream.of(Color.values())
                    .map(e -> new SelectOptionResponse(e.getValue(), e.getName()))
                    .collect(Collectors.toList());
        }

        public static String getNameByValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Color.values())
                        .filter(color -> color.getValue().equals(value))
                        .map(Color::getName)
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Color not found with value " + value));
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public enum Featured {
        YES(1),
        NO(0);

        private final Integer value;

        Featured(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static Integer checkValue(Integer value) {
            if (Objects.nonNull(value)) {
                return Stream.of(Featured.values())
                        .map(Featured::getValue)
                        .filter(eValue -> eValue.equals(value))
                        .findFirst()
                        .orElseThrow(() -> new StoreException("Featured not found with value " + value));
            }
            throw new StoreException("Featured value is null");
        }
    }

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
