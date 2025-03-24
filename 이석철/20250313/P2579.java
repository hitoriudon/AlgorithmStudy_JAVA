
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[300 + 1];

        for (int i = 1; i < n + 1; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[300 + 1];
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];

        for (int i = 3; i < n + 1; i++){
            dp[i] = Math.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
        }

        System.out.println(dp[n]);
    }
}
