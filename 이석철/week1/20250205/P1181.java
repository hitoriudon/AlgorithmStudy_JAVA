import java.io.*;
import java.util.*;

public class P1181 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        // 중복 제거
        Set<String> wordsSet = new HashSet<>(words);
        List<String> wordsAfterRemoveDuplicate = new ArrayList<>(wordsSet);

        // 정렬, Comparator 인자 부분을 람다식으로 구현
        Collections.sort(wordsAfterRemoveDuplicate, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            }
            return o1.compareTo(o2);
        });

        System.out.println(wordsAfterRemoveDuplicate);
        for (String word : wordsAfterRemoveDuplicate) {
            System.out.println(word);
        }
    }
}