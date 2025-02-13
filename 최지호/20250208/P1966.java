import java.io.*;
import java.util.*;

public class P1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            StringTokenizer input = new StringTokenizer(br.readLine());
            Deque<int[]> q = new LinkedList<>();
            List<Integer> proper = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                int p = Integer.parseInt(input.nextToken());
                //문서 위치, 중요도 같이 저장 받기
                q.add(new int[]{j,p});
                proper.add(p);
            }
            int printCount = 0;

            while (!q.isEmpty()) {
                 // 현재 문서
                int[] current = q.pollFirst();
                 // 현재 큐에서 가장 높은 중요도 찾기
                int maxPriority = Collections.max(proper);
                if (current[1] == maxPriority) {
                    printCount++;
                    proper.remove(Integer.valueOf(current[1]));

                    if (current[0] == m) {
                        System.out.println(printCount);
                        break;
                    }
                } else {
                    q.addLast(current);
                }
            }
        }
    }
}