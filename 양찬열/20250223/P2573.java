package study.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 빙산: 영역이 2개 이상으로 나뉘어지는 최소 시간
// bound가 전부 0이므로 bound 처리 불필요, 이중 for문으로 값을 줄여나가다 0이 되면 bfs 시도(영역 판단)
public class P2573 {
	static int N, M;
	static int minTime, currTime;
	static int[][] map;
	static boolean[][] isVisited;
	static boolean isZero, turnZero;  // 전부 0이 되었는가?
	static Deque<int[]> queue = new ArrayDeque<>();
	static int[] dxs = {0, 0, -1, 1};
	static int[] dys = {-1, 1, 0, 0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int value = Integer.parseInt(st.nextToken());
				map[row][col] = value;
				if (value == 0) {
					isVisited[row][col] = true;
				}
			}
		}
		
		isZero = false;
		turnZero = false;
		minTime = 0;
		currTime = 0;
		while(!isZero) {
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (isVisited[row][col]) continue;
					// 실수 2: 같은 시점에 0이 된 주변 값도 영향을 준다.
					// -> isVisited 추가 시점을 pop 시기로 바꾸어 이를 이용해 해결하려 했으나, 이 경우 이미 음수로 변한 지역을 방문하는 문제 발생
					// -> 해당 분제를 해결하기 위해 음수를 건너뛰는 동작을 추가하니, 이번에는 음수로 변한 부분을 방문 처리 하지 못하는 문제 발생
					for (int dir = 0; dir < 4; dir++) {
						if (map[row + dxs[dir]][col + dys[dir]] <= 0 && isVisited[row + dxs[dir]][col + dys[dir]]) {  // 주변이 바다
							map[row][col] -= 1;  // isVisited로 0 여부를 관리하므로, 0 이하가 나와도 코드 상 문제 X
						}
					}
					if (map[row][col] <= 0) {
						turnZero = true;
//						isVisited[row][col] = true;
					}
					else if (queue.isEmpty()) {
//						System.out.println(row + " " + col);
						queue.offer(new int[] {row, col});  // 0이 아닌 지점 하나 저장
					}
				}
			}

//			System.out.println(Arrays.deepToString(map));
			currTime++;
			if (!isZero && turnZero) {
//				System.out.println(currTime);
				if (queue.isEmpty()) {  // 실수: 전부 0이 되어 queue가 빈 상태 -> 0 반환
					break;
				}
//				int[] position = queue.peek();
//				isVisited[position[0]][position[1]] = true;
				bfs();
				if (minTime != 0) break;
				resetVisited();  // visited 초기화
				turnZero = false;
			}
			queue.clear();  // 실수 3: queue에 추가하고 다음 턴에 0으로 바뀌는 경우, 그대로 원소가 잔류해 문제 발생 -> 명시적 초기화
		}
		
		System.out.println(minTime);
	}

	private static void bfs() {
		// TODO Auto-generated method stub
//		System.out.println(Arrays.deepToString(isVisited));
		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			int x = position[0];
			int y = position[1];
			
			if (isVisited[x][y] || map[x][y] <= 0) continue;
			isVisited[x][y] = true;
			
			for (int dir = 0; dir < 4; dir++) {
				int newX = x + dxs[dir];
				int newY = y + dys[dir];
				if (!isVisited[newX][newY]) {  // 바다는 방문 처리
					queue.offer(new int[] {newX, newY});
//					isVisited[newX][newY] = true;
				}
			}
		}
		checkedVisted();
	}

	private static void checkedVisted() {
		// TODO Auto-generated method stub
//		System.out.println(Arrays.deepToString(isVisited));
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				// 실수 4: 음수를 미리 방문 처리 하지 않아 이후 방문 처리가 되지 않는 문제 발생 -> 방문 검사 시 map 확인 필요
				if (!isVisited[row][col] && map[row][col] > 0) {  // 방문 안 한 곳 존재
//					System.out.println(row + " " + col);
					minTime = currTime;
					return;
				}
			}
		}
	}

	private static void resetVisited() {
		// TODO Auto-generated method stub
		int count = 0;
		isVisited = new boolean[N][M];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] <= 0) {
					isVisited[row][col] = true;
					count++;
				}
			}
		}
		
		if (count == N * M) {  // isVisited가 모두 true
			isZero = true;
		}
	}
}
