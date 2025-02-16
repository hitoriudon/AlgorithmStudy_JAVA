import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            stack = new ArrayDeque<>();
            for(char chr : br.readLine().toCharArray()){
                if (stack.isEmpty() || chr == '('){
                    stack.add(chr);
                }else{
                    if (stack.peek() == ')')
                        break;
                    stack.poll();
                }
            }
            if (stack.isEmpty()){
                sb.append("YES");
            }else{
                sb.append("NO");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
