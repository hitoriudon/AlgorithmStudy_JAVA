import java.io.*;
import java.util.*;

public class P18115 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        int[] skills = new int[n];

        for (int i = 0; i < n; i++) {
            skills[i] = Integer.parseInt(st.nextToken());
        }

        int cardNumber = 1;
        //거꾸로 돌면서 처리해주기 2일때는 위에걸 뽑아주고 다시 넣어주는 형식 
        for (int i = n - 1; i >= 0; i--) {
            if (skills[i] == 1) {
                deque.addFirst(cardNumber);
            } 
            else if (skills[i] == 2) {
                int first = deque.pollFirst();
                deque.addFirst(cardNumber);
                deque.addFirst(first);
            } 
            else {
                deque.addLast(cardNumber);
            }
            cardNumber++;
        }
        
        //sb이용
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
