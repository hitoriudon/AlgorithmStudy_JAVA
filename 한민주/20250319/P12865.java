package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int bags[][] = new int[n][2]; // w v
		int minW = 100000;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			bags[i][0] = Integer.parseInt(st.nextToken());
			bags[i][1] = Integer.parseInt(st.nextToken());
			minW = Math.min(bags[i][0], minW);
		}

		int dp[] = new int[k + 1];
		for (int i = 0; i < n; i++) {
			for (int j = k; j >= bags[i][0]; j--) {
				if (j - bags[i][0] >= 0)
					dp[j] = Math.max(dp[j - bags[i][0]] + bags[i][1], dp[j]);
				else
					dp[j] = Math.max(dp[j], bags[i][1]);
			}
		}

		System.out.println(dp[k]);
	}

}
