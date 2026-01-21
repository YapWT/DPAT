package com.example.discount;

// Design Pattern: Chain of Responsibility Pattern
public abstract class DiscountHandler {
    protected DiscountHandler next;

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    public abstract double process(double amount, String promoCode);
}

class BlackFridayHandler extends DiscountHandler {
    public double process(double amount, String promoCode) {
        if ("BLACK_FRIDAY".equals(promoCode))
            return amount * 0.50;
        return (next != null) ? next.process(amount, promoCode) : amount;
    }
}

class NewUserHandler extends DiscountHandler {
    public double process(double amount, String promoCode) {
        if ("WELCOME".equals(promoCode))
            return amount * 0.90;
        return (next != null) ? next.process(amount, promoCode) : amount;
    }
}
