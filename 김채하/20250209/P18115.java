
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P18115{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String tag;
        List<Integer> cardDeck = new LinkedList<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());


        for (int i = 1; i <= N; i++) {
            stack.addFirst(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            tag = stack.poll();
            if (tag.equals("1")) {
                cardDeck.add(0,i);
            }else if(tag.equals("2")) {
                cardDeck.add(1,i);
            }else {
                cardDeck.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int val : cardDeck) {
            sb.append(val).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}