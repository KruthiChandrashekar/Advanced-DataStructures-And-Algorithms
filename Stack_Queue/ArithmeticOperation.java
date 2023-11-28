package Stack_Queue;

public class ArithmeticOperation {
    public static double solution(String str) {
        StackUsingLL operator = new StackUsingLL(); // stack to store operators
        StackUsingLL operand = new StackUsingLL(); // stack to store operands/digits
        int i = 0;

        str = str.replaceAll(" ", ""); // replacing white spaces with no space in the string expression

        while (i < str.length()) {
            char c = str.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder digit = new StringBuilder();

                while (i < str.length() && (Character.isDigit(c) || c == '.')) {
                    digit.append(c);
                    i++;

                    if (i < str.length()) {
                        c = str.charAt(i); // Get the next character
                    }
                }

                i--; // Decrement to account for the next character
                operand.push(Double.parseDouble(digit.toString())); // adding to operand stack
            }

            else if (c == '+' || c == '-' || c == '/' || c == '*') {

                while (!operator.isStackEmpty() && bodmas(c, (char) operator.peek())) {
                    operand.push(calculate(operand.pop(), operand.pop(), (char) operator.pop())); // to perform the
                                                                                                  // operation and push
                                                                                                  // it on top of the
                                                                                                  // operand stack for
                                                                                                  // further calculation
                    System.out.println("Operator pushed");
                }
                operator.push(c);

            } else {
                return Double.NaN;
            }

            i++;
        }

        while (!operator.isStackEmpty()) // repeat the calculation process until all the operators are executed
        {
            operand.push(calculate(operand.pop(), operand.pop(), (char) operator.pop()));

            return operand.pop();
        }

        return Double.NaN;
    }

    private static boolean bodmas(char a, char b) {
        if ((a == '*' || a == '/') && b == '+' || b == '-') {
            return true;
        }

        return false;
    }

    private static double calculate(double num1, double num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    System.out.println("Divide 0");
                    return Double.NaN; // Cannot be divided by zero
                }
                return num1 / num2;
            default:
                return 0;
        }
    }
}
