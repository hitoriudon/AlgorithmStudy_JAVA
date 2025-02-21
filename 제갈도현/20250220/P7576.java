package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P7576 {
	static int[][] tomato;
	static ArrayDeque<int[]> q = new ArrayDeque<>();

	static void bfs(int N, int M) {
		int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
		int[] t;
		int x, y, nx, ny;

		while (!q.isEmpty()) {
			t = q.removeFirst();
			x = t[0];
			y = t[1];

			for (int[] d : dir) {
				nx = x + d[0];
				ny = y + d[1];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 옆에 안 익은 토마토가 있다면 전파
					if (tomato[nx][ny] == 0) {
						// -1은 빈 칸, 0은 안 익은 토마토, 그 외는 익은 토마토
						// 익은 토마토의 숫자는 날짜를 의미
						tomato[nx][ny] = tomato[x][y] + 1;
						q.addLast(new int[] { nx, ny });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 토마토 상자 크기 저장
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		// 토마토 상자 상태 저장
		tomato = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				// 익은 토마토가 들어가 있다면 좌표를 큐에 저장
				if (tomato[i][j] == 1) {
					q.addLast(new int[] { i, j });
				}
			}
		}

		bfs(N, M);

		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 안 익은 토마토가 있다면 후숙 실패!
				if (tomato[i][j] == 0) {
					System.out.print(-1);
					return;
				} else {
					res = Math.max(res, tomato[i][j]);
				}
			}
		}

		// 기본적으로 주어진 익은 토마토는 0일차
		System.out.print(res - 1);
	}
}