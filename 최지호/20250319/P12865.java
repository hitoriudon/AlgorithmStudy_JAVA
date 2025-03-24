import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 12865 평범한 배낭
// 메모리 : 13388kb
// 시간 : 120ms

public class P12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int maxK = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[maxK + 1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			for(int j = maxK; j >= w; j--) {
				dp[j] = Math.max(dp[j], dp[j-w]+v);
			}
//			System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[maxK]);
	}
}