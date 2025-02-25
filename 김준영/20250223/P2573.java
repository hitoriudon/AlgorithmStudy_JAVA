
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 2573 빙산
// 목적 : 언제 두덩어리 이상으로 분리 되는가
public class Main {
	static int row, col;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] ice;
	static int year = 0;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		ice = new int[row][col];

		// 얼음 입력 받음
		for (int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < col; c++) {
				ice[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 얼음이 두조각 이상 될떄까지 녹이고 BFS로 탐색 반복
		while (true) {
			// 1년이 흐른 뒤
			year++;
			meltIce();

			int iceCount = countIce();
			if (iceCount >= 2) {
				System.out.println(year);
				return;
			}

			if (iceCount == 0) {
				System.out.println(0);
				return;
			}
		}
	}
	
	// 빙하 녹임 해당 점이 0인 경우 상하좌우 -1씩
	static void meltIce() {
		ArrayDeque<int[]> plz = new ArrayDeque<>();
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (ice[r][c] == 0) {
					plz.offer(new int[] { r, c });
				}
			}
		}

		while (!plz.isEmpty()) {
			int[] plzzz = plz.poll();
			int r = plzzz[0];
			int c = plzzz[1];
			for (int i = 0; i < 4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];
				if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
					ice[nx][ny] = Math.max(0, ice[nx][ny] - 1); 
				}
			}
		}
	}

	static int countIce() {
		int cnt = 0;
		visit = new boolean[row][col];
		
		for (int r1 = 0; r1 < row; r1++) {
			for (int c1 = 0; c1 < col; c1++) {
				visit[r1][c1] = ice[r1][c1] == 0 ? true : false;
			}
		}

		for (int r1 = 0; r1 < row; r1++) {
			for (int c1 = 0; c1 < col; c1++) {
//				System.out.print(visit[r1][c1] + " ");
			}
//			System.out.println();
		}

		// 해당 점이 0이 아닌 경우 영역 visit , 구역 개수만큼 ++
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (visit[r][c] == false) {
					cnt++;
					ArrayDeque<int[]> q = new ArrayDeque<>();
					q.offer(new int[] { r, c });

					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int curRow = cur[0];
						int curCol = cur[1];


						for (int i = 0; i < 4; i++) {
							int nx = curRow + dx[i];
							int ny = curCol + dy[i];

							if (nx >= 0 && nx < row && ny >= 0 && ny < col && visit[nx][ny] == false) {
								visit[nx][ny] = true;
								q.offer(new int[] { nx, ny });
							}
						}
					}
				}
			}
		}

		
		return cnt;
	}
}
