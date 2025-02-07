import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[10799, 쇠막대기]
[스택]

문제
레이저 : ()
쇠막대기 : (...)
쇠막대기는 레이저를 찢어

풀이
1. 레이저는 '('를 통해 힘(power)을 얻음
2. ')'가 나오면 힘을 잃음
3. 전(bef)에 )가 나온경우, 전 문자가 '('일경우 res에 더함
*/

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int power = 0;
		char bef = 'a';
		int res = 0;
		int cur;

		for (int i = 0; i < input.length(); i++) { // input 탐색

			cur = input.charAt(i);

			if (cur == '(') {
				power++;
				bef = '(';
			} else if (cur == ')') {
				power--;
				if (bef == '(') {
					res += power;
				} else {
					res += 1; // '))' 일 때 + 1
				}
				bef = ')';
			}
		}

		System.out.println(res);
	}
}
