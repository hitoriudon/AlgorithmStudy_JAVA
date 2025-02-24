package study.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 적록색약: BFS 2회 반복(map으로 char -> int, 절댓값을 이용해 if 대체)
public class P10026 {
	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	static Deque<int[]> queue;
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};
	static int RGBArea;
	static int GBArea;
	static Map<Character, Integer> charMap = new HashMap<>();	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		charMap.put('R', -1);
		charMap.put('G', 1);
		charMap.put('B', 0);
		
		// map 생성
		map = new int[N][N];
		queue = new ArrayDeque<>();
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int col = 0; col < N; col++) {
				map[row][col] = charMap.get(line.charAt(col));
			}
		}
		
		// R/G/B 구분
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isVisited[i][j]) {
					continue;
				}
				
				RGBArea++;
				queue.add(new int[] {i, j});
				bfs(map[i][j], true);
			}
		}
		
		// RG/B 구분
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isVisited[i][j]) {
					continue;
				}

				GBArea++;
				queue.add(new int[] {i, j});
				bfs(Math.abs(map[i][j]), false);
			}
		}
		
		System.out.println(RGBArea + " " + GBArea);
	}

	private static void bfs(int area, boolean isRGB) {
		// TODO Auto-generated method stub
		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			int x = position[0];
			int y = position[1];
			
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dxs[dir];
				int newY = y + dys[dir];
				if (isRGB && checked(newX, newY) && !isVisited[newX][newY] && map[newX][newY] == area) {  // 일반
					isVisited[x + dxs[dir]][y + dys[dir]] = true;
					queue.offer(new int[] {x + dxs[dir], y + dys[dir]});
				}
				else if (!isRGB && checked(newX, newY) && !isVisited[newX][newY] && Math.abs(map[newX][newY]) == area) {  // 적록색약
					isVisited[x + dxs[dir]][y + dys[dir]] = true;
					queue.offer(new int[] {x + dxs[dir], y + dys[dir]});
				}
			}
		}
	}

	private static boolean checked(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
