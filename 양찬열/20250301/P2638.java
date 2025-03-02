package study.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 치즈: 안쪽 공간은 ok, 바깥쪽 공간과 2칸 이상 닿으면 녹음
// 바깥쪽 공간을 dfs 돌리며 탐색, 1과 만날 때마다 해당 좌표의 count를 올려서 2 이상이면 0으로 변경
// 현재 가능한 지점의 범위를 매번 설정하여 줄여나가며 DFS 범위 한정, 범위가 0이 되면 종료
public class P2638 {
	static int N, M;
	static int startX, startY, endX, endY;
	static int[][] map;
	static int[][] countMap;  // 닿는 외부 칸 count용
	static boolean[][] isVisited;  // bit masking
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};
	static int minTime;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// map 생성
		int value;
		startX = startY = Integer.MAX_VALUE;
		endX = endY = 0;
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				value = Integer.parseInt(st.nextToken());
				map[row][col] = value;
				
				if (value == 1) {
					startX = Math.min(startX, row);
					startY = Math.min(startY, col);
					endX = Math.max(endX, row);
					endY = Math.max(endY, col);
				}
			}
		}
		
		// map 탐색
		minTime = 0;
		while(startX != Integer.MAX_VALUE) {  // 1인 구역이 하나도 없으면 범위 업데이트 시 초기값이 그대로 적용
			// 외부를 돌며 닿는 곳 count
			isVisited = new boolean[N][M];
			countMap = new int[N][M];
			isVisited[startX - 1][startY - 1] = true;
			searchCheese(startX - 1, startY - 1);

			// countMap을 확인하여 녹이기 & 범위 update
			meltCheese();
			
			minTime++;
		}
		System.out.println(minTime);
	}

	private static void meltCheese() {
		// TODO Auto-generated method stub
		int newStartX, newStartY, newEndX, newEndY;
		newStartX = newStartY = Integer.MAX_VALUE;
		newEndX = newEndY = 0;
		for (int row = startX; row <= endX; row++) {
			for (int col = startY; col <= endY; col++) {
				if (countMap[row][col] >= 2) {
					map[row][col] = 0;
				}
				else if (map[row][col] == 1) {
					newStartX = Math.min(newStartX, row);
					newStartY = Math.min(newStartY, col);
					newEndX = Math.max(newEndX, row);
					newEndY = Math.max(newEndY, col);
				}
			}
		}
		startX = newStartX;
		startY = newStartY;
		endX = newEndX;
		endY = newEndY;
	}

	private static void searchCheese(int x, int y) {
		// TODO Auto-generated method stub
		int dx, dy;
		for (int dir = 0; dir < 4; dir++) {
			dx = x + dxs[dir];
			dy = y + dys[dir];
			
			if (checked(dx, dy) && !isVisited[dx][dy]) {
				if (map[dx][dy] == 1) {
					countMap[dx][dy] += 1;
				}
				else {
					isVisited[dx][dy] = true;
					searchCheese(dx, dy);
				}
			}
		}
	}

	private static boolean checked(int dx, int dy) {
		// TODO Auto-generated method stub
		return dx >= startX - 1 && dx <= endX + 1 && dy >= startY - 1 && dy <= endY + 1;
	}
}
