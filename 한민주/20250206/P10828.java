import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class P10828{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n ; i++){
            String input = br.readLine();
            switch (input) {
                case "pop":
                    if(stack.isEmpty()) System.out.println(-1);
                    else System.out.println(stack.poll());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.isEmpty() ? 1:0);
                    break;
                case "top":
                    if(stack.isEmpty()) System.out.println(-1);
                    else System.out.println(stack.peekFirst());
                    break;
                default:
                    stack.push(Integer.parseInt(input.split(" ")[1]));
                    break;
            }
        }
    }
}