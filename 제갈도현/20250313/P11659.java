import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 입력된 수의 합을 저장
		int[] d = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			d[i] = d[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int i, j;
		for (int a = 0; a < M; a++) {
			st = new StringTokenizer(br.readLine(), " ");
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			
			sb.append(d[j]-d[i-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
