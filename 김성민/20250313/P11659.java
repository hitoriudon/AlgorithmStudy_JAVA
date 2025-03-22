import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int numAmount = Integer.parseInt(st.nextToken());
		int orderAmount = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] dp = new int[numAmount + 1];
		for (int numCount = 1; numCount <= numAmount; numCount++) {
			// dp로 이전까지의 합 계속 더하며 나가기
			dp[numCount] = dp[numCount - 1] + Integer.parseInt(st.nextToken());
		}

		for (int orderCount = 0; orderCount < orderAmount; orderCount++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 끝부분까지 더해진 것에서 앞부분 전까지 더해준 부분 빼기
			sb.append(dp[end] - dp[start - 1]).append("\n");
		}

		System.out.println(sb);
	}
}
