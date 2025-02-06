import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
1. 스택 이용
2. 종료 조건 : 마지막에 스택이 비워지도록
3. 비정상 종료 조건
	3.1. 스택 !empty일떄 pop 시도
	3.2. 스택이 마지막에 안비워짐
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayDeque<String> stk = new ArrayDeque<>();

		for (int i = 0; i < n; i++) { // Test Case
			String str = br.readLine();
			char gual;
			stk.clear();
			for (int j = 0; j < str.length(); j++) { // 괄호 처리
				gual = str.charAt(j);
//				System.out.println(gual);

				if (gual == '(') {
//					System.out.println(1);
					stk.add("ㅋ");
				} else if (gual == ')') {
//					System.out.println(2);
					if (stk.isEmpty()) {// stk 이 empty 아닐떄
						stk.add("ㅠ");
						break;
					} else { // stk이 empty 일떄
						stk.pop();
					}
				}
			}
			if (stk.isEmpty())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}