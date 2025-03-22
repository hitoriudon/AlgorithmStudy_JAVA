import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1463 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());
		int[] dp = new int[num + 1];
		for (int numCount = 2; numCount <= num; numCount++) {
			// 1을 뺀 방식으로 일단 추가
			dp[numCount] = dp[numCount - 1] + 1;
			// 2의 배수면 2를 나눠서 하는 방식이 빠른지 확인
			if(numCount % 2 == 0) {
				dp[numCount] = Math.min(dp[numCount], dp[numCount / 2] + 1);
			}
			// 3의 배수면 3를 나눠서 하는 방식이 빠른지 확인
			if(numCount % 3 == 0) {
				dp[numCount] = Math.min(dp[numCount], dp[numCount / 3] + 1);
			}
		}
		System.out.println(dp[num]);
	}
}
