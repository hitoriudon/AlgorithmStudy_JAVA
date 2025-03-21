package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1로 만들기: greedy한 접근. 나눌 수 있으면 최대한 빨리 나눈다. -> 2와 3으로 나누는 경우가 혼재해 greedy 불가
// dp 사용: dp(n) = Math.min(차 + dp(n/3), 차 + dp(n/2)) 
public class P1463 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		
		for (int num = 2; num <= N; num++) {  // 1은 0
			dp[num] = 1 + Math.min(num - (num/2)*2 + dp[num/2], num - (num/3)*3 + dp[num/3]);  // 나눗셈 횟수(1) + min
		}
		System.out.println(dp[N]);
//		while(N != 1) {
//			int divideThree = N/3;
//			int divideTwo = N/2;
//			
//			// 차 + 결과 값(나머지 N) 비교
//			if (N - divideThree*2 > N - divideTwo) {
//				count += N - divideTwo*2;
//				N = divideTwo;
//			}
//			else {  // 2와 3으로 둘 다 나눌 수 있을 때는 3으로 나누기 선택
//				count += N - divideThree*3;
//				N = divideThree;
//			}
//			count++;  // 나누기용
//		}
	}
}
