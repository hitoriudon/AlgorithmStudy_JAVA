package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11441 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[n+1];
		arr[1] = Integer.parseInt(st.nextToken());
		for (int i = 2; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
		}

		int m = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (s > 0) {
				sb.append(arr[e] - arr[s - 1]);
			} else {
				sb.append(arr[e]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
