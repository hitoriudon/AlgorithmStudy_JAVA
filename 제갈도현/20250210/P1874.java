import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P1874 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int input, newNum = 1;
		boolean isCorrect = true;
		
		int n = Integer.parseInt(br.readLine());
		// 숫자 저장용 스택
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		// 연산 저장용 스택
		ArrayDeque<Character> res = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			input = Integer.parseInt(br.readLine());
			
			// 해당 숫자가 나올 때 까지 push
			while (newNum <= input) {
				stack.addLast(newNum++);
				res.addLast('+');
			}
			
			if (stack.peekLast() == input) {
				// 만약 마지막 요소가 숫자와 같다면 pop
				stack.removeLast();
				res.addLast('-');
			} else {
				// 아니라면 NO 출력
				System.out.println("NO");
				isCorrect = false;
				break;
			}
		}
		
		// 정상적인 경우만 정답 출력
		if(isCorrect) {
			for (char c : res) {
				sb.append(c).append("\n");
			}
			System.out.println(sb);
		}
	}
}