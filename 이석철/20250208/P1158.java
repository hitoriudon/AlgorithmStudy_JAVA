import java.io.*;
import java.util.*;

public class P1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> deque = new ArrayDeque<>(); // que랑 같음
        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }

        StringBuilder sb = new StringBuilder("<"); // string builder가 이럴 떄 필요한지 잘 모르겠네
        while (!deque.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                deque.offer(deque.poll());
            }

            sb.append(deque.poll()); // java in append
            if (!deque.isEmpty()) {
                sb.append(", ");
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
