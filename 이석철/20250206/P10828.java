
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P10828 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++){
            String[] command = (br.readLine()).split(" ");
            char c = command[0].charAt(0); // 명령어 첫 글자
            int cSize = command[0].length(); // push와 pop 구분 (사이즈)
            
            // empty 항상 체크
            if (c == 't'){ // top
                System.out.println(stack.isEmpty() ? -1 : stack.get(stack.size() - 1));
            } else if (c == 's') { // size
                System.out.println(stack.size());
            } else if (c == 'e') { // empty
                System.out.println(stack.isEmpty() ? 1 : 0);
            } else if (c == 'p' && cSize == 3) { // pop
                System.out.println(stack.isEmpty() ? -1 : stack.pop());
            } else if (c == 'p' && cSize == 4) { // push
                int value = Integer.parseInt(command[1]);
                stack.push(value);
            }
        }
    }
}
