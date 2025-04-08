
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i + 1; j++){
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i < n + 1; i++){
            dp[i][1] += dp[i - 1][1];
            dp[i][i] += dp[i - 1][i - 1];
        }

        for (int i = 3; i < n + 1; i++){
            for (int j = 2; j < i; j++){
                dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }
        int answer = 0;
        for (int j = 1; j < n + 1; j++){
            answer = Math.max(answer, dp[n][j]);
        }
        
        System.out.println(answer);
        // for (int i = 0; i < n + 1; i++){
        //     for (int j = 0; j < n + 1; j++){
        //         if (dp[i][j] != 0){
        //             System.out.print(dp[i][j] + " ");
        //         }
        //     }
        //     System.out.println();
        // }
    }    
}
