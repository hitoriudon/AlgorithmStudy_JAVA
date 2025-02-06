import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayDeque;
import java.util.Deque;

// [실버4] 9012번. 괄호
public class P9012 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스
        
        // #1. 스택(ArrayDeque) 사용
        // 메모리 11880KB, 시간 72ms
        Deque<Character> stack = new ArrayDeque<>();
        while (T-- > 0) {
            stack.clear();
            String input = br.readLine();
            boolean result = true;

            for(int i = 0; i < input.length(); i++) {
                Character p = input.charAt(i);

                // 오른쪽 괄호 '(' -> push
                if (p.equals('(')) stack.push(p);

                // 왼쪽 괄호 ')' -> pop
                // 스택이 비어있다면 반복문 종료
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

        // -------------------------------------------------------------------------
        // #2. 단순 카운터 사용
        // 메모리 11760KB, 시간 68ms
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            int balance = 0;
            boolean isVPS = true;
            
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '(') balance++;
                else {
                    balance--;
                    if (balance < 0) {
                        isVPS = false;
                        break;
                    }
                }
            }
            
            // 전체 다 조회했는데 balance가 0이 아닌, 즉 오른쪽 괄호가 남은 경우
            if (balance != 0) isVPS = false;
            
            sb.append(isVPS ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }
}
