import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [2579 계단 오르기]
// 1. 계단 1~2개씩 오를 수 있음
// 2. 연속된 세계의 계단을 밟을 수는 없음
// 3. 마지막 계단은 밟아야함
// 최대 점수 출력

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= n; i++) {
			arr[i] += Integer.parseInt(st.nextToken()) + arr[i-1];
		}
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			System.out.println(-arr[Integer.parseInt(st.nextToken())- 1] + arr[Integer.parseInt(st.nextToken())]);
		}
	}
}