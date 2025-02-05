import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class P1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int wordAmount = Integer.parseInt(br.readLine());

		ArrayList<String> words = new ArrayList<>();
		for (int wordCount = 0; wordCount < wordAmount; wordCount++) {
			String word = br.readLine();
			if(!(words.contains(word))) {
				words.add(word);
			}
		}
		Collections.sort(words, (word1, word2) -> {
			if (word1.length() == word2.length()) {
				return word1.compareTo(word2);
			} else {
				return word1.length() - word2.length();
			}
		});

		int wordsSize = words.size();
		for (int wordCount = 0; wordCount < wordsSize; wordCount++) {
			sb.append(words.get(wordCount)).append("\n");
		}

		System.out.println(sb);
	}

}