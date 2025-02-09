import java.io.*;
import java.util.*;

public class P18115 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        
        /** 이 문제도 어제처럼 우선순위 큐로 풀었는데, 시간 초과 났음... 그냥 ArrayDeque으로 푸는 게 맞겠다 싶어서 코드 변경함 */
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                deque.addFirst(i);
            } else if (num == 2) {
                int top = deque.pollFirst();
                deque.addFirst(i);
                deque.addFirst(top);
            } else {
                deque.addLast(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst()).append(" ");
        }
        System.out.println(sb);
    }
}
