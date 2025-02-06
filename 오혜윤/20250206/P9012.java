import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayDeque;
import java.util.Deque;

// [실버4] 9012번. 괄호
public class P9012 {
    public static void main(String[] args) throws IOException{
        Deque<Character> stack = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스

        while (T > 0) {
            T--;
            stack.clear();
            String input = br.readLine();
            boolean result = true;

            for(int i = 0; i < input.length(); i++) {
                Character p = input.charAt(i);

                // 오른쪽 괄호 '(' -> push
                if (p.equals('(')) stack.push(p);

                // 왼쪽 괄호 ')' -> pop
                // pop 전부터 스택이 비어있다면 바로 "NO" 출력 후 반복문 종료
                else if (p.equals(')')) {
                    if (stack.isEmpty()) {
                        result = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if (stack.isEmpty() && result) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
