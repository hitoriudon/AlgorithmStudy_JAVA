package test.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2615 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("Test5.txt"));
		//---------여기에 코드를 작성하세요.---------------//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Stack<int[]> points = new Stack();
		
		// map 생성
		int N = 19;
		int[][] map = new int[N][N];
		for (int row = 0; row < N; row++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(line.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(map));
		
		// map 탐색
		int[] dxs = {-1, 0, 1, 1};
		int[] dys = {1, 1, 1, 0};
				
		for (int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if (map[row][col] != 0) {
					for (int i = 0; i < 4; i++) {
						int dx = dxs[i];
						int dy = dys[i];
						int x = row + dx;
						int y = col + dy;
						int team = map[row][col];
						if (!(row - dx >= 0 && row - dx < N && col - dy >= 0 && col - dy < N) || map[row - dx][col - dy] != team) {
							int count = 1;
							while (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == team) {
								count++;
								x += dx;
								y += dy;
							}
							if (count == 5) {
								System.out.println(team);
								System.out.printf("%d %d\n", row + 1, col + 1);
								return;
							}
						}
					}
				}
			}
		}
		System.out.println(0);
	}
}