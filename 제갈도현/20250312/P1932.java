import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] tri = new int[n][n];
		
		// 삼각형 정보 입력
		int index = 1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < index; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
			index++;
		}
		
		// 최댓값 계산 시작
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				// 첫 요소
				if (j == 0)
					tri[i][j] += tri[i-1][j];
				// 마지막 요소
				else if (j == i)
					tri[i][j] += tri[i-1][j-1];
				// 가운데 요소
				else
					tri[i][j] += Math.max(tri[i-1][j-1], tri[i-1][j]);
			}
		}
		
		// 마지막줄에서 가장 큰 수 추출
		int res = 0;
		for (int i = 0; i < n; i++) {
			res = Math.max(res, tri[n-1][i]);
		}
		
		System.out.println(res);
	}
}
