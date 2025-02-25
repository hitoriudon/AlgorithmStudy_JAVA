package study.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 구슬 탈출 2: 최소 시도 횟수->BFS with count -> BFS로 O에 이르는 최단 경로 찾은 후 blue 움직임 추적
public class P13460 {
	static int N, M;
	static char[][] map;
	static boolean[][][][] isVisited;  // 석철님 아이디어: {redX, redY, blueX, blueY}
	// -> 메모리는 증가하나 방향을 기준으로 삼아 blue만 움직일 수 있을 때 visited를 초기화해야 하는 기존 코드에 비해 직관적으로 변하며 오류 발생 X
	
	static Deque<int[]> redQueue = new ArrayDeque<>();  // 붉은 공 움직임
	static Deque<int[]> blueQueue = new ArrayDeque<>();  // 파란 공 움직임(붉은 공 움직임에 종속)
	static int[] dxs = {0, 0, -1, 1};  // 좌우상하
	static int[] dys = {-1, 1, 0, 0};
	static boolean redOut;
	static boolean blueOut;
	static int minTime;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String line;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// map 생성
		map = new char[N][M];
		isVisited = new boolean[N][M][N][M];  // 실수 4: 재방문 불가로 인해 경로 차단 발생 -> 석철님 아이디어 사용!
		redQueue.clear();
		blueQueue.clear();
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'R') {
					map[i][j] = '.';  // 빈 칸
					redQueue.offer(new int[] {i, j, 0});  // x, y, 동작 횟수
				}
				else if (line.charAt(j) == 'B') {
					map[i][j] = '.';
					blueQueue.offer(new int[] {i, j});
				}
				else {
					map[i][j] = line.charAt(j);
				}
			}
		}
		
		// bfs
		redOut = false;
		blueOut = false;
		minTime = -1;
		isVisited[redQueue.peek()[0]][redQueue.peek()[1]][blueQueue.peek()[0]][blueQueue.peek()[1]] = true;
		bfs();
		System.out.println(minTime);
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		while(!redQueue.isEmpty()) {
			int[] pos = redQueue.poll();
			int[] bluePos = blueQueue.poll();
			// 실수 9: 현재는 자식을 추가할 차례이므로, 10 초과로 설정하면 11 가능
			if (pos[2] >= 10) {  // 10번 이하로 안 되면 -1
				return;
			}

			for (int i = 0; i < 4; i++) {
				redOut = false;  // 실수 5: blueOut && redOut 시 초기화 필요...
				blueOut = false;
				int[] posSet = findChildren(pos[0], pos[1], bluePos[0], bluePos[1], i);
				// 변화 발생 시에만 추가: 실수 1: x축 변화로만 조건 설정=>수평 변화 인식 X, 실수 5: lue가 들어가지 않도록 하는 과정에서, red 위치가 움직이지 않아도 해당 움직임이 필요한 경우 발생
//				int[] newBluePose = findChildren(bluePos[0], bluePos[1], bluePos[2], i, false);
				
				// 실수 2: 파란 공과 붉은 공이 서로 경로를 방해하는 경우 고려 X -> 붉은 공과 파란 공의 위치가 겹치는 경우, 처리 필요
				// 실수 8: 방문 처리를 위해, 먼저 좌표를 수정 후 방문 확인 필요 -> peek() 비교에서 값 비교로 변경
				if (posSet[0] == posSet[2] && posSet[1] == posSet[3]) {  // 동일 좌표
					if (pos[0] == bluePos[0]) {  // x축 동일
						if (pos[1] > bluePos[1]) {  // 붉은 공이 오른쪽에 위치
							if (dys[i] > 0) {  // 오른쪽 기울이기
								posSet[3] -= 1;
							}
							else if (dys[i] < 0) {
								posSet[1] += 1;
							}
						}
						else if (pos[1] < bluePos[1]) {
							if (dys[i] > 0) {  // 오른쪽 기울이기
								posSet[1] -= 1;
							}
							else if (dys[i] < 0) {
								posSet[3] += 1;
							}
						}
					}
					if (pos[1] == bluePos[1]) {  // y축 동일
						if (pos[0] > bluePos[0]) {  // 붉은 공이 오른쪽에 위치
							if (dxs[i] > 0) {  // 오른쪽 기울이기
								posSet[2] -= 1;
							}
							else if (dxs[i] < 0) {
								posSet[0] += 1;
							}
						}
						else if (pos[0] < bluePos[0]) {
							if (dxs[i] > 0) {  // 오른쪽 기울이기
								posSet[0] -= 1;
							}
							else if (dxs[i] < 0) {
								posSet[2] += 1;
							}
						}
					}
				}
				
				if (!blueOut && !isVisited[posSet[0]][posSet[1]][posSet[2]][posSet[3]]) {
					redQueue.offer(new int[] {posSet[0], posSet[1], pos[2] + 1});
					blueQueue.offer(new int[] {posSet[2], posSet[3]});
					isVisited[posSet[0]][posSet[1]][posSet[2]][posSet[3]] = true;  // visited 정의가 바뀌어 모든 경로에 visit 처리하는 대신, 마지막 좌표만 추가
				}
				// 실수 6: red 위치가 동일하고 blue 위치가 바뀐 경우, visited 초기화 필요 -> 석철님 방식 사용 시 필요 X
//				if ((newPos[0] == pos[0] && newPos[1] == pos[1]) && (newBluePose[0] != bluePos[0] || newBluePose[1] != bluePos[1])) {
//					for (int dir = 0; dir < 4; dir++) {
//						isVisited[pos[0]][pos[1]][dir] = false;
//					}
//				}

				// 파란 공 out -> 실수 3: return하는 대신, 해당 경우의 자식만 제거해야 한다 -> offer 시 체크 필요 & 초기화
				if (redOut && !blueOut) {  // 붉은 공만 out
					minTime = pos[2] + 1;
					return;
				}
			}
		}
	}
	

