package com.example.discount;

public class DiscountCalculator {
    public double calculateFinalPrice(double amount, String type, boolean isVip, boolean isHoliday) {
        double total = amount;

        if (type.equals("SUMMER"))
            total *= 0.90;
        else if (type.equals("WINTER"))
            total *= 0.80;

        if (isVip)
            total -= 10;
        if (isHoliday)
            total *= 0.95;

        return total;
    }
}
