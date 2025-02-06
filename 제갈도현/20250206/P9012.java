import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// int로 스택을 대체
		int stack = 0;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			char[] lst = br.readLine().toCharArray();
			stack = 0;
			
			for (char c : lst) {
				// '('를 만나면 스택에 저장 -> 1 증가
				if (c == '(')
					stack += 1;
				// ')'를 만나면 스택에서 제거 -> 1 감소
				else
					stack -= 1;
				
				// 스택이 비어있는 경우 종료
				if (stack < 0)
					break;
			}
			
			// 배열이 비어있지 않거나, 위 for문에서 종료된 경우 "NO"
			sb.append(stack == 0 ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
}
