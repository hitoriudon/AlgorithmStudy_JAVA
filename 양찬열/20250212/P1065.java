import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if (N < 99) {
			System.out.println(N);  // 두 자릿수는 전부 만족
			return;
		}
		
		int count = 99;
		for (int i = 99; i <= N; i++) {
			String number = Integer.toString(i);  // 자릿수 계산을 위해 string화
			int change = number.charAt(1) - number.charAt(0);  // 등차 계산
			for (int j = 2; j < number.length(); j++) {
				if (number.charAt(j) - number.charAt(j-1) != change) {
					break;
				}
				if (j == number.length() - 1) {  // 마지막까지 만족
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
