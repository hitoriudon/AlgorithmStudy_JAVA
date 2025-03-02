import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2638 {

	public static void main(String[] args) throws Exception {

		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");

		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int cheeseAmount = 0;
		int[][] oldMap = new int[y][x];
		for (int yCount = 0; yCount < y; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int xCount = 0; xCount < x; xCount++) {
				oldMap[yCount][xCount] = Integer.parseInt(st.nextToken());
				if (oldMap[yCount][xCount] == 1) {
					cheeseAmount++;
				}
			}
		}

		int curDay = 0;
		// 바로 아래 치즈 갯수가 0이 될때까지 돌기
		while (true) {
			if (cheeseAmount == 0) {
				break;
			}

			curDay++;

			// 바깥공기 구분해서 bfs로 내부 공기랑 다른 번호로 입력
			// 치즈가 녹아 내부공기가 외부공기가 되었을때를 위해 항상 다시 실행
			Queue<int[]> outQue = new LinkedList<>();
			// 바깥쪽은 치즈가 없으므로 {0, 0}은 무조건 공기
			oldMap[0][0] = 2;
			outQue.add(new int[] { 0, 0 });
			while (!outQue.isEmpty()) {
				int[] air = outQue.remove();
				int curY = air[0];
				int curX = air[1];
				for (int dir = 0; dir < 4; dir++) {
					int nextY = curY + dy[dir];
					int nextX = curX + dx[dir];
					
					// bfs에 걸리고 0이라면 바깥공기로
					if (nextY >= 0 && nextY < y && nextX >= 0 && nextX < x && oldMap[nextY][nextX] == 0) {
						oldMap[nextY][nextX] = 2;
						outQue.add(new int[] { nextY, nextX });
					}
				}
			}

			// 치즈 지우기
			int[][] newMap = new int[y][x];
			for (int yCount = 0; yCount < y; yCount++) {
				for (int xCount = 0; xCount < x; xCount++) {
					// 치즈라면 외부 공기 확인
					if (oldMap[yCount][xCount] == 1) {
						int airCount = 0;
						for (int dir = 0; dir < 4; dir++) {
							int nextY = yCount + dy[dir];
							int nextX = xCount + dx[dir];

							if (oldMap[nextY][nextX] == 2) {
								airCount++;
							}
						}

						// 외부공기 2개 이상이면 치즈 녹이기
						if (airCount >= 2) {
							newMap[yCount][xCount] = 0;
							cheeseAmount--;
						}
						// 외부공기 2개 미만이면 치즈 그대로
						else {
							newMap[yCount][xCount] = 1;
						}
					}
				}
			}
			oldMap = newMap;
		}

		System.out.println(curDay);
	}

}
