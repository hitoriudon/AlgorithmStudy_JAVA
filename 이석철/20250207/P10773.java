
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P10773 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int sum = 0;

        // 0이 나오면 직전 요소 값을 sum에서 빼주면 된다.
        for (int i = 0; i < k; i++){
            int n = Integer.parseInt(br.readLine());
            if (n == 0){
                int ret = stack.pop();
                sum -= ret;
            } else {
                stack.push(n);
                sum += n;
            }
        }

        System.out.println(sum);
    }
}