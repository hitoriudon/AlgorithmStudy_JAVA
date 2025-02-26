import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1987 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int y;
	static int x;

	static int[][] map;
	static boolean[] visited;

	// 처음은 무조건 밟을 수 있으므로 1로 미리 세팅
	// dfs 탐색 못할 때를 이용
	static int maxMove = 1;

	static boolean isRange(int curY, int curX) {
		return (curY >= 0 && curY < y && curX >= 0 && curX < x);
	}

	static void dfs(int curY, int curX, int curMove) {
		// 네 방향 탐색
		for(int dir = 0; dir < 4; dir++) {
			int nextY = curY + dy[dir];
			int nextX = curX + dx[dir];
			
			// 범위 안에 있다
			if(isRange(nextY, nextX)) {
				int curAlpha = map[nextY][nextX];
				// 밟은 적 있는 알파벳이면 전진 불가능 최댓값 비교 후 갱신
				if(visited[curAlpha]) {
					maxMove = Math.max(maxMove, curMove);
				} 
				// 밟지 않았다면 밟고 전진 후 dfs
				// dfs 끝나면 밟은거 다시 되돌리기
				else {
					visited[map[nextY][nextX]] = true;
					dfs(nextY, nextX, curMove + 1);
					visited[map[nextY][nextX]] = false;
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
		// 알파벳을 몇번째 알파벳인지로 숫자로 변환하여 저장
		map = new int[y][x];
		// 방문도 숫자로 변환 후 저장
		visited = new boolean[26];
		for (int yCount = 0; yCount < y; yCount++) {
			String line = br.readLine();
			for (int xCount = 0; xCount < x; xCount++) {
				// map을 만들 때 'A'를 빼서 미리 숫자로 치환
				map[yCount][xCount] = line.charAt(xCount) - 'A';
			}
		}
		
		// 0, 0 에서 시작
		visited[map[0][0]] = true;
		dfs(0, 0, 1);
		
		System.out.println(maxMove);
	}

}
