
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class P10799 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stick = br.readLine();
        Stack<Character> stack = new Stack<>();

        int len = stick.length();
        char before_c = '-';
        int ans = 0;

        for (int i = 0; i < len; i++){
            char c = stick.charAt(i);
            
            // 전략: '(': 우선 다 스택에 넣기, ')': pop()하는데, 직전 c가 '('였으면 레이저로 간주하고 다르게 처리
            // 사실 스택 안 쓰고 그냥 더하기 빼기로도 할 수 있을 거 같다.
            if (c == '('){
                stack.push('(');
            } else if (c == ')' && before_c == '('){
                stack.pop();
                ans += stack.size();
            } else if (c == ')') {
                stack.pop();
                ans += 1;
            }
            before_c = c;
        }
        System.out.println(ans);
    }    
}