import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2589 {
	static ArrayDeque<int[]> q;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int w, h;
	static int[][] map;
	static boolean visited[][];
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			String input = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = input.charAt(j) == 'L' ? 1 : 0;
			}
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 1) {
					q = new ArrayDeque<int[]>();
					visited = new boolean[h][w];
					visited[i][j] = true;
					q.add(new int[] { i, j });
					bfs();
				}
			}
		}

		System.out.println(answer);
	}

	static void bfs() {
		// TODO Auto-generated method stub
		int time = 0;
		int cnt = 1;
		int nextCnt = 0;
		int now = 0;
		while (!q.isEmpty()) {

			int[] xy = q.pollFirst();
			int x = xy[0];
			int y = xy[1];

			now++;
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];

				if (inRange(newX, newY) && map[newX][newY] == 1 && !visited[newX][newY]) {
					q.add(new int[] { newX, newY });
					visited[newX][newY] = true;
					nextCnt++;
				}
			}

			if (cnt == now) {
				cnt = nextCnt;
				nextCnt = 0;
				now = 0;
				if (q.isEmpty())
					break;
				time++;
			}
		}
		answer = Math.max(answer, time);
	}

	static boolean inRange(int newX, int newY) {
		// TODO Auto-generated method stub
		return 0 <= newX && 0 <= newY && newX < h && newY < w;
	}

}
