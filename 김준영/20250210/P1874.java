import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
[1874, 스택 수열]
[문제]

1~n까지의 수를 스택에 넣었다 뽑아 수열을 만들 수 있다.
스택 푸시는 반드시 오름차 순
주어진 수열을 스택으로 만들 수 있는지 확인

[입력]
n (카드 개수)
~ (n개의 수열을 이루는 정수 주어짐) (같은 정수 없음)

[출력]
푸시+, 팝-

[풀이]

 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> stk = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		int cur = 1; // 푸시할 숫자
		boolean possible = true;

		for(int i = 0 ; i < n ; i ++) { // want 반복
			int want = Integer.parseInt(br.readLine());
			
			// 원하는 슷자까지 푸시
			while (cur <= want) {
				stk.push(cur);
				sb.append('+').append('\n');
				cur ++;
			}
			
			// 원하는 숫자 == 스택 맨위 숫자 -> pop
			if (!stk.isEmpty() && stk.peek() == want) {
				stk.pop();
				sb.append('-').append('\n');
			}else {
				possible = false; // 원하는 숫자가 못나옴
				break;
			}
		}
		if(!possible) {
			System.out.println("NO");
		}else {
			System.out.println(sb.toString());
		}
	}
}