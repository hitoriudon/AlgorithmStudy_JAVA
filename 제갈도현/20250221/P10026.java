import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P10026 {
	static int N, cnt = 0, rgCnt = 0;
	static char[][] paint;
	static boolean[][] visited;
	static ArrayDeque<int[]> q = new ArrayDeque<>();
	static int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	static void bfs(char color, boolean isRG) {
		int x, y, nx, ny;
		int[] t;

		while (!q.isEmpty()) {
			t = q.removeFirst();
			x = t[0];
			y = t[1];

			for (int[] d : dir) {
				nx = x + d[0];
				ny = y + d[1];
				
				// 옆 좌표가 범위 내이고, 방문한 적 없는 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					// 적녹색약 + 현재 칸이 R 또는 G인 경우
					if (isRG && (color == 'R' || color == 'G')) {
						// 다음 칸이 R 또는 G인 경우
						if (paint[nx][ny] == 'R' || paint[nx][ny] == 'G') {
							q.addLast(new int[] { nx, ny });
							visited[nx][ny] = true;
						}
					} else if (paint[nx][ny] == color) {
						q.addLast(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		paint = new char[N][N];
		visited = new boolean[N][N];

		// 그림 정보 입력
		for (int i = 0; i < N; i++) {
			paint[i] = br.readLine().toCharArray();
		}

		// 그림 영역 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					q.addLast(new int[] { i, j });
					visited[i][j] = true;
					bfs(paint[i][j], false);
					cnt++;
				}
			}
		}
		
		visited = new boolean[N][N];

		// 적녹색약 그림 영역 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					q.addLast(new int[] { i, j });
					visited[i][j] = true;
					bfs(paint[i][j], true);
					rgCnt++;
				}
			}
		}

		System.out.printf("%d %d", cnt, rgCnt);
	}
}