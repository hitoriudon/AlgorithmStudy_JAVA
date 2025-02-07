import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] lst = br.readLine().toCharArray();
		// 스택을 int로 대체
		int q = 0, res = 0;
		
		int len = lst.length;
		for (int i = 0; i < len; i++) {
			char curr = lst[i];
			
			if (curr == '(') {
				// 만약 '('를 만난다면 스택에 저장
				q += 1;
			} else {
				if (i != 0 && lst[i-1] == '(') {
					// ')'를 만났는데 이전이 '('이면 레이저
					// 현재 스택에 저장된 괄호만큼 추가
					res += --q;
				} else {
					// 막대기의 끝, 스택에서 제거 후 결과 1만큼 증가
					q--;
					res += 1;
				}
			}
		}
		
		System.out.println(res);
	}
}