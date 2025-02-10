import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P2504 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean isCorrect = true;
		char lastBracket;
		int lastValue, point, thisValue;
		
		// 괄호열 저장 스택
		ArrayDeque<Character> bracketStack = new ArrayDeque<>();
		// 깊이별 값을 계산하는 스택
		ArrayDeque<Integer> valueStack = new ArrayDeque<>();
		
		for (char c : br.readLine().toCharArray()) {
			if (c == '(' || c == '[') {
				// 여는 괄호일 경우
				bracketStack.addLast(c);
				// depth별 초기값 0 삽입
				valueStack.addLast(0);
			} else {
				// 닫는 괄호일 경우
				// bracketStack이 비어있을 경우 옳은 괄호열이 아님
				if (bracketStack.isEmpty()) {
					isCorrect = false;
					break;
				}
				
				// 괄호가 서로 일치하지 않는 경우 옳은 괄호열이 아님
				lastBracket = bracketStack.removeLast();
				if ((c == ')' && lastBracket != '(') || (c == ']' && lastBracket != '[')) {
                    isCorrect = false;
                    break;
                }
				
				// 괄호에 따른 숫자 변경
				point = (c == ')') ? 2 : 3;
				lastValue = valueStack.removeLast();
				// 만약 depth가 다르다면 point와 이전 수를 곱해주기
				thisValue = (lastValue == 0) ? point : point * lastValue;
				
				// valueStack에 depth가 같은 수가 있다면 더하기
				if(!valueStack.isEmpty()) {
					valueStack.addLast(valueStack.removeLast() + thisValue);
				} else {
					valueStack.addLast(thisValue);
				}
				
			}
		}
		
		// 스택에 값이 남아있다면 옳은 괄호열이 아님
		if (!bracketStack.isEmpty()) isCorrect = false;
		
		if (isCorrect) {
			System.out.println(valueStack.removeLast());
		} else {
			System.out.println("0");
		}
	}
}
