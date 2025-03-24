package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 소형기관차: 연속적으로 이어진 객차를 끌어 최대로 옮길 수 있는 손님의 수 -> dp: dp(n) = Math.max(dp(n-1, 3), dp(n-l, 2) + l)  // 직전 값(전부 선택) VS 마지막 줄 기차 추가
public class P2616 {
	static int N, L;
	static int[] train;  // 누적합 적용
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		train = new int[N + 1];
		dp = new int[N + 1][4];  // 객차 개수, 사용 기관차 개수 -> 0개, 1개, 2개, 3개(1개일 때의 통일성을 위함)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			train[i] = train[i - 1] + Integer.parseInt(st.nextToken());  // 누적합: 연속된 객차이므로, 누적합 이용 가능
		}
		L = Integer.parseInt(br.readLine());
		
		for (int idx = L; idx <= N; idx++) {  // 최소 기차 1대는 위치
			for (int use = 1; use < 4; use++) {
				dp[idx][use] = Math.max(dp[idx - 1][use], dp[idx - L][use - 1] + (train[idx] - train[idx - L]));  // 직전 방법과 끝에 새로운 기차 추가하는 방법 비교
			}
		}
		System.out.println(dp[N][3]);
	}
}
