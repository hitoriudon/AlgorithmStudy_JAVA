import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1 2 4 or 1 3
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n];

        for(int i = 0; i < n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        dp[0] = stairs[0];
        // 아래 부분 없으니 런타임 에러 발생했음
        if (n >= 2) {
            dp[1] = stairs[0] + stairs[1];
        }

        if (n >= 3) {
            dp[2] = Math.max(stairs[1] + stairs[2], stairs[0] + stairs[2]);
        }
        //

        for(int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i-3] + stairs[i-1] + stairs[i], dp[i-2] + stairs[i]);
        }
        System.out.println(dp[n-1]);
    }
}
