import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class P1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String[] words = new String[n];
		
		for(int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}
		
		//sort 길이 같으면 알파벳 순 정렬 else -> length 비교 
		Arrays.sort(words, new Comparator<>() {
			public int compare(String a, String b) {
				if(a.length() == b.length()) {
					return a.compareTo(b);
				}
				else {
					return a.length() - b.length();
				}
			}
		});
		
		//중복 없이 출력하기
		StringBuilder sb = new StringBuilder();
		sb.append(words[0] + "\n");
		for(int i = 1; i < n; i++) {
			if(!words[i].equals(words[i-1])) {
				sb.append(words[i] + "\n");
			}
		}
		System.out.println(sb);
	}
}