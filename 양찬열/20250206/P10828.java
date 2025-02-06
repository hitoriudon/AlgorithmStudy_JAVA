package study.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P10828 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> stack = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			String cmd = br.readLine();
			
			if (cmd.contains("push")) {
				StringTokenizer st = new StringTokenizer(cmd);
				st.nextToken();
				int X = Integer.parseInt(st.nextToken());
				stack.push(X);
			}
			else if (cmd.contains("pop")) {
				if (stack.size() != 0) {
					int top = stack.pop();
					sb.append(top).append("\n");
				}
				else {
					sb.append("-1\n");
				}
			}
			else if (cmd.contains("size")) {
				int size = stack.size();
				sb.append(size).append("\n");
			}
			else if (cmd.contains("empty")) {
				if (stack.isEmpty()) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
			}
			else if (cmd.contains("top")) {
				if (stack.size() != 0) {
					int top = stack.peek();
					sb.append(top).append("\n");
				}
				else {
					sb.append("-1\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
