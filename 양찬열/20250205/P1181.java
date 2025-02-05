package study.rearrange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P1181 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> words = new HashSet<>();
		for (int i = 0; i < N; i++) {
			words.add(br.readLine());
		}
		
		String[] words_arr = words.toArray(new String[0]);
		Arrays.sort(words_arr);
		Arrays.sort(words_arr, (o1, o2) -> o1.length() - o2.length());
		for (String item : words_arr) {
			System.out.println(item);
		}
	}
}
