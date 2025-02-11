import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P9012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			Stack<String> s = new Stack<String>();
			boolean chk = true;
			String input = br.readLine();
			for(int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				if(c == '(') {
					s.push("(");
				}
				else {
					if(!s.empty()) {
						s.pop();
					}
					else {
						chk = false;
						break;
					}
				}
				//디버깅
//				System.out.println(j+"번째 stack: " + s);
			}
			if(s.empty() && chk == true) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
}