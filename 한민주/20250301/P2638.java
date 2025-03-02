import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2638 {
	static Deque<int[]> deque = new ArrayDeque<int[]>();
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int n, m;

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < n && y < m;
	}

	static int cheese() {
		int cnt = deque.size();
		int now = 0;
		int nextCnt = 0;
		int time = 0;
		Deque<int[]> noCheese = new ArrayDeque<int[]>();
		while (!deque.isEmpty()) {
			now++;
			int[] xy = deque.pollFirst();
			int x = xy[0];
			int y = xy[1];
			int hp = 2;
			// 녹아 없어진 치즈 확인
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];
				if (map[newX][newY] == -1) {
					hp--;
				}
				if (hp == 0) {
					noCheese.add(xy);
					break;
				}
			}

			if (hp > 0) {
				deque.add(xy);
				nextCnt++;
			}

			if (now == cnt) {
				cnt = nextCnt;
				time++;
				now = 0;
				nextCnt = 0;

				int temp[];
				int tempX, tempY;
				// 녹은 치즈 중심으로 외부 공기 업데이트
				while (!noCheese.isEmpty()) {
					temp = noCheese.pollFirst();
					tempX = temp[0];
					tempY = temp[1];
					map[tempX][tempY] = -1;
					outside(tempX, tempY);
				}
			}
		}
		return time;
	}

	static void outside(int x, int y) {
		for (int dir = 0; dir < 4; dir++) {
			int newX = x + dx[dir];
			int newY = y + dy[dir];

			if (inRange(newX, newY) && map[newX][newY] == 0) {
				map[newX][newY] = -1;
				outside(newX, newY);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					deque.add(new int[] { i, j }); // cheese!
				}
			}
		}

		// 외부 공기 확인
		map[0][0] = -1;
		outside(0, 0);

		// cheese 없애기
		System.out.println(cheese());
	}

}
