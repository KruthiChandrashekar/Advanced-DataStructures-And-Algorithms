
package Stack_Queue;

import java.util.*;

//task 1
public class StackImplementation {
    public static void main(String args[]) {
        StackUsingLL stack = new StackUsingLL();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose what function do you want to perform: 1.Push 2.Pop 3.Peek 4.Size");
        stack.push(5.6);
        stack.push(56);
        stack.push(6);
        stack.push(60);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Enter the integer element to insert");
                stack.push(scanner.nextDouble());
                break;

            case 2:
                System.out.println(stack.pop());
                break;

            case 3:
                System.out.println(stack.peek());
                break;

            case 4:
                System.out.println(stack.size());
                break;

            default:
                System.out.println("Invalid Choice");
        }

        scanner.close();

    }
}