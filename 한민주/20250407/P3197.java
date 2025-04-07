package algorithm;

// 메모리: 111804 KB, 시간: 788ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Water {
	int x;
	int y;
	Water root;
	boolean iced;

	public Water(int x, int y, boolean iced) {
		this.x = x;
		this.y = y;
		this.root = this;
		this.iced = iced;
	}
}

public class P3197 {
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static int r, c;
	static ArrayDeque<Water> ices;
	static boolean visited[][];
	static Water lake[][];
	static Water[] whites;

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < r && y < c;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visited = new boolean[r][c];
		lake = new Water[r][c];

		whites = new Water[2];

		for (int i = 0; i < r; i++) {
			String input = br.readLine();
			for (int j = 0; j < c; j++) {
				if (input.charAt(j) == '.') {
					lake[i][j] = new Water(i, j, false);
				} else if (input.charAt(j) == 'X') {
					lake[i][j] = new Water(i, j, true);
				} else {
					lake[i][j] = new Water(i, j, false);
					if (whites[0] == null) {
						whites[0] = lake[i][j];
					} else {
						whites[1] = lake[i][j];
					}
				}
			}
		}

		ices = new ArrayDeque<Water>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!lake[i][j].iced && !visited[i][j]) {
					visited[i][j] = true;
					findWater(i, j);
				}
			}
		}

		// 이미 만날 수 있으면 조기 종료
		if (check()) {
			System.out.println(0);
			return;
		}

		int day = 1;
		int size = ices.size();
		int nextSize = 0;
		int cnt = 0;

		while (!check() && !ices.isEmpty()) {
			Water now = ices.poll();
			now.iced = false;
			cnt++;
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];
				if (inRange(nx, ny)) {
					if (!lake[nx][ny].iced) {
						if (now.root.equals(now)) {
							now.root = find(lake[nx][ny]);
						} else {
							find(now);
							find(lake[nx][ny]);
							now.root.root = lake[nx][ny].root;
							if (check()) {
								System.out.println(day);
								return;
							}
						}
					} else if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						ices.add(lake[nx][ny]);
						nextSize++;
					}
				}
			}
			if (size == cnt) {
//				System.out.println("dau" + day);
				day++;
				size = nextSize;
				nextSize=0;
//				System.out.println(size);
				cnt = 0;
			}
		}
	}

	static void findWater(int x, int y) {
		for (int dir = 0; dir < 4; dir++) {
			int nx = dx[dir] + x;
			int ny = dy[dir] + y;

			if (inRange(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				if (!lake[nx][ny].iced) {
					lake[nx][ny].root = lake[x][y].root;
					findWater(nx, ny);
				} else {
					ices.add(lake[nx][ny]);
				}
			}
		}
	}

	static boolean check() {
		find(whites[0]);
		find(whites[1]);
		return whites[0].root.equals(whites[1].root);
	}

	static Water find(Water w) {
		if (w.root.equals(w))
			return w;
		else
			return w.root = find(w.root);
	}
}
