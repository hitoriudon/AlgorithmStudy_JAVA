import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P25682 {
	static int N, M, K, res = (int) 1e9;
	static char[][] board;
	
	static void checker(char color) {
		// 색상을 변경해야 할 칸의 개수 표현
		int[][] d = new int[N+1][M+1];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 시작 칸과 같은 색상을 가져야하는 친구들
				if ((i + j) % 2 == 0) {
					// 만약 색상이 다르다면 체크
					if (board[i][j] != color)
						d[i+1][j+1] = 1;
				} else {
					if (board[i][j] == color)
						d[i+1][j+1] = 1;
				}
				
				// 누적합 바로 갱신
				d[i+1][j+1] += d[i][j+1] + d[i+1][j] - d[i][j];
			}
		}
		
		for (int i = 0; i <= N - K; i++) {
			for (int j = 0; j <= M - K; j++) {
				res = Math.min(res, d[i+K][j+K] - d[i+K][j] - d[i][j+K] + d[i][j]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[N][];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().trim().toCharArray();
		}

		// W로 시작하는 체스판의 경우 구하기
		checker('W');
		// B로 시작하는 체스판의 경우 구하기
		checker('B');		
		
		System.out.println(res);
	}
}
