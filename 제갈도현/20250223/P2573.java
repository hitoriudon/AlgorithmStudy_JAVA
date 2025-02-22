import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2573 {
	static int N, M;
	static int[][] glacier;
	static int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	static int glacierChecker() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];

		int sumGlacier = 0;
		boolean firstGalcier = false;

		// 빙하인 경우 큐에 좌표 하나만 저장
		// 빙하가 하나일 경우 bfs를 통해 다 이어지기 때문
		out: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (glacier[i][j] != 0) {
					// 첫 빙하 정보 저장
					if (!firstGalcier && glacier[i][j] != 0) {
						firstGalcier = true;
						isVisited[i][j] = true;
						q.offer(new int[] { i, j });
					}

					// 빙하 체크용
					sumGlacier += glacier[i][j];
				}
			}
		}

		// 빙하가 다 녹은 경우
		if (sumGlacier == 0)
			return -1;

		int nx, ny;
		int[] currGlacier;
		while (!q.isEmpty()) {
			currGlacier = q.poll();

			for (int[] d : dir) {
				nx = currGlacier[0] + d[0];
				ny = currGlacier[1] + d[1];

				// 주변 칸이 영역 내이고, 바다인 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !isVisited[nx][ny] && glacier[nx][ny] != 0) {
					isVisited[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// bfs로 방문하지 않은 빙하가 있다면 찢어진 것
				if (!isVisited[i][j] && glacier[i][j] != 0) {
					return 0;
				}
			}
		}

		return 1;
	}

	static void melt() {
		int[][] melted = new int[N][M];
		int sea;
	    
		// 녹은 빙하(0)가 다른 빙하 체크에 영향을 주지 않도록 한 번에 작업
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if (glacier[i][j] != 0) {
	            	// 주변 바다 체크
	                sea = 0;
	                
	                for (int[] d : dir) {
	                    int nx = i + d[0];
	                    int ny = j + d[1];
	                    
	                    // 옆 칸이 범위를 벗어나지 않고 바다인 경우
	                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && glacier[nx][ny] == 0) {
	                        sea++;
	                    }
	                }
	                
	                // 0보다 작아지면 0으로 수정
	                melted[i][j] = Math.max(0, glacier[i][j] - sea);
	            }
	        }
	    }
	    
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            glacier[i][j] = melted[i][j];
	        }
	    }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int year = 0, status;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 빙하 정보 입력
		glacier = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				glacier[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// 녹는 빙하 체크
			melt();
			year++;

			// 빙하 덩어리 체크
			status = glacierChecker();
			if (status == -1) {
				// 빙하가 다 녹았다면 0
				System.out.println(0);
				return;
			} else if (status == 0) {
				System.out.println(year);
				return;
			}
		}
	}
}