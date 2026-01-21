package com.example.discount;

// Design Pattern: Strategy Pattern
interface DiscountStrategy {
    double apply(double amount);
}

class PercentageDiscount implements DiscountStrategy {
    private final double rate;

    public PercentageDiscount(double rate) {
        this.rate = rate;
    }

    public double apply(double amount) {
        return amount * (1 - rate);
    }
}

class FlatAmountDiscount implements DiscountStrategy {
    private final double discount;

    public FlatAmountDiscount(double amount) {
        this.discount = amount;
    }

    public double apply(double amount) {
        return Math.max(0, amount - discount);
    }
}

public class StrategyPattern {
    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy s) {
        this.strategy = s;
    }

    public double execute(double amount) {
        return strategy.apply(amount);
    }
}
