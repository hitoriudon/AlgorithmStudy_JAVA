package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1992 {
	static int video[][];
	static StringBuilder sb = new StringBuilder();

	static void zip(int sx, int sy, int len) {
		int color = video[sx][sy];
		boolean need = false;

		for (int i = sx; i < sx + len; i++) {
			for (int j = sy; j < sy + len; j++) {
				if (color != video[i][j]) {
					need = true;
					break;
				}
			}
		}

		if (need) {
			sb.append("(");
			zip(sx, sy, len / 2);
			zip(sx, sy + len / 2, len / 2);
			zip(sx + len / 2, sy, len / 2);
			zip(sx + len / 2, sy + len / 2, len / 2);
			sb.append(")");
		} else {
			sb.append(color);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		video = new int[n][n];
		String input;
		for (int i = 0; i < n; i++) {
			input = br.readLine();
			for (int j = 0; j < n; j++) {
				video[i][j] = input.charAt(j) - '0';
			}
		}

		zip(0, 0, n);

		System.out.println(sb);
	}

}
