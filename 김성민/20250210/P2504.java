package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class P2504 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Deque<Character> stack = new LinkedList<>();

		int sum = 0;
		int tmp = 1;
		char preChar = ' ';

		String problem = br.readLine();
		int problemLength = problem.length();
		for (int i = 0; i < problemLength; i++) {
			char cur = problem.charAt(i);
			// ( or [ 를 만나면 분배의 법칙을 이용해 미리 곱해준다
			if (cur == '(') {
				stack.addLast(cur);
				tmp *= 2;
			} else if (cur == '[') {
				stack.addLast(cur);
				tmp *= 3;
			} else if (cur == ')') {
				// )인데 비어 있거나 ( 이아니면 문제 발생 종료
				if(stack.isEmpty() || stack.peekLast() != '(') {
					System.out.println(0);
					return;
				}
				// 바로 이전이 (라면 숫자로 인식 값을 더해준다
				if (preChar == '(') {
					sum += tmp;
				}
				stack.removeLast();
				// 닫혔으니 분배법칙 곱하기 제거
				tmp /= 2;
			} else if (cur == ']') {
				// ]인데 비어 있거나 [이 아니면 문제 발생 종료
				if(stack.isEmpty() || stack.peekLast() != '[') {
					System.out.println(0);
					return;
				}
				// 바로 이전이 [라면 숫자로 인식 값을 더해준다
				if (preChar == '[') {
					sum += tmp;
				}
				stack.removeLast();
				// 닫혔으니 분배법칙 곱하기 제거
				tmp /= 3;
			}
			preChar = cur;
		}
		
		// 스택이 모두 빠져 괄호를 전부 사용했다면 출력
		if(stack.isEmpty()) {
			System.out.println(sum);
		} else {
			System.out.println(0);
		}
	}

}
