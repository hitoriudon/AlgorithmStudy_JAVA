package PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2차원 배열의 합: 누적합 이용(sums(i, j) - sums(x, y))
public class P2167 {
	static int N, M, K;
	static int[][] sums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sums = new int[N + 1][M + 1];  // 테두리 포함 -> 1개일 때도 공통 처리
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				sums[r][c] = Integer.parseInt(st.nextToken()) + sums[r - 1][c] + sums[r][c - 1] - sums[r - 1][c - 1];  // 새로운 값(현재 좌표 값) + 직사각형 2개 - 중복 정사각형
			}
		}
		
		K = Integer.parseInt(br.readLine());
		int firstX, firstY, secondX, secondY;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			firstX = Integer.parseInt(st.nextToken()) - 1;
			firstY = Integer.parseInt(st.nextToken()) - 1;
			secondX = Integer.parseInt(st.nextToken());
			secondY = Integer.parseInt(st.nextToken());
			
			sb.append(sums[secondX][secondY] - sums[secondX][firstY] - sums[firstX][secondY] + sums[firstX][firstY]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
