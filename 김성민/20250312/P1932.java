import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int numAmount = Integer.parseInt(br.readLine());

		int dp[][] = new int[numAmount + 1][numAmount + 1];

		for (int line = 1; line <= numAmount; line++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 1; x <= line; x++) {
				int cutNum = Integer.parseInt(st.nextToken());
				// 내려가면서 접근 가능한 위쪽 두 곳중 큰 값을 더해가며 내려가기 DP
				dp[line][x] = Math.max(dp[line - 1][x - 1], dp[line - 1][x]) + cutNum;
			}
		}
		int maxSum = 0;
		// 마지막층 전부 탐색하며 가장 큰 값 찾기
		for (int x = 1; x <= numAmount; x++) {
			maxSum = Math.max(maxSum, dp[numAmount][x]);
		}

		System.out.println(maxSum);
	}
}
