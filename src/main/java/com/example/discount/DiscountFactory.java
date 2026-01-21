package com.example.discount;

// Design Pattern: Factory Method Pattern
public class DiscountFactory {
    public static DiscountStrategy getStrategyBySeason(String season) {
        return switch (season.toUpperCase()) {
            case "SUMMER" -> new PercentageDiscount(0.10);
            case "WINTER" -> new PercentageDiscount(0.20);
            case "CLEARANCE" -> new FlatAmountDiscount(50.0);
            default -> new PercentageDiscount(0.0);
        };
    }
}
