import java.io.*;
import java.util.*;
 
public class P2616 {
 
    static int n, m;
	static int[] train, prefixSum;
	static int[][] dp;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        n = Integer.parseInt(br.readLine());
		train = new int[n+1];
		prefixSum = new int[n+1];
		dp = new int[4][n+1];
        
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n + 1; i++) {
			train[i] = Integer.parseInt(st.nextToken());
			prefixSum[i] = prefixSum[i - 1] + train[i];
		}
        
		m = Integer.parseInt(br.readLine());

		for(int i = 1; i < 4; i++){
			for(int j = m * i; j < n + 1; j++){
				dp[i][j] = Math.max(dp[i - 1][j - m] + (prefixSum[j] - prefixSum[j-m]), dp[i][j - 1]); // 선택 안 한 경우와 선택(소형 기관차 선택)
			}
		}
		
		System.out.println(dp[3][n]);
	}
}