package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
	static int N, minusOne, zero, one;
	static int[][] paper;
	
	static void divide(int x, int y, int size) {
		int currNum = paper[x][y];
		
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (paper[i][j] != currNum) {
					int nextSize = size / 3;
					
					divide(x, y, nextSize);
					divide(x, y + nextSize, nextSize);
					divide(x, y + nextSize * 2, nextSize);
					divide(x + nextSize, y, nextSize);
					divide(x + nextSize, y + nextSize, nextSize);
					divide(x + nextSize, y + nextSize * 2, nextSize);
					divide(x + nextSize * 2, y, nextSize);
					divide(x + nextSize * 2, y + nextSize, nextSize);
					divide(x + nextSize * 2, y + nextSize * 2, nextSize);
					
					return;
				}
			}
		}
		
		if (currNum == -1)
			minusOne++;
		else if (currNum == 0)
			zero++;
		else
			one++;
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
		
		System.out.println(minusOne);
		System.out.println(zero);
		System.out.println(one);
	}
}