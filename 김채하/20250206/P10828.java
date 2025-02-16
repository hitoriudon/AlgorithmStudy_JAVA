import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayDeque<String> stack = new ArrayDeque<>();
        String oprator;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            oprator = st.nextToken();

            if(oprator.equals("push")){
                stack.addLast(st.nextToken());
            }else if (oprator.equals("size")){
                    sb.append(stack.size()).append("\n");
            }else if (oprator.equals("empty")){
                sb.append(stack.isEmpty()?1:0).append("\n");
            }else{
                if (stack.isEmpty()){
                    sb.append(-1).append("\n");
                    continue;
                }
                if (oprator.equals("pop")){
                    sb.append(stack.pollLast()).append("\n");
                }else {
                    sb.append(stack.peekLast()).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
