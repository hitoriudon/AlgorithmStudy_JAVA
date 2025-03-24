import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1463 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        
        for (int i = 2; i < n + 1; i++){
            dp[i] = dp[i - 1] + 1;
            
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
        }

        System.out.println(dp[n]);
    }
}

/**
 * if (i % 3 == 0) dp[i] = Math.min(dp[i - 1] + 1, dp[i / 3] + 1);
 * else if (i % 2 == 0) dp[i] = Math.min(dp[i - 1] + 1, dp[i / 2] + 1);
 * else dp[i] = dp[i - 1] + 1;
 * 
 * 문제는 if–else구조로 인해 가능한 모든 연산을 고려하지 않는다.
 * 예를 들어, i가 2와 3으로 모두 나누어떨어지는 경우(예: 6, 12, 18 등),
 * 올바른 점화식은
 * dp[i] = min(dp[i − 1] + 1, dp[i / 2] + 1, dp[i / 3] + 1)이다.
 * 그러나 if else로 처리하게 되면 
 * dp[i] = min(dp[i − 1] + 1, dp[i / 3] + 1) 밖에 처리하지 않는다.
 */ 