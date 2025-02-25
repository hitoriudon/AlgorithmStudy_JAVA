import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2146 {
	static int n, answer = Integer.MAX_VALUE;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static ArrayDeque<int[]> deque = new ArrayDeque<>();
	static boolean inDeque[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		inDeque = new boolean[n][n];

		// 섬 파악 + 다리 시작 가능한 땅 deque에 넣기
		int num = 2; // 2빼면 섬 개수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					map[i][j] = num++;
					bfs(i, j);
				}
			}
		}
		
		bfsForBridge();
		System.out.println(answer);

	}

	static void bfsForBridge() {
		int cnt = deque.size();
		int nextCnt = 0;
		int now = 0;
		int length = 1;
		int visited[][][] = new int[n][n][2]; // 다리 개수, 시작섬번호
		while (!deque.isEmpty()) {
			int xy[] = deque.pollFirst(); // x, y, 섬번호
			int x = xy[0];
			int y = xy[1];
			now++;

			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];

				if (inRange(newX, newY)) {
					// 바다
					if (map[newX][newY] == 0) {
						if (visited[newX][newY][1] >= 2 && visited[newX][newY][1] != xy[2]) {
							// 이미 건설된 다리가 있고 시작 섬번호가 다른 섬이라면
							answer = Math.min(answer, length - 1 + visited[newX][newY][0]);
							
						} else if (visited[newX][newY][0] == 0) {
							// 아직 건설 전이라면
							visited[newX][newY][0] = length;
							visited[newX][newY][1] = xy[2];
							deque.add(new int[] { newX, newY, xy[2] });
							nextCnt++;
						}
					}
				}
			}

			if (now == cnt) {
				length++;
				cnt = nextCnt;
				nextCnt = 0;
				now = 0;
//				
//				for(int i = 0; i < n;i++) {
//					for(int j = 0; j < n; j++) {
//						System.out.print(visited[i][j][0] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		}
	}

	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { x, y });

		while (!q.isEmpty()) {
			int xy[] = q.pollFirst();
			int nowX = xy[0];
			int nowY = xy[1];

			for (int dir = 0; dir < 4; dir++) {
				int newX = nowX + dx[dir];
				int newY = nowY + dy[dir];

				if (inRange(newX, newY) && map[newX][newY] == 1) {
					map[newX][newY] = map[x][y];
					q.add(new int[] { newX, newY });
				}
				else if (inRange(newX, newY) && map[newX][newY] == 0 && !inDeque[nowX][nowY]) {
					deque.add(new int[] { nowX, nowY, map[x][y] });
					inDeque[nowX][nowY] = true;
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < n && y < n;
	}
}
