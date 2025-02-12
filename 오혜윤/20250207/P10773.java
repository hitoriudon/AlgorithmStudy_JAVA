import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// [실버4] 10773번. 제로
public class P10773 {
    public static void main(String[] args) throws Exception {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        
        while (K-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) stack.pop();
            else stack.addFirst(n);
        }
        int sum = 0;
        for (int n: stack) sum += n;
        System.out.print(sum);
    }
}
