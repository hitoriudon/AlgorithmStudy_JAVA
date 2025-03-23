import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1987 {
	static int[][] field;
	static int visited;
	static int R,C,answer;
	static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		field = new int[R][C];
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				field[i][j] = row.charAt(j) - 'A';
			}
		}
		backtracking(0,0,0);

	}

	static void backtracking(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);


	}
}
