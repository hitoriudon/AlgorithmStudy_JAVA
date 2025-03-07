import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P1992 {
	static int N;
	static int[][] movie;
	static ArrayList<Character> q = new ArrayList<>();

	static void divide(int x, int y, int size) {
		// 탐색용 첫 색상 기록
		int firstColor = movie[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				// 첫 색상과 다른 경우
				if (movie[i][j] != firstColor) {
					int nextSize = size / 2;

					// 괄호 열기
					q.add('(');
					// 영상 분할
					divide(x, y, nextSize);
					divide(x, y + nextSize, nextSize);
					divide(x + nextSize, y, nextSize);
					divide(x + nextSize, y + nextSize, nextSize);
					// 괄호 닫기
					q.add(')');

					return;
				}
			}
		}

		// 1이라면 큐에 1, 0이라면 큐에 0 저장
		if (firstColor == 1)
			q.add('1');
		else
			q.add('0');		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		movie = new int[N][N];

		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().trim();
			for (int j = 0; j < N; j++) {
				movie[i][j] = input.charAt(j) - '0';
			}
		}

		divide(0, 0, N);
		
		for (char c : q) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}