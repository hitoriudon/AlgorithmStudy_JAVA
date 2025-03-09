package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
	static int l, c;
	static char word[];
	static StringBuilder sb;

	static void make(int n, StringBuilder answer, int cnt1, int cnt2) {
		if (2 - cnt2 > l - answer.length()) {
			return;
		}
		if (answer.length() == l && cnt1 >= 1) {
			sb.append(answer);
			sb.append("\n");
			return;
		}

		StringBuilder nextAnswer;
		for (int i = n; i < c; i++) {

			if (answer.length() + c - n < l) {
				return;
			}
			nextAnswer = new StringBuilder();

			nextAnswer.append(answer);
			nextAnswer.append(word[i]);
			if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u') {
				make(i + 1, nextAnswer, cnt1 + 1, cnt2);
			} else {
				make(i + 1, nextAnswer, cnt1, cnt2 + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		word = new char[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			word[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(word);

		make(0, new StringBuilder(), 0, 0);
		System.out.println(sb);

	}

}