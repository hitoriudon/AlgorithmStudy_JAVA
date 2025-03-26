package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P25682 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp[] = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		int k = Integer.parseInt(temp[2]);

		boolean chess[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				if (input.charAt(j) == 'B') {
					chess[i][j] = false;
				} else {
					chess[i][j] = true;
				}
			}
		}

		int prefix[][][] = new int[n - k + 1][m][2];
		int minSum = k * k;

		int preSum[] = new int[2];

		for (int i = 0; i < m; i++) {
			// 0, 0이 검은색일 때 해당 칸의 색
			boolean colorB = true; // 흰색
			if (i % 2 == 0) {
				colorB = false; // 검은색
			}

			for (int j = 0; j < k; j++) {
				if (colorB == chess[j][i]) { // 검은색 시작과 색이 같다면
					prefix[0][i][1]++; // 하얀색 시작일 때 고쳐야 하는 정사각형 수 ++
				} else {
					prefix[0][i][0]++;
				}
				colorB = !colorB; // 컬러 바꿔주기
			}

			preSum[0] += prefix[0][i][0];
			preSum[1] += prefix[0][i][1];

			if (i >= k) {
				preSum[0] -= prefix[0][i - k][0];
				preSum[1] -= prefix[0][i - k][1];

				minSum = Math.min(minSum, Math.min(preSum[0], preSum[1]));
			} else if (i == k - 1) {
				minSum = Math.min(preSum[0], preSum[1]);
			}
			if (minSum == 0) {
				System.out.println(0);
				return;
			}
		}

		for (int i = 1; i < n - k + 1; i++) {
			preSum[0] = 0;
			preSum[1] = 0;
			for (int j = 0; j < m; j++) {
				prefix[i][j][0] = prefix[i - 1][j][0];
				prefix[i][j][1] = prefix[i - 1][j][1];
				if ((!chess[i - 1][j] && (i - 1) % 2 == j % 2) || ((chess[i - 1][j]) && (i - 1) % 2 != j % 2)) {
					prefix[i][j][1]--;
				} else {
					prefix[i][j][0]--;
				}
				if ((!chess[i + k - 1][j] && (i + k -1) % 2 == j % 2) || ((chess[i + k - 1][j]) && (i + k -1) % 2 != j % 2)) {
					prefix[i][j][1]++;
//					System.out.println(i + " " + j + " : " + (!chess[i + k - 1][j] && i % 2 == j % 2));
				} else {
					prefix[i][j][0]++;
				}

				preSum[0] += prefix[i][j][0];
				preSum[1] += prefix[i][j][1];
				if (j >= k) {
					preSum[0] -= prefix[i][j - k][0];
					preSum[1] -= prefix[i][j - k][1];

					minSum = Math.min(minSum, Math.min(preSum[0], preSum[1]));
				} else if (j == k - 1) {
					minSum = Math.min(minSum, Math.min(preSum[0], preSum[1]));
				}
				if (minSum == 0) {
					System.out.println(0);
					return;
				}
			}

		}

//		for (int i = 0; i < n - k + 1; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(prefix[i][j][0] + "," + prefix[i][j][1] + "  |  ");
//			}
//			System.out.println();
//		}
		System.out.println(minSum);

	}
}
