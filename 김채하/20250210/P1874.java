import java.io.*;
import java.util.*;

public class P1874 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> stack = new ArrayDeque();
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int number = 0;
        int value = 0;
        for(int i = 0; i < N; i++) {
            int target = Integer.parseInt(br.readLine());
            while(stack.isEmpty() || stack.peekLast() != target) {
                sb.append("+").append("\n");
                stack.addLast(++number);
                if(number > N) {
                    System.out.println("NO");
                    return;
                }
            }
            stack.pollLast();
            sb.append("-").append("\n");
        }
        System.out.print(sb);
    }

}
