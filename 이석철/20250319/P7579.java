import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P7579 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        StringTokenizer stMemory = new StringTokenizer(br.readLine());
        StringTokenizer stCost = new StringTokenizer(br.readLine());

        int[] memory = new int[n + 1];
        int[] cost = new int[n + 1];

        int totalCost = 1;
        for (int i = 1; i < n + 1; i++) {
            memory[i] = Integer.parseInt(stMemory.nextToken());
            cost[i] = Integer.parseInt(stCost.nextToken());

            totalCost += cost[i];
        }

        int[][] dp = new int[n + 1][totalCost];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < totalCost; j++) {
                if (j >= cost[i])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        for (int i = 0; i < totalCost; i++) {
            if (dp[n][i] >= m) { // 모든 앱을 고려한 상태에서, 최소 비용을 찾기
                System.out.println(i);
                break;
            }
        }
    }
}