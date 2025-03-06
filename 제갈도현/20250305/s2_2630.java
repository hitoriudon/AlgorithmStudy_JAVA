package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s2_2630 {
	static int N, blue, white;
	static int[][] paper;

	static void divide(int x, int y, int size) {
		// 탐색할 첫 칸의 색을 저장
		int firstColor = paper[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				// 만약 첫 칸과 다른 색이 등장한다면 분할
				if (paper[i][j] != firstColor) {
					int nextSize = size / 2;
					// 4분할
					divide(x, y, nextSize);
					divide(x + nextSize, y, nextSize);
					divide(x, y + nextSize, nextSize);
					divide(x + nextSize, y + nextSize, nextSize);
					return;
				}
			}
		}

		if (firstColor == 1)
			blue++;
		else
			white++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, 0, N);

		System.out.println(white);
		System.out.println(blue);
	}
}