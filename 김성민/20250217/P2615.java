import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2615 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int[][] map = new int[19][19];
		int[] dy = { 1, 0, 1, -1 };
		int[] dx = { 0, 1, 1, 1 };

		for (int y = 0; y < 19; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < 19; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				if (map[y][x] != 0) {
					int color = map[y][x];

					for (int dir = 0; dir < 4; dir++) {
						if (y - dy[dir] >= 0 && y - dy[dir] < 19 && x - dx[dir] >= 0 && x - dx[dir] < 19 && map[y - dy[dir]][x - dx[dir]] == color) {
							continue;
						}

						int curY = y;
						int curX = x;
						int count = 1;

						// 오른쪽 혹은 아래로 탐색
						while (true) {
							curY += dy[dir];
							curX += dx[dir];
							if (curY < 0 || curY >= 19 || curX < 0 || curX >= 19 || map[curY][curX] != color) {
								break;
							}
							count++;
						}

						// 바둑 돌 갯수가 5개 연속이라면
						if (count == 5) {
							sb.append(map[y][x]).append("\n").append(y + 1).append(" ").append(x + 1);
							System.out.println(sb);
							return;
						}

					}
				}
			}
		}

		// 아무도 이기지 않았을 경우
		System.out.println(0);

	}
}
