package algorithm;
// 메모리: 127520 KB , 시간: 664ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int sum[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				if (i != 0) {
					sum[i][j] += sum[i - 1][j];
				}
				if (j != 0) {
					sum[i][j] += sum[i][j - 1];
				}
				if (i != 0 && j != 0) {
					sum[i][j] -= sum[i - 1][j - 1];
				}
				sum[i][j] += Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken())-1;
			int sy = Integer.parseInt(st.nextToken())-1;
			int ex = Integer.parseInt(st.nextToken())-1;
			int ey = Integer.parseInt(st.nextToken())-1;

			int answer = sum[ex][ey];

			if (sx != 0) {
				answer -= sum[sx - 1][ey];
			}
			if (sy != 0) {
				answer -= sum[ex][sy - 1];
			}
			if (sx != 0 && sy != 0) {
				answer += sum[sx - 1][sy - 1];
			}
			sb.append(answer);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
