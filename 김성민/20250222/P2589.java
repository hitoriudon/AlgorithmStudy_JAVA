import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2589 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static char[][] map;
	static boolean[][] visited;
	static int y;
	static int x;
	static int maxLength = 0;

	// 범위안에 있는지 체크
	static boolean isRange(int curY, int curX) {
		return (curY >= 0 && curY < y && curX >= 0 && curX < x);
	}

	static void bfs(int startY, int startX) {
		Queue<int[]> LQueue = new LinkedList<>();
		visited[startY][startX] = true;
		LQueue.add(new int[] { startY, startX, 0 });

		while (!LQueue.isEmpty()) {
			int[] curL = LQueue.remove();
			int curY = curL[0];
			int curX = curL[1];
			int curLength = curL[2];

			// 현재 길이가 더 길다면 바꾸기
			maxLength = Math.max(maxLength, curLength);

			for (int dir = 0; dir < 4; dir++) {
				int nextY = curY + dy[dir];
				int nextX = curX + dx[dir];

				// 범위 안에 있으며 방문하지 않았고 육지인 경우 큐에 추가
				if (isRange(nextY, nextX) && !visited[nextY][nextX] && map[nextY][nextX] == 'L') {
					visited[nextY][nextX] = true;
					LQueue.add(new int[] { nextY, nextX, curLength + 1 });
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		map = new char[y][x];

		for (int curY = 0; curY < y; curY++) {
			String line = br.readLine();
			for (int curX = 0; curX < x; curX++) {
				map[curY][curX] = line.charAt(curX);
			}
		}

		for (int curY = 0; curY < y; curY++) {
			for (int curX = 0; curX < x; curX++) {
				if (map[curY][curX] == 'L') {
//					int nearLCount = 0;
//					// 주위에 몇개의 L이 있는지 확인
//					for (int dir = 0; dir < 4; dir++) {
//						int nextY = curY + dy[dir];
//						int nextX = curX + dx[dir];
//
//						if (isRange(nextY, nextX) && map[nextY][nextX] == 'L') {
//							nearLCount++;
//						}
//					}
//					
//					// 주위에 L이 2개 이상이라면 중간 다리 역할이므로 스킵
//					if(nearLCount > 1) {
//						continue;
//					}
					
					// 길 새로 탐색 + 방문 초기화
					visited = new boolean[y][x];
					bfs(curY, curX);
				}
			}
		}

		System.out.println(maxLength);
	}
}