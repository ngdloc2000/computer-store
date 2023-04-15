package com.cdtn.computerstore.util;

import com.cdtn.computerstore.exception.StoreException;

public class QueryUtil {

    public static String checkOrderSearchProductByAdmin(String order) {

        return switch (order) {
            case "productId" -> "productId ";
            case "productName" -> "productName ";
            case "price" -> "price ";
            case "quantity" -> "quantity ";
            case "sold" -> "sold ";
            case "warranty" -> "warranty ";
            case "createAt" -> "createAt ";
            default -> throw new StoreException("Order query not match");
        };
    }
}
