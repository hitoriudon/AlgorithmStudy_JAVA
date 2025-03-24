package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 계단 오르기: 한 계단 or 두 계단 -> dp, 연속 방지를 위한 flag 설정
// dp[n] = 현재 + max(dp[n - 1][1], dp[n-2][2])
public class P2579 {
	static int N;
	static int[][] dp;  // 계단 개수, 연속 횟수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 2][3];
		
		int value;
		for (int idx = 2; idx < N + 2; idx++) {
			value = Integer.parseInt(br.readLine());
			dp[idx][1] = value + dp[idx - 2][2];  // 이전에 2칸 뛰기 적용
			dp[idx][2] = value + Math.max(dp[idx - 2][2], dp[idx - 1][1]);  // 두 칸 뛰기와 1칸 뛰기를 비교하여 최댓값 저장
		}
		
		System.out.println(dp[N+1][2]);
	}
}
