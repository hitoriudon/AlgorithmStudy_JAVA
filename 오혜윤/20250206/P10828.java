import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Deque;
import java.util.ArrayDeque;

// [실버4] 10828번. 스택
public class P10828 {

    public static void main(String[] args) throws IOException {

        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String action = st.nextToken();

            // 스택 연산
            if (action.equals("push")){
                stack.push(Integer.parseInt(st.nextToken()));
            }

            else if (action.equals("pop")){
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            }

            else if (action.equals("size")){
                sb.append(stack.size()).append("\n");
            }
            
            else if (action.equals("empty")){
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            }

            else if (action.equals("top")){
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
