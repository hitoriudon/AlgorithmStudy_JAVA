import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2615 {
	static int[][] badukpan = new int[19][19];
	static boolean black = false, white = false;
	static int resX = 0, resY = 0;
	// 배열 탐색은 위에서 아래로, 좌에서 우로
	// 그렇기에 좌->우, 상->하, 좌상->우하, 좌하->우상 4방향만 검색
	static int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { -1, 1 } };

	static void omokChecker(int x, int y, int color) {
		int px, py, nx, ny, cnt;

		for (int d = 0; d < 4; d++) {
			// 육목 방지로 이전 칸 체크
			px = x - dir[d][0];
			py = y - dir[d][1];
			if (px >= 0 && px < 19 && py >= 0 && py < 19 && badukpan[px][py] == color)
				continue;

			// 같은 색 바둑알 숫자 체크
			nx = x + dir[d][0];
			ny = y + dir[d][1];
			cnt = 1;
			while (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && badukpan[nx][ny] == color) {
				cnt++;
				nx += dir[d][0];
				ny += dir[d][1];
			}

			// 오목인 경우
			if (cnt == 5) {
				resX = x + 1;
				resY = y + 1;

				if (color == 1)
					black = true;
				else
					white = true;
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 19; j++) {
				badukpan[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		out: for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				// 오목 체크
				if (badukpan[i][j] != 0)
					omokChecker(i, j, badukpan[i][j]);
				// 만약 승패가 결정난 경우 for문 탈출
				if (black || white)
					break out;
			}
		}

		if (black)
			System.out.printf("1\n%d %d", resX, resY);
		else if (white)
			System.out.printf("2\n%d %d", resX, resY);
		else
			System.out.printf("0");
	}
}
