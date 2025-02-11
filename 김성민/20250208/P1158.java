import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1158 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int caseAmount = Integer.parseInt(br.readLine());
		for(int caseCount = 0; caseCount < caseAmount; caseCount++) {
			String caseLine =br.readLine();
			
			int caseLineLength = caseLine.length();
			// 길이가 짝수가 아니면 괄호쌍이 맞지 않음
			if(caseLineLength % 2 != 0) {
				sb.append("NO\n");
				continue;
			}
			
			// 열린괄호 현재 갯수 체크
			// 열리괄호 들어오면 +, 닫힌괄호 들어오면 -
			int curOpenCount = 0;
			for(int i = 0; i < caseLineLength; i++) {
				if(caseLine.charAt(i) == '(') {
					curOpenCount++;
				} else {
					curOpenCount--;
					// 0보다 작다는 것은 현재 열린 괄호가 없는데
					// 닫힌 괄호가 나와 쌍이 맞지 않는 것
					if (curOpenCount < 0) {
						break;
					}
				}
			}
			
			// 0이 아니면 남은 괄호가 있다는 뜻
			sb.append((curOpenCount == 0 ? "YES\n" : "NO\n"));
		}
		
		System.out.println(sb);
	}
	
}