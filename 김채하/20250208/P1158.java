import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        sb.append("<");
        int value;
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < K-1; j++) {
                value = queue.pollFirst();
                queue.addLast(value);
            }
            sb.append(queue.pollFirst()).append(", ");
        }
        sb.append(queue.pollFirst()).append(">");
        System.out.print(sb);
    }

}
