package com.example.discount;

// Design Pattern: Decorator Pattern
interface PriceComponent {
    double getPrice();
}

class BasePrice implements PriceComponent {
    private final double amount;

    public BasePrice(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return amount;
    }
}

abstract class PriceDecorator implements PriceComponent {
    protected PriceComponent decoratedPrice;

    public PriceDecorator(PriceComponent p) {
        this.decoratedPrice = p;
    }
}

class VipMemberDecorator extends PriceDecorator {
    public VipMemberDecorator(PriceComponent p) {
        super(p);
    }

    public double getPrice() {
        return decoratedPrice.getPrice() - 20.0;
    } // Flat $20 off
}

class TaxDecorator extends PriceDecorator {
    public TaxDecorator(PriceComponent p) {
        super(p);
    }

    public double getPrice() {
        return decoratedPrice.getPrice() * 1.05;
    } // 5% Tax
}
