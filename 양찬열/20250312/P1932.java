package PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정수 삼각형: n*n+2(양쪽 패딩) 배열을 만들어 누적합 계산
public class P1932 {
	static int N;
	static int[][] sums;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sums = new int[N][N + 2];
		
		sums[0][1] = Integer.parseInt(br.readLine());
		for (int depth = 1; depth < N; depth++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= depth + 1; c++) {
				sums[depth][c] = Math.max(sums[depth - 1][c - 1], sums[depth - 1][c]) + Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, sums[N - 1][i]);
		}
		System.out.println(max);
	}
}
