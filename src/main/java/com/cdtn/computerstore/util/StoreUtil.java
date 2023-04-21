package com.cdtn.computerstore.util;

import java.util.Objects;

public class StoreUtil {

    /**
     * Tính phần trăm giảm giá của sản phẩm
     * @param retailPrice
     * @param latestPrice
     * @return
     */
    public static Double calculateDiscountProduct(Long retailPrice, Long latestPrice) {

        if (Objects.isNull(retailPrice) || Objects.isNull(latestPrice)) {
            return null;
        }

        double discount = Math.abs((double) (latestPrice - retailPrice) / (double) retailPrice * 100);

        return roundToTwoDecimalPlaces(discount);
    }

    /**
     * Lấy 2 số sau dấu phẩy của số thập phân
     * @param number
     * @return
     */
    public static double roundToTwoDecimalPlaces(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
