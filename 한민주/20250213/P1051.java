package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1051 {
	public static void main(String[] args) throws Exception {
		int n, m, answer = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		String input;
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			input = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = input.charAt(j) - '0';
			}
		}

		int x, y;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				x = i + 1;
				y = j + 1;

				while (0 <= x && 0 <= y && x < n && y < m) {
					if (answer < (x - i + 1) * (x - i + 1) && arr[x][y] == arr[i][j] && arr[x][j] == arr[i][j]
							&& arr[i][y] == arr[i][j]) {
						answer = (x - i + 1) * (x - i + 1);
					}

					x++;
					y++;

				}
			}
		}

		System.out.println(answer);
	}

}
