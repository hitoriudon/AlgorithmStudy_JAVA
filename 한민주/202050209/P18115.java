import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class P18115 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");

        LinkedList<Integer> deque = new LinkedList<Integer>();
        LinkedList<Integer> originalCards = new LinkedList<Integer>();
        for(int i = 0; i < n; i++){
            deque.add(i+1);
        }
        
        int m;
        int move;
    
        for(int i = str.length-1; i >=0; i--)
        {   
            m = Integer.parseInt(str[i]);   
            move = deque.pollFirst();    
            switch (m) {
                case 1:
                    originalCards.addFirst(move);
                    break;
                case 2:
                    originalCards.add(1, move);
                    break;
                case 3:
                    originalCards.addLast(move);
                    break;
            }
            
        }

        StringBuilder sb = new StringBuilder();
        for(int card : originalCards){
            sb.append(card);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}