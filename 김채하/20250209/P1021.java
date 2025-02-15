import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P1021 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        ArrayDeque<Integer> leftArr = new ArrayDeque<>();
        ArrayDeque<Integer> rightArr = new ArrayDeque<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            leftArr.add(i);
            rightArr.add(i);
        }

        int answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int value = Integer.parseInt(st.nextToken());
            int left = 0;
            int right = 0;

            while(leftArr.peekFirst() != value) {
                left++;
                leftArr.addLast(leftArr.pollFirst());
            }

            while(rightArr.peekFirst() != value) {
                right++;
                rightArr.addFirst(rightArr.pollLast());
            }

            leftArr.pollFirst();
            rightArr.pollFirst();

            answer += Math.min(left, right);

        }
        System.out.println(answer);
    }

}
