import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
[2594 괄호의 값]

 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		ArrayDeque<Character> stk2 = new ArrayDeque<>();
		ArrayDeque<Character> stk3 = new ArrayDeque<>();
		int weight = 1;
		int res = 0;
		char bef= 'a';
		boolean possible = true;
		
		for (int i = 0; i < input.length(); i++) { // 문자 순화
			char curInput = input.charAt(i);
//			System.out.println("cur Input : " + curInput);

			switch (curInput) {
			case '(': {
				weight *= 2;
				stk2.push(curInput);
				bef = '(';
				break;
			}
			case ')': {
				weight /= 2;
				if (bef == '(')
					res += weight * 2;
				if(!stk2.isEmpty())
					stk2.pop();
				else
					possible = false;
				bef = ')';
				break;
			}
			case '[': {
				weight *= 3;
				stk3.push(curInput);
				bef = '[';
				break;
			}
			case ']': {
				weight /= 3;
				if (bef == '[')
					res += weight * 3;
				if(!stk3.isEmpty())
					stk3.pop();
				else
					possible = false;				
				bef = ']';
				break;
			}

			}
		}
		if(stk2.isEmpty() && stk3.isEmpty() && possible) {
			System.out.println(res);

		}else {
			System.out.println(0);
		}
	}
}