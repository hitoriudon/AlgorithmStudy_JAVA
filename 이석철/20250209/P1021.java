import java.io.*;
import java.util.*;

public class P1021 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            dq.offer(i); // 가장 마지막에 넣는 메소드. 잘 기억하기!
        }
        
        int[] seq = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            int targetIndex = 0;
            for (int num : dq) {
                if (num == seq[i]) break;
                targetIndex++;
            }
            
            int halfIndex;
            if (dq.size() % 2 == 0) {
                halfIndex = dq.size() / 2 - 1;
            } else {
                halfIndex = dq.size() / 2;
            }
            
            if (targetIndex <= halfIndex) {
                for (int j = 0; j < targetIndex; j++) {
                    dq.offerLast(dq.pollFirst());
                    count++;
                }
            } else {
                for (int j = 0; j < dq.size() - targetIndex; j++) {
                    dq.offerFirst(dq.pollLast());
                    count++;
                }
            }
            dq.pollFirst();
        }
        
        System.out.println(count);
    }
}
