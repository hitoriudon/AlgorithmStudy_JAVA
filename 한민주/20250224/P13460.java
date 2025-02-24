import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P13460 {

	static int n, m, rx, ry, bx, by, answer = Integer.MAX_VALUE;
	static int[][] map;
	static int dx[] = { 0, 0, -1, 1 }; // 서동북남
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String inputArr[] = br.readLine().split(" ");
		n = Integer.parseInt(inputArr[0]);
		m = Integer.parseInt(inputArr[1]);

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				switch (input.charAt(j)) {
				case '#':
					map[i][j] = 0;
					break;
				case '.':
					map[i][j] = 1;
					break;
				case 'R':
					rx = i;
					ry = j;
					map[i][j] = 2;
					break;
				case 'B':
					bx = i;
					by = j;
					map[i][j] = 2;
					break;

				case 'O':
					map[i][j] = 3;
				}
				
				
			}
		}

		int originalRx = rx;
		int originalRy = ry;
		int originalBx = bx;
		int originalBy = by;

		for (int dir = 0; dir < 4; dir++) {
			dfs(dir, 1);
			map[rx][ry] = 1;
			map[bx][by] = 1;
			rx = originalRx;
			ry = originalRy;
			bx = originalBx;
			by = originalBy;
			map[rx][ry] = 2;
			map[bx][by] = 2;
		}
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);

	}

	static void dfs(int dir, int cnt) {
		if (cnt == 11) {
			return;
		}

		// 먼저 움직일 공 정하기
		boolean isRedFirst = false;

		if (dir == 0) { // 서
			if (ry < by) {
				isRedFirst = true;
			}
		} else if (dir == 1) { // 동
			if (ry > by) {
				isRedFirst = true;
			}
		} else if (dir == 2) { // 북
			if (rx < bx) {
				isRedFirst = true;
			}
		} else { // 남
			if (rx > bx) {
				isRedFirst = true;
			}
		}

		boolean redGoal = false;
		boolean blueGoal = false;
		// 옮기기
		if (isRedFirst) {
			redGoal = moveRed(dir);
			blueGoal = moveBlue(dir);
		} else {
			blueGoal = moveBlue(dir);
			redGoal = moveRed(dir);
		}
		if (redGoal && !blueGoal) {
			answer = Math.min(answer, cnt);
			return;
		} else if (blueGoal) {
			return;
		}

		int originalRx = rx;
		int originalRy = ry;
		int originalBx = bx;
		int originalBy = by;

		for (int i = 0; i < 4; i++) {
			dfs(i, cnt + 1);
			map[rx][ry] = 1;
			map[bx][by] = 1;
			rx = originalRx;
			ry = originalRy;
			bx = originalBx;
			by = originalBy;
			map[rx][ry] = 2;
			map[bx][by] = 2;
		}
	}

	static boolean moveRed(int dir) {
		int newRx = rx + dx[dir];
		int newRy = ry + dy[dir];

		while (map[newRx][newRy] == 1) {
			newRx += dx[dir];
			newRy += dy[dir];
		}

		if (map[newRx][newRy] == 3) // 만약 구멍이면
		{
			map[rx][ry] = 1;
			return true;
		}

		newRx -= dx[dir];
		newRy -= dy[dir];

		map[rx][ry] = 1;
		rx = newRx;
		ry = newRy;
		map[rx][ry] = 2;

		return false;
	}

	static boolean moveBlue(int dir) {
		int newBx = bx + dx[dir];
		int newBy = by + dy[dir];

		while (map[newBx][newBy] == 1) {
			newBx += dx[dir];
			newBy += dy[dir];
		}

		if (map[newBx][newBy] == 3) // 만약 구멍이면
		{
			return true;
		}

		newBx -= dx[dir];
		newBy -= dy[dir];

		map[bx][by] = 1;
		bx = newBx;
		by = newBy;
		map[bx][by] = 2;

		return false;
	}

}
