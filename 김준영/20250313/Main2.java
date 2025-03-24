import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [2579 계단 오르기]
// 1. 계단 1~2개씩 오를 수 있음
// 2. 연속된 세계의 계단을 밟을 수는 없음
// 3. 마지막 계단은 밟아야함
// 최대 점수 출력

public class Main {
	static int n;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		dp = new int[n + 1];

		// 입력
		for (int i = 1; i <= n; i++) {
			arr[i] = (Integer.parseInt(br.readLine()));
		}

		// DP
		dp[1] = arr[1];
		if (n >= 2) dp[2] = arr[1] + arr[2];

		for(int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i];
		}
		

//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(res));

		System.out.println(dp[n]);
	}
}
