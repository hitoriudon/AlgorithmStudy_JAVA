import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// [실버3] 1021번. 회전하는 큐
public class P1021 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());    // 큐의 크기
        int M = Integer.parseInt(st.nextToken());    // 뽑아내려고 하는 수의 개수

        int[] numIndex = new int[M];
        LinkedList<Integer> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) numIndex[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) queue.add(i);

        int step = 0;
        for (int goal : numIndex) {
            while (!queue.peekFirst().equals(goal)) {
                int idx = queue.indexOf(goal);

                // 연산 2. 왼쪽으로 한 칸 이동
                if (idx < queue.size() - idx) {
                    step += Math.abs(idx);
                    while (idx-- != 0) { queue.addLast(queue.pollFirst()); }
                }

                // 연산 3. 오른쪽으로 한 칸 이동
                else {
                    idx = queue.size() - idx;
                    step += Math.abs(idx);
                    while (idx-- != 0){ queue.addFirst(queue.pollLast()); }
                }
            }

            if (queue.peekFirst().equals(goal)){
                // 연산 1. 첫 번째 원소 뽑아내기
                queue.pollFirst();
            }
        }
        System.out.print(step);
    }
}
