import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2146 {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int mapSize;
	static int[][] map;
	static boolean[][] visited;

	static boolean isRange(int y, int x) {
		return (y >= 0 && y < mapSize && x >= 0 && x < mapSize);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];

		for (int y = 0; y < mapSize; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < mapSize; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		// 각 섬 구별용 번호
		int islandNum = 2;
		for (int y = 0; y < mapSize; y++) {
			for (int x = 0; x < mapSize; x++) {
				// 처음 1을 만나면 Queue에 넣고 인접 육지 BFS 돌면서 번호 매기기
				if (map[y][x] == 1) {
					Queue<int[]> islandQue = new LinkedList<>();
					map[y][x] = islandNum;
					islandQue.add(new int[] { y, x });
					while (!islandQue.isEmpty()) {
						int[] curPoint = islandQue.remove();
						int curY = curPoint[0];
						int curX = curPoint[1];

						for (int dir = 0; dir < 4; dir++) {
							int nextY = curY + dy[dir];
							int nextX = curX + dx[dir];
							// 범위 안에 있고 육지인지 확인
							if (isRange(nextY, nextX) && map[nextY][nextX] == 1) {
								map[nextY][nextX] = islandNum;
								islandQue.add(new int[] { nextY, nextX });
							}
						}
					}
					
					islandNum++;
				}
			}
		}

		int minLength = Integer.MAX_VALUE;
		for (int y = 0; y < mapSize; y++) {
			for (int x = 0; x < mapSize; x++) {
				
				// map에서 1보다 크다면 육지인 것 Queue에 넣기
				// 이후에는 0인 바다 부분만 Queue에 넣으면서 다리 만들기
				// 현재 다리가 얼만큼 지어졌는지도 Queue에 같이 담기
				// 현재 섬번호를 담고 이 번호와 다른 섬번호를 만나면 다리 완성
				if (map[y][x] > 1) {
					int curIslandNum = map[y][x];
					
					// 새로운 지점에서 시작 할 때마다 방문 초기화
					visited = new boolean[mapSize][mapSize];

					Queue<int[]> bridgeQue = new LinkedList<>();
					bridgeQue.add(new int[] { y, x, 0 });
					while (!bridgeQue.isEmpty()) {
						int[] curBridge = bridgeQue.remove();

						int curY = curBridge[0];
						int curX = curBridge[1];
						
						// 현재 다리 길이
						int curLength = curBridge[2];

						// 현재 다리 길이가 최소보다 길다면 확인할 필요X
						if (curLength >= minLength) {
							break;
						}

						for (int dir = 0; dir < 4; dir++) {
							int nextY = curY + dy[dir];
							int nextX = curX + dx[dir];
							// 바다 만나서 다리 추가
							if (isRange(nextY, nextX) && map[nextY][nextX] == 0 && !visited[nextY][nextX]) {
								visited[nextY][nextX] = true;
								bridgeQue.add(new int[] { nextY, nextX, curLength + 1 });
							} 
							// 다른 섬 번호를 만나서 다리 완성
							// 위에서 현재 길이가 최소보다 짧을 때만 확인했기에 바로 대입
							else if (isRange(nextY, nextX) && map[nextY][nextX] != 0
									&& map[nextY][nextX] != curIslandNum) {
								minLength = curLength;
								break;
							}
						}
					}
				}
			}
		}

		System.out.println(minLength);
	}

}
