package study.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 토마토
public class P7576 {
	static int M, N;
	static int[][] map;
	static boolean[][] isVisited;
	static int tomatoCount, totalTomatoes;
	static Deque<int[]> queue;
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};
	static int time;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int value = 0;
		map = new int[N][M];
		isVisited = new boolean[N][M];
		queue = new ArrayDeque<>();
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				value = Integer.parseInt(st.nextToken());
				map[row][col] = value;

				if (!(value == -1)) {
					totalTomatoes++;
				}
				if (value == 1) {
					queue.offer(new int[] {row, col, 0});
				}
			}
		}
		
		tomatoCount = 0;
		time = -1;
		bfs();
		if (tomatoCount != totalTomatoes) {
//			System.out.println(tomatoCount + " " + totalTomatoes);
			System.out.println(-1);
//			System.out.println(Arrays.deepToString(isVisited));
			return;
		}
//		System.out.println(Arrays.deepToString(isVisited));
		System.out.println(time);
	}

	// 실수: -1까지 처리했네...
	private static void bfs() {
		// TODO Auto-generated method stub
		while (!queue.isEmpty()) {
//			System.out.println("size: " + queue.size());
			int[] pos = queue.poll();
			int x = pos[0];
			int y = pos[1];
			if (isVisited[x][y] || map[x][y] == -1) {
				continue;
			}
			isVisited[x][y] = true;
			tomatoCount++;
//			System.out.println(tomatoCount);
			
			for (int dir = 0; dir < 4; dir++) {
				if(checked(x + dxs[dir], y + dys[dir])) {
					queue.offer(new int[] {x + dxs[dir], y + dys[dir], pos[2] + 1});
				}
			}
			time = pos[2];
		}
	}

	private static boolean checked(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
