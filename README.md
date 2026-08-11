# 🚀 Advanced Polynomial Calculator & Expression Converter (Java Swing)

A robust desktop application built in Java that combines **Graphical User Interfaces (Java Swing)** with core **Data Structures and Algorithms**, designed to perform complex operations on polynomials and logical expression conversions.

---

## 📌 Key Features & Technical Highlights

* **Custom Data Structures (No Built-in Shortcuts):**
  * **Linked List (`Node`, `PolynomialCalculator`):** Polynomials are dynamically represented, stored, and manipulated using linked lists to handle arbitrary degrees and sparse terms efficiently.
  * **Custom Stack (`Stack`):** Built from scratch using an `ArrayList` without relying on Java's built-in `Stack` class, used specifically for parsing and expression conversions.
* **Algebraic Operations on Polynomials:**
  * Addition, Subtraction, Multiplication, and **Polynomial Division** (returning both Quotient and Remainder).
  * Evaluation for a given value of $x$ (`compensationX`).
  * Automatic Derivation and **Root Finding** (handling linear, quadratic equations via Discriminant, and higher-degree polynomials using numerical methods like **Newton-Raphson** and integer root theorems).
* **Expression Conversion:**
  * Converts mathematical expressions between **Infix, Postfix, and Prefix** notations using custom parsing logic and operator precedence.
* **Custom Email Validation (Strict Constraints):**
  * Implemented a manual validation algorithm for user login *without* using Regular Expressions (`Regex`), strictly parsing string indexes, `@` positions, and dot sequences.
* **Graphical User Interface (GUI):**
  * Built cleanly using Java Swing (`JFrame`, `JTextField`, `JButton`, `JComboBox`, and custom event listeners).

---

## 📂 Project Structure
- `src/Main.java`: Entry point of the application.
- `src/LoginEmail.java`: Authentication window with custom string-based email validation.
- `src/TheCalculator.java`: Main dashboard handling GUI actions, formatting, and triggers.
- `src/PolynomialCalculator.java`: Core logic for polynomial math, calculus, and root finding.
- `src/InPostPreFix.java`: Infix/Postfix/Prefix conversion engine.
- `src/Stack.java`: Custom Stack implementation.
- `src/Node.java`: Linked list node structure for polynomial terms.
