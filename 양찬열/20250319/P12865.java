package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평범한 배낭: 배낭 문제 -> 조합 or dp
public class P12865 {
	static int N, K;
	static int[] weights;
	static int[] values;
	static int[][] dp;  // Weight에 따른 dp, 원소를 하나씩 추가하며 확인
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weights = new int[N + 1];
		values = new int[N + 1];
		dp = new int[N + 1][K + 1];  // 첫번째 원소도 공통 처리 -> 0번은 사용 X
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int idx = 1; idx <= N; idx++) {
			for (int w = 1; w <= K; w++) {
				if (w >= weights[idx]) {  // 추가 가능: 이전 값과 현재 값 비교 후 최댓값 사용
					dp[idx][w] = Math.max(dp[idx - 1][w], values[idx] + dp[idx - 1][w - weights[idx]]);
				}
				else {  // 추가 불가
					dp[idx][w] = dp[idx - 1][w];  // 이전 값 이용
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}