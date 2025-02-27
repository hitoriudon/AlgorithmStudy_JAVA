import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2573 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };

		st = new StringTokenizer(br.readLine(), " ");
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		// 녹이기 전 맵
		int[][] oldMap = new int[y][x];
		
		for(int yCount = 0; yCount < y; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int xCount = 0; xCount < x; xCount++) {
				oldMap[yCount][xCount] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0;
		while(true) {
			
			// 빙산 갯수 확인
			boolean[][] visited = new boolean[y][x];
			int iceCount = 0;
			for(int curY = 0; curY < y; curY++) {
				for(int curX = 0; curX < x; curX++) {
					// 0보다 크면 빙산, 빙산 갯수 늘리고 Queue에 넣어서 BFS로 주변 탐색 
					if(oldMap[curY][curX] > 0 && !visited[curY][curX]) {
						iceCount++;
						Queue<int[]> iceQue = new LinkedList<>();
						iceQue.add(new int[] {curY, curX});
						visited[curY][curX] = true;
						while(!iceQue.isEmpty()) {
							int[] curIce = iceQue.remove();
							
							for(int dir = 0; dir < 4; dir++) {
								int nextY = curIce[0] + dy[dir];
								int nextX = curIce[1] + dx[dir];
								if(oldMap[nextY][nextX] > 0 && !visited[nextY][nextX]) {
									iceQue.add(new int[] {nextY, nextX});
									visited[nextY][nextX] = true;
								}
							}
						}
					}
				}
			}
			
			// 빙산이 2개 이상이라면 년도 출력
			if(iceCount > 1) {
				System.out.println(year);
				return;
			} 
			// 빙산이 없다면 0 출력
			else if (iceCount == 0) {
				System.out.println(0);
				return;
			}
			
			// 빙산 녹이기
			year++;
			// 녹이고 나서 업데이트된 맵
			int[][] newMap = new int[y][x];
			for(int curY = 0; curY < y; curY++) {
				for(int curX = 0; curX < x; curX++) {
					// 0보다 크면 빙산, 주변 바다 수 확인 후 녹여주기
					// 0보다 작으면 0으로 넣어주기
					// 끝부분은 전부 0이라 isRange 판단 필요 X
					if(oldMap[curY][curX] > 0) {
						int seaCount = 0;
						for(int dir = 0; dir < 4; dir++) {
							int nextY = curY + dy[dir];
							int nextX = curX + dx[dir];
							if(oldMap[nextY][nextX] == 0) {
								seaCount++;
							}
						}
						
						newMap[curY][curX] = Math.max(0, oldMap[curY][curX] - seaCount);
					}
				}
			}
			
			// 빙산 맵 업데이트
			oldMap = newMap;
		}

	}

}
