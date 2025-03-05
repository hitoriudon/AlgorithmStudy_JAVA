package study.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호 만들기: 사전식 배열된 암호를 모두 만들어 보기 -> 정렬 후 순열
// 실수: 조건을 제대로 보지 않아 한 개의 모음 & 두 개의 자음 조건을 무시했다.
public class P1759 {
	static int L, C;
	static String[] chars;
	static boolean[] isVowels;  // 모음 여부 저장
	static int[] vowels, consonants;  // 남은 모음 & 자음 개수 저장
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		chars = new String[C];
		isVowels = new boolean[C];
		vowels = new int[C + 1];  // 끝을 0으로 남겨 기저 전 조건 처리
		consonants = new int[C + 1];
		
		// 입력 저장 및 정렬
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < C; idx++) {
			chars[idx] = st.nextToken();
		}
		Arrays.sort(chars);
		
		// 모음 & 자음 구분
		int vowelCount = 0;
		int consonantCount = 0;
		for (int idx = C - 1; idx >= 0; idx--) {  // 역으로 돌리며 남은 모음/자음 개수 계산
			if ("aeiou".contains(chars[idx])) {  // 모음
				vowelCount++;
				isVowels[idx] = true;
				vowels[idx] = vowelCount;
			}
			else {
				consonantCount++;
			}
			// 실수: if-else 문 내부에서 처리 시 상대 회차에서는 남은 수 반영 X
			vowels[idx] = vowelCount;
			consonants[idx] = consonantCount;
		}

		checkedPW(0, 0, "", 0, 0);  // depth, start, string -> stringbuilder를 생각했으나, 초기화 문제로 인해 적합 X
	}

	private static void checkedPW(int depth, int start, String sb, int numVowel, int numConsonant) {
		// TODO Auto-generated method stub
		// 실수: 남은 개수로만 판단할 경우, 남은 개수는 충분하나 이미 끝에 도달한 경우를 놓친다!
		if (numVowel + (L - depth) < 1 || numConsonant + (L - depth) < 2 || numVowel + vowels[start] < 1 || numConsonant + consonants[start] < 2) {
			return;  // 조합은 start 이후의 값만 확인하므로 남은 문자 중 모음/자음 개수를 따져 가지치기
		}
		
		if (depth == L) {
			System.out.println(sb);
			return;
		}
		
		for (int i = start; i < C; i++) {
			if (isVowels[i]) {
				checkedPW(depth + 1, i + 1, sb + chars[i], numVowel + 1, numConsonant);
			}
			else {
				checkedPW(depth + 1, i + 1, sb + chars[i], numVowel, numConsonant + 1);
			}
		}
	}
}
