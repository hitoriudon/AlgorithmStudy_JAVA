import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class P2504{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        
        char in, out;
        int answer = 0;
        int val = 1;
        boolean prePop = false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            in = str.charAt(i);

            // 열린 괄호
            if (in == '(' || in == '[') {
                stack.push(in);
                val *= in == '(' ? 2 : 3;
                prePop = false;
            } else {
                if (stack.isEmpty()) {
                    answer = 0;
                    break;
                }
                if (in == ')' && stack.lastElement() == '(' || in == ']' && stack.lastElement() == '[') {
                    out = stack.pop();
                    if (!prePop)
                        answer += val;
                    val /= out == '(' ? 2 : 3;
                    prePop = true;
                } else {
                    answer = 0;
                    break;
                }
            }

            // System.out.println(val + " 현재 답: " + answer);

        }
        if (!stack.isEmpty())
            answer = 0;
        System.out.println(answer);
    }
}