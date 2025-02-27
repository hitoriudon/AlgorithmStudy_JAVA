import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2146 {

	static int n;
	static int[][] map;
	static int firstLandCnt;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int initializedNum;
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < n; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// Chk
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
//				System.out.print(map[row][col] + " ");
			}
//			System.out.println();
		}

		// Alg : 각 모든 섬에 대해 섬 하나를 선택해서(-1로 표시) 확장
		// 저장 후 최소값 계산
		// 1. BFS를 통해 섬 개수 세면서 번호 매김
		initializeLand();

		res = Integer.MAX_VALUE;

		// 2. 배열 복사 후 각 섬에 대해 확장 하면서 개수 체크
		for (int curLand = 2; curLand < 2 + firstLandCnt; curLand++) {
			res = Math.min(res, expandLand(curLand));
		}

		System.out.println(res);
	}

	// 1. BFS를 통해 섬 개수 세면서 번호 매김
	static void initializeLand() {
		initializedNum = 2;
		firstLandCnt = 0;

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (map[row][col] == 1) {
					initialize(row, col);
					firstLandCnt++;
				}
			}
		}

		
//		System.out.println("\n번호 매긴 후 ");
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
//				System.out.print(map[row][col] + " ");
			}
//			System.out.println();
		}

//		System.out.println("초기 섬 수 : " + firstLandCnt);
	}

	static void initialize(int row, int col) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n][n];

		q.offer(new int[] { row, col });
		visit[row][col] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			map[curRow][curCol] = initializedNum;

			for (int i = 0; i < 4; i++) {
				int nx = curRow + dx[i];
				int ny = curCol + dy[i];

				if (nx >= 0 && nx < n && ny >= 0 && ny < n && visit[nx][ny] == false && map[nx][ny] != 0) {
					q.offer(new int[] { nx, ny });
					visit[nx][ny] = true;
				}

			}
		}

		initializedNum++;
	}

	// 2. 배열 복사 후 각 섬에대해 상하좌우 1씩 확장 -> 연결될때까지 확장된 횟수 반환
	static int expandLand(int curLand) {
		int[][] copiedMap = new int[n][n];
		int cnt = 0;

		// 섬 복사
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				copiedMap[row][col] = map[row][col];
			}
		}

//		System.out.println("\n 현재섬 >>>>>" + curLand);


		// 섬 확장
		out: while (true) {
			
			ArrayDeque<int[]> jibae = new ArrayDeque<>();
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					if (copiedMap[row][col] == curLand) { // 지배자들의 땅에 사는사람들
						for (int i = 0; i < 4; i++) {
							int nx = row + dx[i];
							int ny = col + dy[i];

							if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
								if (copiedMap[nx][ny] == 0) { // 확장의 기회
									jibae.offer(new int[] {nx,ny});
								} else if (copiedMap[nx][ny] != curLand) {// 남의 땅!!
									
//									System.out.println(">>>>>" + curLand);
//									System.out.println("확장 이후");
									for (int row1 = 0; row1 < n; row1++) {
										for (int col1 = 0; col1 < n; col1++) {
//											System.out.print(copiedMap[row1][col1] + " ");
										}
//										System.out.println();
									}
									
									break out;
								}
							}
						}
					}
				}
			}

			while(!jibae.isEmpty()) {
				int [] jijibae = jibae.poll();
				copiedMap[jijibae[0]][jijibae[1]] = curLand; // 침바르기
			}
			cnt++;

		}

//		System.out.println("WOW! 섬" + curLand + "는 "+ cnt + "번 만에!!!!");
		return cnt;
	}
}