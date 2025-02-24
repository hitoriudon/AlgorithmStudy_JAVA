import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
[7576, 토마토]
 */

public class P7576 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken()); // 상자 가로칸 수 6
		int n = Integer.parseInt(st.nextToken()); // 상자 세로칸 수 4

		int[][] arr = new int[n][m];

		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < m; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(arr[row]));
		}

//		BFS
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][] visit = new int[n][m];

		for (int row = 0; row < n; row++) { // 1인 토마토 찾아서 q에 넣음
			for (int col = 0; col < m; col++) {
				if (arr[row][col] == 1) {
					q.offer(new int[] { row, col });
					visit[row][col] = 1;
//					System.out.print("1 row : " + row);
//					System.out.println("1 col : " + col);
				}
			}
		}

		int[] dx = new int[] { 0, 0, 1, -1 };
		int[] dy = new int[] { 1, -1, 0, 0 };

//		큐의 상하좌우 탐색 0이면 1로, -1이면 pass

		int res = 0;

		while (!q.isEmpty()) {
//			System.out.println("cur Q : " + q.peek()[0] + " " + q.peek()[1]);
			int[] cur = q.poll();
			int curRow = cur[0];
			int curCol = cur[1];

			if (arr[curRow][curCol] == 0) { // 현재 값이 0인 경우
				arr[curRow][curCol] = 1;
			}

			// 상하좌우 한번 탐색
			for (int i = 0; i < 4; i++) {
				int nx = curRow + dx[i];
				int ny = curCol + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (visit[nx][ny] == 0) {
						if (arr[nx][ny] == 0) {
							visit[nx][ny] = visit[curRow][curCol] + 1;
							q.offer(new int[] { nx, ny });
							res = Math.max(res, visit[nx][ny]);
						}
					}
				}

			}

		}

//		탐색 완료후 0이 없는경우 res 출력, 아니면 -1 출력
		boolean possible = true;
		out: for (int row = 0; row < n; row++) { // 1인 토마토 찾아서 q에 넣음
			for (int col = 0; col < m; col++) {
				if (arr[row][col] == 0) {
//					System.out.println("because of : " + row + ", " + col);
					possible = false;
					break out;
				}
			}
		}
		System.out.println(possible ? Math.max(res - 1, 0) : -1); // 첫째날은 빼야함
	}
}