# Discount System: Design Pattern & Testability Evaluation

This project implements a discount calculation system designed to demonstrate the empirical benefits of using Design Patterns over a simple, monolithic implementation. The primary quality attribute evaluated is **Testability**, measured through **Cyclomatic Complexity (CC)**.

---

## 📂 Project Structure & Pattern Mapping

The following files represent the two different design solutions (Simple & Pattern-based):

| File | Design Pattern | Role in Evaluation |
| :--- | :--- | :--- |
| **`DiscountCalculator.java`** | **None** | The baseline "monolithic" solution. High coupling and high Cyclomatic Complexity ($CC=5$). |
| **`StrategyPattern.java`** | **Strategy Pattern** | Isolates calculation algorithms into independent classes ($CC=1$). |
| **`Decorator.java`** | **Decorator Pattern** | Handles stackable price components (Tax, VIP) without modifying base classes ($CC=1$). |
| **`DiscountFactory.java`** | **Factory Pattern** | Separates selection logic ($CC=4$) from business logic. |
| **`DiscountHandler.java`** | **Chain of Responsibility** | Decouples conditional processing into a chain of handlers ($CC=3$). |
| **`Main.java`** | **Evaluation Runner** | Provides a simulation of unit tests to prove behavioral isolation. |

## 🛠 Empirical Evaluation (Testability)
The evaluation uses **McCabe’s Cyclomatic Complexity** $(V(G) = P + 1)$ to prove that:
1. Simple creates a single unit with a complexity of 5, requiring combinatorial test cases.
2. Pattern-based breaks logic into smaller units with minimum complexities of 1, allowing for targeted unit testing and behavioral isolation.

---

## 📊 UML Diagram
The architectural relationship between these patterns and the baseline solution is documented in the following diagram:

**[View UML Diagram (Draw.io)](https://drive.google.com/file/d/1DN9UTTTo1ApRJnfbzv0alv8R2ODLiS9o/view?usp=sharing)** 

---

## 🚀 Execution Guide

This project uses Maven for dependency management and execution.

### 1. Prerequisites
* **Java SDK 17** or higher.
* **Apache Maven** installed.

### 2. Running the Evaluation
To run the `Main.java` file and see the empirical proof of testability in the console, use the following command:

```bash
mvn exec:java -Dexec.mainClass="com.example.discount.Main"
```

