import java.io.*;
import java.util.*;

public class P1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;

        List <Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            list.add(i+1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        int idx = 0;
        while(!list.isEmpty()) {
            idx = (idx + k) % list.size();
            sb.append(list.remove(idx));
            if (!list.isEmpty()) {
                sb.append(", ");
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
