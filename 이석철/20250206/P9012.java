import java.io.*;
import java.util.*;
public class P9012{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        List<String> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++){
            list.add(br.readLine());
        }

        for (int i = 0; i < n; i++){
            String ps = list.get(i);
            Stack<Character> stack = new Stack<>();
            
            for (int x = 0; x < ps.length(); x++){
                stack.add(ps.charAt(x));

                int currentStackSize = stack.size();
                if (currentStackSize >= 2 && stack.get(currentStackSize - 2) == '(' && stack.get(currentStackSize - 1) == ')'){
                    stack.pop();
                    stack.pop();
                }
            }
            
            // 삼항 연산자로 줄일 수 있다!
            if (stack.size() <= 0){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}