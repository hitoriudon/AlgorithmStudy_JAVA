package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class g4_1987 {
	static int R, C, result = 0;
	static char[][] board;
	// 알파벳이 사용되었는지 체크하는 boolean 배열
	static boolean[] isUsed = new boolean[26];
	static int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	static void move(int x, int y, int steps) {
		result = Math.max(result, steps);

		int nx, ny;
		for (int[] d : dir) {
			nx = x + d[0];
			ny = y + d[1];

			// 다음 칸이 범위를 넘지 않는 경우
			if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
				// 만약 사용하지 않은 알파벳이라면
				if (!isUsed[board[nx][ny] - 'A']) {
					isUsed[board[nx][ny] - 'A'] = true;
					move(nx, ny, steps + 1);
					isUsed[board[nx][ny] - 'A'] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][];
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}

		// 시작점 방문 체크
		isUsed[board[0][0] - 'A'] = true;
		move(0, 0, 1);

		System.out.println(result);
	}
}
