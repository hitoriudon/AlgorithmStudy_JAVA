import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P7576 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m, n;
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		int[][] box = new int[n][m];
		int totalTo = 0;

		Deque<int[]> q = new ArrayDeque<int[]>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					q.add(new int[] { i, j });
				} else if (box[i][j] == 0) {
					totalTo++;
				}
			}
		}

		int day = 1;
		int dx[] = { 0, 0, -1, 1 };
		int dy[] = { -1, 1, 0, 0 };
		int cnt = q.size();
		int now = 0;
		int nextCnt = 0;

		while (!q.isEmpty()) {
			int[] xy = q.pollFirst();
			int x = xy[0];
			int y = xy[1];

			now++;
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dx[dir];
				int newY = y + dy[dir];

				if (0 <= newX && 0 <= newY && newX < n && newY < m) {
					if (box[newX][newY] == 0) {
						box[newX][newY] = 1;
						totalTo--;
						nextCnt++;
						q.add(new int[] { newX, newY });
					}
				}
			}

			if(totalTo == 0) break;
			if (now == cnt) {
				cnt = nextCnt;
				day++;
				nextCnt = 0;
				now = 0;
			}
		}

		if (totalTo != 0) {
			day = -1;
		}

		System.out.println(day);
	}

}
