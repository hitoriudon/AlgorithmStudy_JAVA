import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class P1874 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int objNum;
        int next = 1;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 0; i < n; i++){
            objNum = Integer.parseInt(br.readLine());
            
            if(objNum < next){
                if(objNum > stack.lastElement()){
                    sb = new StringBuilder("NO");
                    break;
                }
                while(!stack.isEmpty() && objNum != stack.lastElement()){
                    stack.pop(); 
                    sb.append("-\n");
                }
                
            } else{
                while(!stack.isEmpty() && objNum != next){
                    stack.push(next++);
                    
                    sb.append("+\n");
                }
                sb.append("+\n");
                stack.push(next++);
            }
            stack.pop();
            sb.append("-\n");

        }
        System.out.println(sb);

    }
}