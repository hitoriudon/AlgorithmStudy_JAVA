package SWEA;

/**
 * SWEA 1952 수영장
 * DP
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1952 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int[] cost = new int[4];
            for (int i = 0; i < 4; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int[] usePlan = new int[13]; // +1
            for (int i = 1; i < 13; i++) {
                usePlan[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[13];

            for (int month = 1; month <= 12; month++) {
                dp[month] = Math.min(dp[month - 1] + usePlan[month] * cost[0], dp[month - 1] + cost[1]); // dp[month]에는
                                                                                                         // 지금까지
                                                                                                         // 최소값(dp[month-1])과
                                                                                                         // 하루 이용권 쓰기 vs
                                                                                                         // 한 달 이용권 그냥
                                                                                                         // 끊기 값 중 최소가
                                                                                                         // 저장됨

                if (month >= 3) { // 3달 이용권도 비교해주기. 근데 3월~12월만 3달 이용권을 살 수 있음
                    dp[month] = Math.min(dp[month], dp[month - 3] + cost[2]); // 3개월 전까지(지금이 4월이면 1월까지)의 값 + 3달 이용권 값
                }
            }

            dp[12] = Math.min(dp[12], cost[3]); // 마지막으로 1년 이용권
            sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
        }
        System.out.println(sb);
    }
}
