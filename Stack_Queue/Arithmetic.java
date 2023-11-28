package Stack_Queue;

import java.util.Stack;

public class Arithmetic {

    public static double evaluate(String expression) throws IllegalArgumentException, ArithmeticException {
        // Remove white spaces
        expression = expression.replaceAll("\\s", "");

        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length()
                        && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                operandStack.push(Double.parseDouble(num.toString()));
                i--;
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop(); // To Remove the opening parenthesis
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in the expression: " + c);
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        if (operandStack.size() == 1) {
            return operandStack.pop();
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static void performOperation(Stack<Double> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        double result = applyOperator(operand1, operand2, operator);
        operandStack.push(result);
    }

    private static double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expression = "3 * 2 / 5";
        try {
            double result = evaluate(expression);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid expression: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        }
    }
}
