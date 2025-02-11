import java.io.*;
import java.util.*;

public class P1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        int[] targets = new int[m];

        for(int i = 0; i < n; i++) {
            deque.add(i + 1);
        }        

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < m; i++) {
            int target = targets[i];

            int index = 0;
            for (int num : deque) {
                if (num == target) break;
                index++;
            }
            //동근이때와 비슷하게 한쪽과 반대편은 총 크기 - index를 해주면 됨
            int toLeft = index;
            int toRight = deque.size() - index;

            if (toLeft <= toRight) {
                for (int j = 0; j < toLeft; j++) {
                    deque.addLast(deque.pollFirst());
                }
                answer += toLeft;
            } else {
                for (int j = 0; j < toRight; j++) {
                    deque.addFirst(deque.pollLast());
                }
                answer += toRight;
            }
            deque.pollFirst();
        }
        System.out.println(answer);
    }
}