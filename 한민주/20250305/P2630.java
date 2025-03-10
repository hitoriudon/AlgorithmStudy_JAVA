package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
	static int answer[] = new int[2];
	static int paper[][];

	static void cut(int sx, int sy, int len) {
		boolean need = false;
		int color = paper[sx][sy];

		for (int i = sx; i < sx + len; i++) {
			for (int j = sy; j < sy + len; j++) {
				if (color != paper[i][j]) {
					need = true;
					break;
				}
			}
		}

		if (need) {
			cut(sx, sy, len / 2);
			cut(sx + len / 2, sy, len / 2);
			cut(sx, sy + len / 2, len / 2);
			cut(sx + len / 2, sy + len / 2, len / 2);
		} else {
			answer[color]++;
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

		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
}