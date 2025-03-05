package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class g5_1759 {
	static int L, C;
	static char[] alpha;
	
	static void printAlpha(int flag) {
		int vowel = 0, consonant = 0;
		char[] password = new char[L];
		
		int index = 0;
		for (int i = 0; i < C; i++) {
			if ((flag & (1 << i)) != 0) {
				char alphabet = alpha[i];
				
				// 모음이라면 모음 갯수 추가
				if (alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u')
					vowel++;
				// 자음이라면 자음 갯수 추가
				else 
					consonant++;
				
				password[index++] = alphabet;
			}
		}
		
		// 만약 자음 2개, 모음 1개 이상이라면 출력
		if (vowel >= 1 && consonant >= 2) {
			for (char c : password) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	static void selectAlpha(int depth, int curr, int flag) {
		// L만큼 선택시 출력 시작
		if (depth == L) {
			printAlpha(flag);
			return;
		}

		// 비트마스킹을 이용한 select 검사
		for (int i = curr; i < C; i++) {
			if ((flag & (1 << i)) != 0)
				continue;
			
			selectAlpha(depth + 1, i + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 사용할 알파벳 입력받기
		alpha = new char[C];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		// 알파벳 정렬
		Arrays.sort(alpha);

		// 조합을 이용한 알파벳 선택 시작
		selectAlpha(0, 0, 0);
	}
}
