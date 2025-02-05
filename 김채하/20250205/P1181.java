import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class P1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeSet<String> words = new TreeSet<>((o1, o2) -> {
            if (o1.length() > o2.length()){
                return 1;
            }else if(o1.length() ==  o2.length()){
                return o1.compareTo(o2);
            }else {
                return -1;
            }
        });

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        for(String word : words){
            sb.append(word);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
