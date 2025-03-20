import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[N+1][2];
		int[][] d = new int[N+1][K+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				int W = items[i][0];
				int V = items[i][1];
				
				if (j < W) {
					d[i][j] = d[i-1][j];
				} else {
					d[i][j] = Math.max(d[i-1][j], d[i-1][j-W]+V);
				}
			}
		}
		
		System.out.println(d[N][K]);
	}
}