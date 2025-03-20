import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2167 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] orig = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());			
			for (int j= 1; j <= M; j++) {
				orig[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] d = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {			
			for (int j= 1; j <= M; j++) {
				// 누적합 구하기
				d[i][j] = orig[i][j] + d[i-1][j] + d[i][j-1] - d[i-1][j-1];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 누적합 계산
			sb.append(d[x][y] - d[x][j-1] - d[i-1][y] + d[i-1][j-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}