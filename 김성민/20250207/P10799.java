import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10799 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String stickLine = br.readLine();
		int stickLineLength = stickLine.length();
		int sum = 0;
		int curStick = 0;
		for(int i = 0; i < stickLineLength; i++) {
			// 열린 괄호면 다음거 확인하면서 레이저인지 막대인지 확인
			if(stickLine.charAt(i) == '(') {
				// 열린 괄호 다음이 닫힌 괄호면 레이저
				// 인덱스 증가시키고 현재 막대 수 만큼 sum 증가
				if(stickLine.charAt(i + 1) == ')') {
					i++;
					sum += curStick;
				} 
				// 열린 괄호 다음이 열린 괄호면 막대
				// 막대가 늘어났으니 sum과 curStick 모두 1개 추가
				else {
					sum++;
					curStick++;
				}
			} 
			// 열린 괄호 다음이 아닌 닫힌 괄호는 막대기 끝
			// 막대 하나 지우기
			else {
				curStick--;
			}
		}
		System.out.println(sum);
	}
	
}
