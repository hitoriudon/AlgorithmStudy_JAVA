package study.stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P10799 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Character> stack = new ArrayDeque<>();
		String lines = br.readLine();
		int num_bars = 0;
		
		for (int i = 0; i < lines.length(); i++) {
			if ( i + 1 < lines.length() && lines.charAt(i) == '(' && lines.charAt(i + 1) == ')') {
				num_bars += stack.size();
				i++;
//				System.out.println(i + " " + stack.size());
			}
			else if (lines.charAt(i) == '(') {
				stack.push('(');
			}
			else if (lines.charAt(i) == ')') {
				stack.pop();
				num_bars += 1;  // 끝날 때마다 막대 추가
			}
		}
		num_bars += stack.size();
		
		System.out.println(num_bars);
	}
}
