import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P10773 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int res = 0;
		
		int K = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int k = 0; k < K; k++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				// 만약 입력값이 0이면 스택에서 제거
				q.removeLast();
			} else {
				// 아니라면 스택에 저장
				q.addLast(input);
			}
		}
		
		// 스택의 수를 합하여 출력
		for (int i : q) {
			res += i;
		}
		System.out.println(res);
	}
}