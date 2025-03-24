import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int objectAmount = Integer.parseInt(st.nextToken());
		int maxWeight = Integer.parseInt(st.nextToken());

		int[][] objects = new int[objectAmount + 1][2];
		for (int objectCount = 1; objectCount <= objectAmount; objectCount++) {
			st = new StringTokenizer(br.readLine());
			objects[objectCount][0] = Integer.parseInt(st.nextToken());
			objects[objectCount][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[objectAmount + 1][maxWeight + 1];

		for (int objectCount = 1; objectCount <= objectAmount; objectCount++) {
			for (int weightCount = 1; weightCount <= maxWeight; weightCount++) {
				if (objects[objectCount][0] > weightCount) {
					dp[objectCount][weightCount] = dp[objectCount - 1][weightCount];
				} else {
					dp[objectCount][weightCount] = Math.max(dp[objectCount - 1][weightCount],
							dp[objectCount - 1][weightCount - objects[objectCount][0]] + objects[objectCount][1]);
				}
			}
		}

		System.out.println(dp[objectAmount][maxWeight]);
	}
}
