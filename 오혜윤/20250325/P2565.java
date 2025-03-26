// [골드5] 2565번. 전깃줄
// 메모리 : 18396 KB, 시간 : 188 ms

import java.io.*;
import java.util.*;

class P2565 {

    static int[][] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 전깃줄 개수 N

        list = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[N]; // i번째 전깃줄까지 봤을 때, 교차되지 않고 연결 가능한 전깃줄의 최대 개수
        int result = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 최소값 : 자기 자신
            for (int j = 0; j < i; j++) {
                if (list[j][1] < list[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

        System.out.println(N - result);
    }
}
