import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P15686 {
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int answer = Integer.MAX_VALUE;

		int[][] map = new int[n][n];
		List<int[]> chickens = new ArrayList<int[]>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chickens.add(new int[] { i, j });
				}
			}
		}

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		for (int i = 1; i < 1 << chickens.size(); i++) {
			Deque<int[]> q = new ArrayDeque<>();
			for (int j = 0; j < chickens.size(); j++) {
				if (((1 << j) & i) != 0) {
					q.add(chickens.get(j));
				}
			}

			if (q.size() > m) {
				continue;
			}
//			System.out.println();
			boolean[][] visited = new boolean[n][n];
			int total = 0;
			int dis = 1;
			int cnt = q.size();
			int now = 0;
			int nextCnt = 0;
			while (!q.isEmpty()) {
				int[] xy = q.pollFirst();
				int x = xy[0];
				int y = xy[1];
				visited[x][y] = true;
				now++;

//				System.out.println(x + " " + y + " : " + dis);
				for (int dir = 0; dir < 4; dir++) {
					int newX = x + dx[dir];
					int newY = y + dy[dir];
					if (inRange(newX, newY) && !visited[newX][newY]) {
						nextCnt++;
						visited[newX][newY] = true;
						if (map[newX][newY] == 1) {
							total += dis;
						}
						q.add(new int[]{ newX, newY });
					}
				}

				if (now == cnt) {
					cnt = nextCnt;
					nextCnt = 0;
					now = 0;
					dis++;
				}
			}

			answer = Math.min(answer, total);
		}

		System.out.println(answer);
	}

	static boolean inRange(int x, int y) {
		// TODO Auto-generated method stub
		return 0 <= x && 0 <= y && x < n && y < n;
	}

}
