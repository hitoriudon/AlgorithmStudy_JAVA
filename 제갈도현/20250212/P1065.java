import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int gap, res = 0;
		String tmpStr;
		int[] tmpArr;
		
		// 1부터 N까지 검사
		out: for (int i = 1; i <= N; i++) {
			// 한 자릿대의 수는 무조건 추가
			if (i < 100) { res += 1; continue; }
			
			// i를 String으로 변환 후, 이를 다시 분해하여 배열에 저장
			tmpStr = Integer.toString(i);
			tmpArr = new int[tmpStr.length()];
			for (int j = 0; j < tmpStr.length(); j++) {
				tmpArr[j] = tmpStr.charAt(j) - '0'; 
			}
			
			// 배열[0]과 배열[1]의 차이를 계산
			gap = tmpArr[1] - tmpArr[0];
			// 만약 등차수열이 아니라면 패스
			for (int j = 1; j < tmpStr.length()-1; j++) 
				if (tmpArr[j+1] - tmpArr[j] != gap) 
					continue out;
			// 맞다면 결과 증가
			res += 1;
		}
		
		System.out.println(res);
	}
}