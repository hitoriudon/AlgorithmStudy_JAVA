import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2589 {
	static int N, M, res = 0;
	static char[][] map;
	static boolean[][] isVisited;
	static ArrayDeque<int[]> q = new ArrayDeque<>();
	static int[][] dir = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	static void bfs() {
		int[] thisArr;
		int x, y, cnt, nx, ny, maxCnt = 0;

		while (!q.isEmpty()) {
			thisArr = q.removeFirst();
			x = thisArr[0];
			y = thisArr[1];
			cnt = thisArr[2];
			
			// 최단 거리로 이동한 가장 먼 거리 저장
			maxCnt = Math.max(maxCnt, cnt);

			for (int[] d : dir) {
				nx = x + d[0];
				ny = y + d[1];

				// nx, ny가 범위 내이며, 다음 칸이 땅이고 방문하지 않은 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 'L' && !isVisited[nx][ny]) {
					isVisited[nx][ny] = true;
					q.addLast(new int[] { nx, ny, cnt + 1 });
				}
			}
		}

		res = Math.max(res, maxCnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 지도 정보 저장
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 땅이라면 탐색 시작
				if (map[i][j] == 'L') {
					// 각 시작점에 따라 방문 배열 초기화
					isVisited = new boolean[N][M];
					// 시작 좌표를 큐에 넣고, 방문 처리
					q.addLast(new int[] { i, j, 0 });
					isVisited[i][j] = true;
					
					bfs();
				}
			}
		}

		System.out.println(res);
	}
}