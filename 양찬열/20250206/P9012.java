package study.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P9012 {
	public static void main(String[] args) throws IOException{
		Deque<Character> VPS = new ArrayDeque();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < N; test_case++) {
			String line = br.readLine();
			boolean flag = false;
			VPS.clear();
			for (int i = 0; i < line.length(); i++) {
				char ps = line.charAt(i);
				if (ps == '(') {
					VPS.push('(');
				}
				else if (ps == ')' && VPS.size() != 0) {
					VPS.pop();
				}
				else {
					flag = true;
					System.out.println("NO");
					break;
				}
				
			}
			
			if (!flag && VPS.size() != 0) {
				System.out.println("NO");
			}
			else if(!flag) {
				System.out.println("YES");
			}
		}
	}
}
