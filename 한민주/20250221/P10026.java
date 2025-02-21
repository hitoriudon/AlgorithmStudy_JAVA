import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P10026 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		char[][] arr = new char[n][n];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = input.charAt(j);
			}
		}

		boolean visited[][] = new boolean[n][n];
		Deque<int[]> q = new ArrayDeque<>();

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		int ans1 = 0;
		int ans2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					q.add(new int[] { i, j });
					ans1++;
				}

				while (!q.isEmpty()) {
					int[] xy = q.pollFirst();
					int x = xy[0];
					int y = xy[1];

					for (int dir = 0; dir < 4; dir++) {
						int newX = x + dx[dir];
						int newY = y + dy[dir];

						if (0 <= newX && 0 <= newY && newX < n && newY < n && arr[newX][newY] == arr[i][j]
								&& !visited[newX][newY]) {
							visited[newX][newY] = true;
							q.add(new int[] { newX, newY });
							if (arr[newX][newY] == 'G') {
								arr[newX][newY] = 'R';
							}
						}
					}
				}
				if (arr[i][j] == 'G') {
					arr[i][j] = 'R';
				}
			}
		}

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					q.add(new int[] { i, j });
					ans2++;
				}

				while (!q.isEmpty()) {
					int[] xy = q.pollFirst();
					int x = xy[0];
					int y = xy[1];

					for (int dir = 0; dir < 4; dir++) {
						int newX = x + dx[dir];
						int newY = y + dy[dir];

						if (0 <= newX && 0 <= newY && newX < n && newY < n && arr[newX][newY] == arr[i][j]
								&& !visited[newX][newY]) {
							visited[newX][newY] = true;
							q.add(new int[] { newX, newY });
						}
					}
				}
			}
		}

		System.out.println(ans1 + " " + ans2);
	}

}