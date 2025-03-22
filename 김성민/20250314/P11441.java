import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11441 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int numAmount = Integer.parseInt(br.readLine());
		int[] dp = new int[numAmount + 1];

		st = new StringTokenizer(br.readLine());
		for (int numCount = 1; numCount <= numAmount; numCount++) {
			// 이전까지의 합을 들고가면서 dp 구성
			dp[numCount] = Integer.parseInt(st.nextToken()) + dp[numCount - 1];
		}

		int orderAmount = Integer.parseInt(br.readLine());
		for (int orderCount = 0; orderCount < orderAmount; orderCount++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// end까지의 합을 구한 뒤 start 이전 까지의 합을 빼서 사이 합 구하기
			sb.append(dp[end] - dp[start - 1]).append("\n");
		}

		System.out.println(sb);
	}
}
