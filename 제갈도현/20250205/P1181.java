import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		// Set을 활용하여 중복 단어 제거 후 배열로 변환
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		String[] lst = set.toArray(new String[0]);
		
		// 사전순 정렬, 람다식을 이용한 길이순 정렬 실시
		Arrays.sort(lst);
		Arrays.sort(lst, (s1, s2) -> s1.length() - s2.length());
		
		// StringBuilder에 출력값 저장
		for (String s : lst) {
			sb.append(s).append("\n");
		}		
		System.out.println(sb);
	}
}