import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2167 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] sumRange = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tot = 0;
				if(i != 0 && j != 0) {
					tot -= sumRange[i-1][j-1];
					
				}
				if(i != 0) {
					tot += sumRange[i-1][j];					
				}
				if(j != 0) {
					tot += sumRange[i][j-1];					
				}
				
				sumRange[i][j] = tot + Integer.parseInt(st.nextToken());
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken())-1;
			int j = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int tot = 0;
			
			if(i-1 >= 0 && j-1 >= 0) {
				tot += sumRange[i-1][j-1];	
			}
			if(i-1 >= 0) {
				tot -= sumRange[i-1][y];								
			}
			if(j-1 >= 0) {
				tot -= sumRange[x][j-1];								
			}
			
			tot += sumRange[x][y];
			System.out.println(tot);
		}
	}
}
