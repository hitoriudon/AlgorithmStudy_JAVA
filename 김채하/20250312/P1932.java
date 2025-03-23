import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(br.readLine());
			return;
		}
		int answer = 0;
		int[][] dp = new int[N][N];
		dp[0][0] = Integer.parseInt(br.readLine());
		for (int j = 2; j < N+1; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < j; i++) {
				if(i == 0) {
					dp[j-1][i] = dp[j-2][i];
				}else if(i == j-1) {
					dp[j-1][i] = dp[j-2][i-1];
				}else {
					dp[j-1][i] = Math.max(dp[j-2][i-1],dp[j-2][i]);
				}
				dp[j-1][i] += Integer.parseInt(st.nextToken());
				answer = Math.max(answer, dp[j-1][i]);
			}
		}
		
		System.out.println(answer);
	}
}
