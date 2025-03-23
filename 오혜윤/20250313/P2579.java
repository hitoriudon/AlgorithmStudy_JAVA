// [실버3] 2579번. 계단 오르기
// 메모리 : 11444 KB, 시간 : 68 ms

import java.io.*;

public class P2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 계단의 개수

        int[] stairs = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (N < 3) {
            int result = 0;
            for (int stair : stairs) {
                result += stair;
            }
            System.out.println(result);
            return;
        }

        int[] prefix = new int[N + 1];
        prefix[1] = stairs[1];
        prefix[2] = stairs[1] + stairs[2];
        for (int i = 3; i < N + 1; i++) {
            prefix[i] = stairs[i] + (Math.max(prefix[i - 2], prefix[i - 3] + stairs[i - 1]));
        }
        System.out.println(prefix[N]);
    }
}
