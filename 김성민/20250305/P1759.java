import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
	
	static int targetLength;
	static int alphabetAmount;
	static char[] alphabets;
	
	static char[] checkAlphabets = {'a', 'e', 'i', 'o', 'u'};

	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int curIdx, String curWord) {
		// 목표 길이와 같아지면 확인 후 리턴
		if(curWord.length() == targetLength) {
			// a, e, i, o, u 몇개 있는지 확인
			int check = 0;
			for(char alphabet : checkAlphabets) {
				if(curWord.contains(alphabet + "")) {
					check++;
				}
			}
			
			// 모음 1개 이상, 자음 2개 이상이면 답에 추가
			if(check >= 1 && curWord.length() - check >= 2) {
				sb.append(curWord).append("\n");
			}
			
			return;
		}
		
		// index가 갯수 넘어가면 리턴
		if(curIdx == alphabetAmount) {
			return;
		}
		
		// 알파벳 넣은 경우
		dfs(curIdx + 1, curWord + alphabets[curIdx]);
		// 알파벳 넣지 않은 경우
		dfs(curIdx + 1, curWord);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		targetLength = Integer.parseInt(st.nextToken());
		alphabetAmount = Integer.parseInt(st.nextToken());
		alphabets = new char[alphabetAmount];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int alphabetCount = 0; alphabetCount < alphabetAmount; alphabetCount++) {
			alphabets[alphabetCount] = st.nextToken().charAt(0);
		}
		
		// 알파벳을 미리 순서대로 정렬
		Arrays.sort(alphabets);
		
		dfs(0, "");
		
		System.out.println(sb);
	}
	
}
