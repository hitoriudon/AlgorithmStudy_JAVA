import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2589 {
	static int row, col;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];

		for (int r = 0; r < row; r++) {
			String temp = br.readLine();
			for (int c = 0; c < col; c++) {
				map[r][c] = temp.charAt(c);
			}
		}

		int res = 0;

		// 모든 L BFS 탐색
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (map[r][c] == 'L') {
					res = Math.max(res, bfs(r, c));
				}
			}
		}
		System.out.println(res);
	}

	static int bfs(int startR, int startC) {
		boolean[][] visited = new boolean[row][col];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startR, startC, 0 });
		visited[startR][startC] = true;
		int maxDist = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			int val = cur[2];

			maxDist = Math.max(maxDist, val);

			for (int i = 0; i < 4; i++) {
				int nx = curRow + dx[i];
				int ny = curCol + dy[i];

				if (nx >= 0 && nx < row && ny >= 0 && ny < col && map[nx][ny] == 'L' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny, val + 1 });
				}
			}
		}

		return maxDist;
	}
}