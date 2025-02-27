package study.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 알파벳: 같은 알파벳은 2번 이상 이동 불가 -> DFS로 최대 거리 반환
public class P1987 {
	static int R, C;
	static int[][] map;  // bit masking을 위해 int로 저장
	static int isVisited;  // 알파벳 자체에 visited 처리, bit masking 사용
	static int maxDepth;
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String line;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		isVisited = 0;
		
		// map 생성
		for (int row = 0; row < R; row++) {
			line = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = line.charAt(col) - 'A';
			}
		}
		
		// DFS
		maxDepth = findMaxDepth(0, 0, 1);  // 실수: 시작 시 1
		System.out.println(maxDepth);
	}

	private static int findMaxDepth(int x, int y, int depth) {
		// TODO Auto-generated method stub
		int maxDepth = depth;
		isVisited = isVisited | 1<<map[x][y];  // 현재 노드 추가
		
		int dx, dy;
		for (int dir = 0; dir < 4; dir++) {
			dx = x + dxs[dir];
			dy = y + dys[dir];
			if (checked(dx, dy) && ((isVisited & 1<<map[dx][dy]) == 0)) {
				maxDepth = Math.max(maxDepth, findMaxDepth(dx, dy, depth + 1));
			}
		}
		isVisited = isVisited & ~(1<<map[x][y]);  // 실수: 방문 처리 초기화
		return maxDepth;
	}

	private static boolean checked(int dx, int dy) {
		// TODO Auto-generated method stub
		return dx >= 0 && dx < R && dy >= 0 && dy < C;
	}
}
