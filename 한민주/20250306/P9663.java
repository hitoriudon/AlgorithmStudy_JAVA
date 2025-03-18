package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9663 {
	static int n;
	static boolean visited1[]; // y
	static boolean visited2[]; // 왼 - 오 대각선
	static boolean visited3[]; // 오 - 왼 대각선
	static int ans = 0;

	static void chess(int num) {
		if (num == n) {
			ans++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited1[i] && !visited2[num - i + n] && !visited3[num + i]) {
				visited1[i] = true;
				visited2[num - i + n] = true;
				visited3[num + i] = true;
				chess(num + 1);
				visited1[i] = false;
				visited2[num - i + n] = false;
				visited3[num + i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		visited1 = new boolean[n]; // y
		visited2 = new boolean[n * 2]; // 왼 - 오 대각선
		visited3 = new boolean[n * 2]; // 오 - 왼 대각선

		chess(0);
		System.out.println(ans);
	}
}
