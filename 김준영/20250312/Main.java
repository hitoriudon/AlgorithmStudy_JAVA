import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [2167 배열의 합]

public class Main {
	static int n, m, k;
	static int i, j, x, y;
	static int[][] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 배열의 크기 N
		m = Integer.parseInt(st.nextToken()); // 배열의 크기 M
		num = new int[n][m];

		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine()); // M개의 정수
			for (int col = 0; col < m; col++) {
				num[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		k = Integer.parseInt(br.readLine()); // 합을 구할 부분의 개수
		for (int ii = 0; ii < k; ii++) {
			st = new StringTokenizer(br.readLine());

			int res = 0;

			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			for (int row = i - 1; row <= x - 1; row++) {
				for (int col = j - 1; col <= y - 1; col++) {
					res += num[row][col];
				}
			}

			System.out.println(res);
		}
	}
}