import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// [골드5] 2504번. 괄호의 값
public class P2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        ArrayDeque<Object> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            // 열린 괄호 -> stack에 추가
            if (c == '(' || c == '[') stack.push(c);
            
            // 닫힌 괄호 or 숫자 값
            else {
                int sum = 0;
                while ((!stack.isEmpty()) && (stack.peek() instanceof Integer)) {
                    sum += (int) stack.pop();
                }

                // 잘못된 괄호열
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                
                char top = (char) stack.pop();

                // 잘못된 괄호열
                if ((c == ')' && top != '(') || (c == ']' && top != '[')) {
                    System.out.println(0);
                    return;
                }
                
                int value = (c == ')') ? 2 : 3;     // ()는 2, []는 3
                stack.push(sum == 0 ? value : sum * value);
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            // 잘못된 괄호열
            if (!(stack.peek() instanceof Integer)) {
                System.out.println(0);
                return;
            }
            result += (int) stack.pop();
        }
        System.out.println(result);
    }
}
