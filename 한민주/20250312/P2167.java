package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2167 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] sum = new int[n * m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				sum[m * i + j] = Integer.parseInt(st.nextToken());
				if (i > 0) {
					sum[m * i + j] += sum[m * (i - 1) + j];
				}
				if (j > 0) {
					sum[m * i + j] += sum[m * i + j - 1];
				}
				if (i > 0 && j > 0) {
					sum[m * i + j] -= sum[m * (i - 1) + j - 1];
				}
			}
		}

		int k = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		// 출력
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int ax = Integer.parseInt(st.nextToken()) - 1;
			int ay = Integer.parseInt(st.nextToken()) - 1;
			int bx = Integer.parseInt(st.nextToken()) - 1;
			int by = Integer.parseInt(st.nextToken()) - 1;

			int first = ax * m + ay;
			int second = bx * m + by;
			int answer = sum[second];

			if (first > 0) { // 1, 1이 아닐 때
				if (first % m == 0) { // 
					answer -= sum[first / m * m + second % m - m];
				} else if (first >= m) {
					answer -= sum[second / m * m + first % m - 1];
					answer -= sum[first / m * m + second % m - m];
					answer += sum[first - m - 1];
				} else {
					answer -= sum[second / m * m + first % m - 1];
				}
			}

			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
