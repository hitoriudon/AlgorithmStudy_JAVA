import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P1181 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            wordSet.add(br.readLine());
        }
        List<String> wordList = new ArrayList<>(wordSet);

        Collections.sort(wordList, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o1.compareTo(o2);
        });

        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }

    }
}