//	private static int[] findChildren(int x, int y, int dir, boolean isRed) {
//		// TODO Auto-generated method stub
//		int dx = dxs[dir];
//		int dy = dys[dir];
//		
//		if (isRed) isVisited[x][y][dir] = true;
//		while (checked(x + dx, y + dy) && map[x + dx][y + dy] != '#') {  // 범위 밖으로 나가기 전까지 이동
//			x += dx;
//			y += dy;
//			if (isRed) isVisited[x][y][dir] = true;
//
//			if (map[x][y] == 'O') {
//				if (isRed) redOut = true;
//				else blueOut = true;
//				break;
//			}
//		}
//		return new int[] {x, y};
//	}
	
	private static int[] findChildren(int redx, int redy, int bluex, int bluey, int dir) {
		// TODO Auto-generated method stub
		int dx = dxs[dir];
		int dy = dys[dir];
		
		boolean canRed, canBlue;
		while ((canRed = checked(redx + dx, redy + dy) && map[redx + dx][redy + dy] != '#') || checked(bluex + dx, bluey + dy) && map[bluex + dx][bluey + dy] != '#') {  // 둘 중 하나라도 움직일 수 있을 때까지
			canBlue = checked(bluex + dx, bluey + dy) && map[bluex + dx][bluey + dy] != '#';
			if (canRed) {
				redx += dx;
				redy += dy;
			}
			if (canBlue) {
				bluex += dx;
				bluey += dy;
			}

			if (map[bluex][bluey] == 'O') {
				blueOut = true;
				break;  // blueOut 시 무조건 종료
			}
			if (map[redx][redy] == 'O') {
				redOut = true;
//				break;  // 실수 7: 붉은 공이 먼저 빠지고, 이후에 파란 공이 빠지는 경우도 존재
			}
		}
		return new int[] {redx, redy, bluex, bluey};
	}

	private static boolean checked(int x, int y) {  // 현재는 생략 가능
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < M;  // && map[x][y] != '#';  // 실수 5에 의거, blue 움직임 판단을 위해 # 여부 판단제거
	}
}
