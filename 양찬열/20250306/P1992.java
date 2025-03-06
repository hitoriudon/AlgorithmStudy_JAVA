package study.DivdeNConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 쿼드트리: 분할 정복을 사용하여 분할 후 대표값으로 압축, depth에 따라 ( 추가
public class P1992 {
	static int[][] map;
	static int N;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		String line;
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}
		
		divideArea(0, N, 0, N);  // x, y 범위
		System.out.println(sb.toString());
	}

	private static void divideArea(int startX, int endX, int startY, int endY) {
		// TODO Auto-generated method stub
		int flag = checkedSame(startX, endX, startY, endY);
		if (flag != -1) {
			sb.append(flag);
			return;
		}
		
		int[] dxs = {startX, (startX + endX)/2};
		int[] dys = {startY, (startY + endY)/2};
		int length = (endX - startX)/2;  // x, y 동일
		sb.append("(");
		for (int x: dxs) {
			for (int y: dys) {
				divideArea(x, x + length, y, y + length);
			}
		}
		sb.append(")");
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
		return flag;
	}
}
