package algorithm;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10830 {
	static int[][] matrix;
	static int n;

	static int[][] realMulti(int[][] m1, int[][] m2) {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					temp[i][j] += m1[i][k] * m2[k][j];
				}
				temp[i][j] %= 1000;
			}
		}
		return temp;
	}

	static int[][] multi(long b) {
		if (b == 1) {
			return matrix;
		}
		
		int[][] halfpow = multi(b / 2);

		// halfpow * halfpow
		int[][] onepow = realMulti(halfpow, halfpow);

		
		if(b % 2 == 1) {
			return realMulti(matrix, onepow);
		}
		
		return onepow;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		matrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}

		matrix = multi(b);

		

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(matrix[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}

