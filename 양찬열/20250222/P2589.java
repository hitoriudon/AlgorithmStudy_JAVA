package study.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 보불섬: 가장 먼 최단 거리->BFS
// 각 좌표에 대해 BFS를 적용하는 완전 탐색으로 풀었으나, GPT에 따르면 해당 문제는 무향 그래프의 지름을 구하는 문제
// 임의의 정점에서 가장 먼 X를 찾고, X에서 가장 먼 정점까지의 거리를 구하면 무향 그래프의 지름이 된다.
// 영역 별로 2번씩만 BFS를 진행하면 되므로, 상당히 효율적인 방법!!!
public class P2589 {
	static int R, C;
	static char[][] map;
	static boolean[][] isVisited;
	static Deque<int[]> queue = new ArrayDeque<>();
	static int maxTime;
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int row = 0; row < R; row++) {
			String line = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = line.charAt(col);
			}
		}
		
		maxTime = 0;
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col]  == 'L') {
					isVisited = new boolean[R][C];
					queue.offer(new int[] {row, col, 0});  // x, y, time
					isVisited[row][col] = true;  // 실수: 시작점 방문 처리 X
					bfs();
				}
			}
		}
		
		System.out.println(maxTime);
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		int time = 0;
		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			int x = position[0];
			int y = position[1];
			time = position[2];
			
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dxs[dir];
				int newY = y + dys[dir];
				if (checked(newX, newY) && !isVisited[newX][newY] && map[newX][newY] == 'L') {  // 일반
					isVisited[newX][newY] = true;
					queue.offer(new int[] {newX, newY, time + 1});
				}
			}
		}
		
		maxTime = Math.max(maxTime, time);
	}

	private static boolean checked(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
