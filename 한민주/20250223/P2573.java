import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2573 {
	static int n, m;
	static boolean isBreak;
	static int[][] map;
	static ArrayDeque<int[]> ices = new ArrayDeque<int[]>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

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
				if (map[i][j] != 0)
					ices.add(new int[] { i, j });
			}
		}

		int time = 0;
		while (!ices.isEmpty()) {
			time++;
			melt();
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			if(ices.isEmpty()) {
				time = 0;
				break;
			}
			iceCheck();

			if (isBreak)
				break;
		}
		System.out.println(time);
	}

	// 얼음이 녹는다
	static void melt() {
		int cnt = 0;
		int iceCnt = ices.size();
		int temp[] = new int[iceCnt+1];


		while (iceCnt != cnt) {
			int[] xy = ices.pollFirst();
			int x = xy[0];
			int y = xy[1];
			cnt++;

			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];

				if (inRange(newX, newY) && map[newX][newY] == 0) {
					temp[cnt]++;
				}
			}
			ices.add(new int[] { x, y });
		}


		for (int i = 1; i <= iceCnt; i++) {
			int[] xy = ices.pollFirst();
			map[xy[0]][xy[1]] = Math.max(map[xy[0]][xy[1]] - temp[i], 0);

			if (map[xy[0]][xy[1]] != 0)
				ices.add(new int[] { xy[0], xy[1] });
		}
	}

	// 얼음 쪼개졌는지 보기
	static void iceCheck() {
		int xy[] = ices.getFirst();
		int cnt = 0;

		ArrayDeque<int[]> temp = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[n][m];
		temp.add(xy);
		visited[xy[0]][xy[1]] = true;

		while (!temp.isEmpty()) {
			xy = temp.pollFirst();
			int x = xy[0];
			int y = xy[1];
			cnt++;
			
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];
				
				if (inRange(newX, newY) && map[newX][newY] != 0 && !visited[newX][newY]) {
					temp.add(new int[] { newX, newY });
					visited[newX][newY] = true;
				}
			}
		}

		if (cnt != ices.size()) {
			isBreak = true;
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < n && y < m;
	}
}
