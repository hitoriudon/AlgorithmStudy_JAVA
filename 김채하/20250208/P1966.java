import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayDeque<int[]> queue;
        PriorityQueue<Integer> priorityQueue;
        int N, M;
        int value;
        int idx, pre;
        int[] document;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
            queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                value = Integer.parseInt(st.nextToken());
                queue.addLast(new int[]{value, i});
                priorityQueue.add(value);
            }

            idx = 0;
            pre = -1;
            while(pre != M){
                document = queue.pollFirst();
                if (document[0] >= priorityQueue.peek()){
                    idx++;
                    priorityQueue.poll();
                    pre = document[1];
                }else {
                    queue.addLast(document);
                }
            }
            sb.append(idx).append("\n");
        }
        System.out.print(sb);
    }
}