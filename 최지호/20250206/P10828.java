import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String token = st.nextToken();
			if(token.equals("push")) {
				stack.push(st.nextToken());
			}
			else if(token.equals("pop")) {
				if(stack.empty()) {
					System.out.println("-1");
				}
				else {
				System.out.println(stack.pop());
				}
			}
			else if(token.equals("size")) {
				System.out.println(stack.size());
			}
			else if(token.equals("empty")) {
				if(!stack.empty()) {
					System.out.println("0");
				}
				else {
					System.out.println("1");
				}
			}
			else if(token.equals("top")) {
				if(!stack.empty()) {
					System.out.println(stack.peek());
				}
				else {
					System.out.println("-1");
				}
			}
		}
	}
}