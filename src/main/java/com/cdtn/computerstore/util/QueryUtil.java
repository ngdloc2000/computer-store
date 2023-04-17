package com.cdtn.computerstore.util;

import com.cdtn.computerstore.exception.StoreException;

import java.util.List;

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

    public static String checkOrderSearchProductByClient(String order) {

        return switch (order) {
            case "productId" -> "productId ";
            case "price" -> "price ";
            case "sold" -> "sold ";
            case "warranty" -> "warranty ";
            case "createAt" -> "createAt ";
            default -> throw new StoreException("Order query not match");
        };
    }

    public static String createWhereQuery(List<String> whereList) {

        StringBuilder where = new StringBuilder();

        if (whereList.size() != 0) {
            where.append("where ");
            where.append(whereList.get(0));

            for (int i = 1; i < whereList.size(); i++) {
                where.append(" and ").append(whereList.get(i));
            }
        }

        return where.toString();
    }

    public static String createInConditionQuery(List<Integer> list) {

        StringBuilder where = new StringBuilder();

        if (list.size() != 0) {
            where.append(list.get(0));

            for (int i = 1; i < list.size(); i++) {
                where.append(", ").append(list.get(i));
            }

            where.append(")");
        }

        return where.toString();
    }
}
