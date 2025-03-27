import java.io.*;
import java.util.*;

// [12865] 평범한 배낭
// 메모리 : 12160
// 시간 : 104

public class B12865 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물품의 수
		int k = Integer.parseInt(st.nextToken()); // 최대 무게
		int res = 0;

		int[][] item = new int[n + 1][2];
		int[] dp = new int[k + 1];
		// 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken()); // 물건의 무게
			item[i][1] = Integer.parseInt(st.nextToken()); // 물건의 가치

		}

		// DP 로직 처리 -> 원래 정방향으로 했는데, 그러면 배낭의 아이템을 무한히 쓰는 경우이므로 역순으로 변경
		for (int i = 1; i <= n; i++) { // n개의 물품에 대하여
			int weight = item[i][0];
			int value = item[i][1];

			for (int size = k; size >= weight; size--) {
				dp[size] = Math.max(dp[size], dp[size - weight] + value);
				res = Math.max(res, dp[size]);
			}
		}

		// System.out.println(Arrays.toString(dp));

		System.out.println(res);
	}
}