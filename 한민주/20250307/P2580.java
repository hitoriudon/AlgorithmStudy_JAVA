package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2580 {
	static int map[][] = new int[9][9];
	static ArrayList<int[]> binkan = new ArrayList<int[]>();
	static boolean kan9[][][] = new boolean[3][3][10];
	static boolean hor[][] = new boolean[9][10];
	static boolean ver[][] = new boolean[9][10];
	static boolean full = false;

	static void sdoku(int n) {
		if (n == binkan.size()) {
			full = true;
			return;
		}

		int x = binkan.get(n)[0];
		int y = binkan.get(n)[1];
		int kanx = x / 3;
		int kany = y / 3;

		for (int i = 1; i < 10; i++) {
			if (!kan9[kanx][kany][i] && !hor[x][i] && !ver[y][i]) {
				kan9[kanx][kany][i] = true;
				hor[x][i] = true;
				ver[y][i] = true;
				map[x][y] = i;
				sdoku(n + 1);
				if (full) {
					return;
				}
				kan9[kanx][kany][i] = false;
				hor[x][i] = false;
				ver[y][i] = false;
				map[x][y] = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					binkan.add(new int[] { i, j });
				} else {
					kan9[i / 3][j / 3][map[i][j]] = true;
					hor[i][map[i][j]] = true;
					ver[j][map[i][j]] = true;
				}
			}
		}

		sdoku(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
