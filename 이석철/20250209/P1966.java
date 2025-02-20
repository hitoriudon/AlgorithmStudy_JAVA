import java.io.*;
import java.util.*;

public class P1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new LinkedList<>();

            // 우선순위 큐를 이용하면 훨씬 좋을듯? with 내림차순 정렬
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new int[] { i, priority });
                pq.add(priority);
            }

            int cnt = 0;
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int index = current[0];
                int priority = current[1];

                if (priority == pq.peek()) {
                    pq.poll();
                    cnt++;
                    if (index == m) {
                        sb.append(cnt).append("\n"); // append 자바에서 사용하기
                        break;
                    }
                } else {
                    queue.add(current);
                }
            }
        }
        System.out.print(sb);
    }
}