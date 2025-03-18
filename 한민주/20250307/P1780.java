package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
	static int paper[][];
	static int answer[] = new int[3];

	static void cut(int sx, int sy, int len) {
		int num = paper[sx][sy];
		boolean need = false;
		for (int i = sx; i < sx + len; i++) {
			for (int j = sy; j < sy + len; j++) {
				if (num != paper[i][j]) {
					need = true;
					break;
				}
			}
			if (need)
				break;
		}

		if (need) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cut(sx + len / 3 * i, len / 3 * j + sy, len / 3);
				}
			}
		} else {
			answer[num + 1]++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cut(0, 0, n);

		for (int i = 0; i < 3; i++) {
			System.out.println(answer[i]);
		}

	}
}
