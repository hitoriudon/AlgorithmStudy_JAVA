package study.DivdeNConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 종이의 개수: 색종이와 동일(1/4을 1/9로만 변경)
public class P1780 {
	static int[][] map;
	static int N;
	static int[] counts;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		counts = new int[3];  // -1, 0, 1
		divideArea(0, N, 0, N);  // x, y 범위
		
		for (int idx = 0; idx < 3; idx++) {
			System.out.println(counts[idx]);
		}
	}

	private static void divideArea(int startX, int endX, int startY, int endY) {
		// TODO Auto-generated method stub
		int flag = checkedSame(startX, endX, startY, endY);
		if (flag != -1) {
			counts[flag] += 1;
			return;
		}
		
		int length = (endX - startX)/3;  // x, y 동일
		int[] dxs = {startX, startX + length, startX + 2*length};
		int[] dys = {startY, startY + length, startY + 2*length};
		for (int x: dxs) {
			for (int y: dys) {
				divideArea(x, x + length, y, y + length);
			}
		}
	}
	
	private static int checkedSame(int startX, int endX, int startY, int endY) {
		// TODO Auto-generated method stub
		int flag = map[startX][startY];
		for (int r = startX; r < endX; r++) {
			for (int c = startY; c < endY; c++) {
				if (map[r][c] != flag) {
					return -1;
				}
			}
		}
		return flag + 1;  // -1, 0, 1 -> 0, 1, 2
	}
}
