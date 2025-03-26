import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [2156] 포도주 시식
// 1. 잔 선택시 전부 다 먹어야함
// 2. 연속 3잔 마실 수는 없음
// 목표 : 가장 많은 포도주를 먹을 수 있도록
// n번째 포도주 안먹는 경우 or 먹는경우 -> (이전에 안먹은 경우 먹은경우)
// dp[n] = Math.max(dp[n-1], Math.max(dp[n-3] + arr[n-1] + arr[n] , dp[n-2] + arr[n]))

public class B2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = arr[1];
		if (n >= 2) {
			dp[2] = arr[1] + arr[2];
		}

		int res = Integer.MIN_VALUE;

		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]));
		}

		for (int d : dp) {
			res = Math.max(res, d);
		}
		System.out.println(res);

	}
}