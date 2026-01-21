package com.example.discount;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    private static String FilePath = "src/main/java/com/example/discount/";

    public static void main(String[] args) {
        testSimpleImplementation();
        testChainOfResponsibility();
        testStrategyPattern();
        testFactoryPattern();
        testDecoratorPattern();
    }

    /**
     * Simple Implementation
     * Proof of Poor Testability: To test just the "Summer" discount,
     * we are forced to provide values for VIP and Holiday, even if they aren't
     * relevant.
     */
    private static void testSimpleImplementation() {
        System.out.println("--- Testing Simple Implementation ---");
        DiscountCalculator calc = new DiscountCalculator();

        // Problem: We want to test Summer (10% off), but we must navigate
        // the logic for VIP and Holiday as well.
        double result = calc.calculateFinalPrice(100.0, "SUMMER", false, false);

        System.out.println("Base $100 + SUMMER (No VIP, No Holiday) = Expected: 90.0, Result: " + result);
        System.out.println("Observation: High coupling. Testing one branch requires managing all parameters.");

        calculateCyclomaticComplexity("DiscountCalculator");
    }

    /**
     * Strategy Pattern
     * Proof of High Testability: We test the Summer logic class directly.
     * No need to worry about VIP or Holiday flags.
     */
    private static void testStrategyPattern() {
        System.out.println("--- Testing Strategy Pattern ---");

        // We test the class in total isolation
        DiscountStrategy summer = new PercentageDiscount(0.10);
        double result = summer.apply(100.0);

        System.out.println("Isolated Summer Strategy (10%) = Expected: 90.0, Result: " + result);
        System.out.println("Observation: High Isolation. CC is 1. Zero dependence on other discount types.");

        calculateCyclomaticComplexity("StrategyPattern");
    }

    /**
     * Decorator Pattern
     * Proof of Behavioral Isolation: We can test the Tax logic independently
     * by wrapping a simple BasePrice.
     */
    private static void testDecoratorPattern() {
        System.out.println("--- Testing Decorator Pattern ---");

        PriceComponent base = new BasePrice(100.0);
        PriceComponent taxed = new TaxDecorator(base); // Adds 5%

        System.out.println("Base $100 + Tax Decorator = Expected: 105.0, Result: " + taxed.getPrice());

        // We can test multi-stacking without modifying the original class
        PriceComponent vipTaxed = new VipMemberDecorator(taxed); // Subtracts $20
        System.out.println("Base $100 + Tax + VIP = Expected: 85.0, Result: " + vipTaxed.getPrice());
        System.out.println("Observation: Granular testing. Each decorator's logic is verified separately.");

        calculateCyclomaticComplexity("Decorator");
    }

    /**
     * Chain of Responsibility
     * Proof of Request Flow Verification: We can test if a handler correctly
     * ignores a code it doesn't handle and passes it to the next.
     */
    private static void testChainOfResponsibility() {
        System.out.println("--- Testing Chain of Responsibility ---");

        DiscountHandler bfHandler = new BlackFridayHandler();
        DiscountHandler welcomeHandler = new NewUserHandler();
        bfHandler.setNext(welcomeHandler);

        // Test: Does BF handler pass the "WELCOME" code to the next in chain?
        double result = bfHandler.process(100.0, "WELCOME");

        System.out.println("Chain processing 'WELCOME' code = Expected: 90.0, Result: " + result);
        System.out.println("Observation: We can test delegation logic independently of the calculation.");

        calculateCyclomaticComplexity("DiscountHandler");
    }

    /**
     * Factory Method
     * Proof of Separation of Concerns: We test the 'Selection Logic'
     * separate from the 'Calculation Logic'.
     */
    private static void testFactoryPattern() {
        System.out.println("--- Testing Factory Pattern ---");

        DiscountStrategy strategy = DiscountFactory.getStrategyBySeason("WINTER");

        // Check if the factory returned the correct object type
        boolean isCorrectType = strategy instanceof PercentageDiscount;

        System.out.println("Factory returned PercentageDiscount for 'WINTER': " + isCorrectType);
        System.out.println("Observation: Simplified assertions. We only need to verify the instance type.");

        calculateCyclomaticComplexity("DiscountFactory");
    }

    private static void calculateCyclomaticComplexity(String filename) {
        try {
            String source = Files.readString(Path.of(FilePath + filename + ".java"));
            long cc = source.lines()
                    .filter(line -> line.matches(".*\\b(if|else if|for|while|case|catch)\\b.*"))
                    .count() + 1; // +1 for default path

            System.out.println("Cyclomatic Complexity: " + cc + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// -- Testing Simple Implementation ---
// Base $100 + SUMMER (No VIP, No Holiday) = Expected: 90.0, Result: 90.0
// Observation: High coupling. Testing one branch requires managing all parameters.
// Cyclomatic Complexity: 5
//
// --- Testing Chain of Responsibility ---
// Chain processing 'WELCOME' code = Expected: 90.0, Result: 90.0
// Observation: We can test delegation logic independently of the calculation.
// Cyclomatic Complexity: 3
//
// --- Testing Strategy Pattern ---
// Isolated Summer Strategy (10%) = Expected: 90.0, Result: 90.0
// Observation: High Isolation. CC is 1. Zero dependence on other discount types.
// Cyclomatic Complexity: 1
//
// --- Testing Factory Pattern ---
// Factory returned PercentageDiscount for 'WINTER': true
// Observation: Simplified assertions. We only need to verify the instance type.
// Cyclomatic Complexity: 4
//
// --- Testing Decorator Pattern ---
// Base $100 + Tax Decorator = Expected: 105.0, Result: 105.0
// Base $100 + Tax + VIP = Expected: 85.0, Result: 85.0
// Observation: Granular testing. Each decorator's logic is verified separately.
// Cyclomatic Complexity: 1
//
