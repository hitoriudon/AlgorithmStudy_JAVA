package no;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2615 {
	static int arr[][] = new int[19][19];
	static int dx[] = { 0, 1, 1, -1 };
	static int dy[] = { 1, 0, 1, 1 }; // 가로 세로 위아대 아위대
	static boolean visited[][][] = new boolean[4][19][19];
	static int ansX, ansY, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (arr[i][j] != 0) {
					omok(i, j, arr[i][j]);
					if (answer != 0) {
						System.out.println(answer);
						System.out.println((ansX + 1) + " " + (ansY + 1));
						return;
					}
				}

			}
		}

		System.out.println(0);
	}

	static void omok(int x, int y, int color) {
		for (int dir = 0; dir < 4; dir++) {
			boolean isOmok = true;
			int newX = x;
			int newY = y;
			for (int i = 0; i < 4; i++) {
				newX += dx[dir];
				newY += dy[dir];

				if (!inRange(newX, newY) || arr[newX][newY] != color) {
					isOmok = false;
					break;
				}

			}
			if (isOmok) {
				newX = x - dx[dir];
				newY = y - dy[dir];
				if (inRange(newX, newY) && arr[newX][newY] == color) {
					isOmok = false;
				}
				newX = x + dx[dir] * 5;
				newY = y + dy[dir] * 5;
				if (inRange(newX, newY) && arr[newX][newY] == color) {
					isOmok = false;
				}
			}

			if (isOmok) {
				ansX = x;
				ansY = y;
				answer = color;
				return;
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < 19 && 0 <= y && y < 19;
	}
}
