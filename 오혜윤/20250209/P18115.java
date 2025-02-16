import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// [실버3] 18115번. 카드 놓기
public class P18115 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> result = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        for (int i = 1; i <= N; i++) {
            int tech = Integer.parseInt(st.nextToken());

            if (tech == 1) result.addFirst(i);
            else if (tech == 2) {
                int tmp = result.removeFirst();
                result.addFirst(i);
                result.addFirst(tmp);
            }
            else result.addLast(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) sb.append(r).append(" ");
        System.out.println(sb);
    }
}
