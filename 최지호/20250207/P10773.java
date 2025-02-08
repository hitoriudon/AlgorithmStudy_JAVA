import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P10773 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		int sum = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		//stack 이용해 입력 받고, 0 이라면 pop하기
		for(int i = 0; i < k; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				stack.pop();
			}
			else {
				stack.add(n);
			}
		}
		
		//for문 돌며 stack의 값을 더하기
		for(int s : stack) {
			sum += s;
		}
		System.out.println(sum);
	}
}