package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1932 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int tree[] = new int[(n * (n + 1) / 2)];
		int sum[] = new int[(n * (n + 1) / 2)];
		int size = 0;

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				tree[size++] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 0;
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (idx == 0) { // root
					sum[idx] = tree[idx++];
					if (i == n) {
						ans = Math.max(ans, sum[idx-1]);
					}

				} else if (0 < j && j < i - 1) {
					sum[idx] = Math.max(sum[idx], Math.max(sum[idx - i] + tree[idx], sum[idx - i + 1] + tree[idx++]));
					if (i == n) {
						ans = Math.max(ans, sum[idx-1]);
					}
				} else if (0 < j) { // 왼쪽 부모는 있음
					sum[idx] = Math.max(sum[idx], sum[idx - i] + tree[idx++]);
					if (i == n) {
						ans = Math.max(ans, sum[idx-1]);
					}
				} else { // 오른쪽 부모는 있음
					sum[idx] = Math.max(sum[idx], sum[idx - i + 1] + tree[idx++]);
					if (i == n) {
						ans = Math.max(ans, sum[idx-1]);
					}
				}
			}
		}

		System.out.println(ans);
	}

}
