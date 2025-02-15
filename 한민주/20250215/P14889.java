package no;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P14889 {
	static int n, ans = Integer.MAX_VALUE;
	static int[][] arr;
	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		for (int i = 0; i < 1 << n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					list.add(j);
				}
			}
			if (list.size() == n / 2) {
//				for(int in: list) {
//					System.out.print(in + " ");
//				}
//				System.out.println();
				int sum = 0;
				for (int k = 0; k < list.size(); k++) {
					for (int l = 0; l < list.size(); l++) {
						sum += arr[list.get(k)][list.get(l)];
					}
				}

				for (int k = 0; k < n; k++) {
					for (int l = 0; l < n; l++) {
						if (list.contains(k) || list.contains(l))
							continue;
						sum -= arr[k][l];
					}
				}
				ans = Math.min(ans, Math.abs(sum));
			}
			list.clear();
		}

		System.out.println(ans);

	}

}
