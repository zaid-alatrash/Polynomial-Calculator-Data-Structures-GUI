# Polynomial & Expression Processor

A Java-based desktop application that combines polynomial computation, mathematical expression processing, and numerical methods with custom implementations of fundamental data structures and algorithms.

The project provides an interactive GUI for performing polynomial operations, converting mathematical expressions between different notations, and solving polynomial equations using numerical methods.

---

## 📌 Overview

The application was developed to demonstrate the practical implementation of data structures, algorithms, and object-oriented programming concepts in Java.

Instead of relying on high-level collection frameworks or regular expressions, core structures and processing logic were implemented manually to provide a deeper understanding of how these mechanisms work internally.

The project combines two main areas:

- Polynomial manipulation and mathematical operations.
- Mathematical expression parsing and notation conversion.

---

## ✨ Features

### Polynomial Operations

The application supports:

- Addition
- Subtraction
- Multiplication
- Polynomial Long Division
- Differentiation
- Root Finding
- Quotient and Remainder calculation

### Expression Processing

The application can convert mathematical expressions between:

- Infix
- Postfix
- Prefix

The conversion process is handled using a custom Stack implementation and manually defined operator precedence.

### Numerical Methods

Polynomial roots can be approximated using numerical techniques such as:

- Newton-Raphson Method
- Discriminant-based calculations

### Input Validation

The project also includes custom email validation without using Regular Expressions.

The validation logic checks the structure of the email using character positions and conditions such as:

- `@` placement
- `.`
- Domain structure
- Domain length

---

## 🧩 Data Structures

The project implements fundamental data structures from scratch.

### Linked List

A custom Linked List structure is used to represent polynomial terms.

Each term contains:

- Coefficient
- Exponent
- Reference to the next term

This allows polynomial terms to be dynamically managed.

### Stack

A custom array-based Stack is used primarily during mathematical expression conversion.

It supports operations such as:

- `push()`
- `pop()`
- `peek()`
- `isEmpty()`

The Stack is responsible for helping manage operators and their precedence during Infix, Postfix, and Prefix conversion.

---

## ⚙️ Main Components

| Component | Responsibility |
|---|---|
| `Main.java` | Application entry point |
| `LoginEmail.java` | Login interface and email validation |
| `TheCalculator.java` | Main calculator interface |
| `PolynomialCalculator.java` | Polynomial operations and calculus |
| `InPostPreFix.java` | Expression parsing and notation conversion |
| `Stack.java` | Custom Stack implementation |
| `Node.java` | Linked List node structure |

---

## 🏗️ Project Structure

```text
Polynomial-Project/
│
├── src/
│   ├── Main.java
│   ├── LoginEmail.java
│   ├── TheCalculator.java
│   ├── PolynomialCalculator.java
│   ├── InPostPreFix.java
│   ├── Stack.java
│   └── Node.java
│
├── images/
│   └── UI Assets
│
└── README.md
