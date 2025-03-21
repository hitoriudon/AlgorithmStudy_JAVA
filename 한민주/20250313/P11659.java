package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int arr[] = new int[n];
		st = new StringTokenizer(br.readLine());

		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < n; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			if (s == 0) {
				sb.append(arr[e]);
			} else {
				sb.append(arr[e] - arr[s - 1]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
