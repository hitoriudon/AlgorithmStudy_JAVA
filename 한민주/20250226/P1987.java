package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1987 {
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static boolean visited[] = new boolean[26];
	static int answer = 0, r, c;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[r][c];

		for (int i = 0; i < r; i++) {
			String input = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = input.charAt(j);
			}
		}

		visited[arr[0][0] - 'A'] = true;
		check(0, 0, 1);

		System.out.println(answer);
	}

	static void check(int x, int y, int cnt) {
		boolean canGo = false;
		for (int dir = 0; dir < 4; dir++) {
			int newX = x + dx[dir];
			int newY = y + dy[dir];
			
			if(inRange(newX, newY) && !visited[arr[newX][newY]-'A']) {
				visited[arr[newX][newY]-'A'] = true;
				canGo = true;
				check(newX, newY, cnt+1);
				visited[arr[newX][newY]-'A'] = false;
			}
		}
		
		if(!canGo) {
			answer = Math.max(answer, cnt);
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < r && y < c;
	}
}
