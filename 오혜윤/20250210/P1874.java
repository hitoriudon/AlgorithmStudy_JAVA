import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// [실버2] 1874번. 스택 수열
public class P1874 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        boolean isSame = true;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());

            while (cnt < num) {
                cnt++;
                stack.addLast(cnt);
                sb.append("+").append("\n");
            }

            if (stack.peekLast() == num) {
                stack.pollLast();
                sb.append("-").append("\n");
            } else isSame = false;
        }

        if (!isSame) System.out.println("NO");
        else System.out.println(sb);
    }
}
