import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// [실버3] 1966번. 프린터 큐
public class P1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 문서의 개수
            int M = Integer.parseInt(st.nextToken());   // 궁금한 문서의 현재 Queue Index
            
            // priority : {문서의 처음 위치, 중요도}
            LinkedList<int[]> priority = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                priority.add(new int[] {i, Integer.parseInt(st.nextToken())});
            }

            int result = 0;
            while (!priority.isEmpty()) {
                boolean isMax = true;
                int[] current = priority.poll();

                for (int[] tmp : priority) {
                    
                    // 가장 앞에 있던 문서보다 우선 순위가 더 큰 문서가 존재하면
                    if (current[1] < tmp[1]) {
                        // 가장 앞에 있던 문서 ~ 우선 순위가 더 큰 문서 전 문서 뒤로 이동
                        priority.add(current);
                        int idx = priority.indexOf(tmp);
                        for (int j = 0; j < idx; j++) priority.add(priority.poll());

                        isMax = false;
                        break;
                    }
                }

                // 우선 순위가 가장 큰 문서가 아니면 continue
                if (!isMax) continue;

                // 우선 순위가 가장 큰 문서 -> 출력 -> result 1만큼 증가
                result++;

                // 현재 문서가 M 문서면 while 반복문 종료
                if (current[0] == M) break;
            } sb.append(result).append('\n');
        } System.out.println(sb);
    }
}
