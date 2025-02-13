import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();

<<<<<<< HEAD
        int result = 0;
        int temp = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // (( (() []) () )) = 2* 2* (2* (2 + 3) + 2)
            // *2 *2 *2 *2 /2, +2 *3 /3, +3 을 생각하기!

            if (c == '(') {
                stack.push(c);
                temp *= 2;
                valueStack.push(temp);
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
                valueStack.push(temp);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                if (s.charAt(i - 1) == '(') {
                    result += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                if (s.charAt(i - 1) == '[') {
                    result += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
=======
    int result = 0;
    int temp = 1;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        // (( (() []) () )) = 2* 2* (2* (2 + 3) + 2)
        // *2 *2 *2 *2 /2, +2 *3 /3, +3라는 식으로 동작하게끔

        if (c == '(') {
            stack.push(c);
            temp *= 2;
            valueStack.push(temp);
        } else if (c == '[') {
            stack.push(c);
            temp *= 3;
            valueStack.push(temp);
        } else if (c == ')') {
            if (stack.isEmpty() || stack.peek() != '(') {
                System.out.println(0);
                return;
            }
            if (s.charAt(i - 1) == '(') {
                result += temp;
            }
            stack.pop();
            temp /= 2;
        } else if (c == ']') {
            if (stack.isEmpty() || stack.peek() != '[') {
                System.out.println(0);
                return;
            }
            if (s.charAt(i - 1) == '[') {
                result += temp;
            }
            stack.pop();
            temp /= 3;
        }
    }

    if (!stack.isEmpty()) {
        System.out.println(0);
    } else {
        System.out.println(result);
    }
}
>>>>>>> 71c5145c317096879961056b5ff928d30009e1ee
}