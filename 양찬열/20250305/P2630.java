package study.DivdeNConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 색종이 만들기: 4분면으로 나누어 분할-정복
public class P2630 {
	static int[][] map;
	static int N;
	static int whiteCount, blueCount;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		whiteCount = 0;
		blueCount = 0;
		checkedPaper(0, N, 0, N);  // x, y 범위
		System.out.println(whiteCount);
		System.out.println(blueCount);
	}

	private static void checkedPaper(int startX, int endX, int startY, int endY) {
		// TODO Auto-generated method stub
		int flag = checkedSame(startX, endX, startY, endY);
		if (flag == 0) {
			whiteCount++;
			return;
		}
		else if (flag == 1) {
			blueCount++;
			return;
		}
		
		int[] dxs = {startX, (startX + endX)/2};
		int[] dys = {startY, (startY + endY)/2};
		int length = (endX - startX)/2;  // x, y 동일
		for (int x: dxs) {
			for (int y: dys) {
				checkedPaper(x, x + length, y, y + length);
			}
		}

	}

	// 생각해 보니 맵을 새로 만들 것 없이, 범위만 보내도 된다!
//	private static int[][] makeSubMap(int[][] subMap, int startX, int endX, int startY, int endY) {
//		// TODO Auto-generated method stub
//		int[][] newMap = new int[endX - startX][endY - startY];
//		for (int i = startX; i < endX; i++) {
//			newMap[i] = Arrays.copyOfRange(subMap[i], startY, endY);
//		}
//		return newMap;
//	}

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
