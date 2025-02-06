import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
스택 구현
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> stk = new ArrayDeque<>();

		for (int i = 0; i < n; i++) { // 입력
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "push": {
				stk.offerLast(Integer.parseInt(st.nextToken()));
//				System.out.println("add : " + stk.peekLast());
				break;
			}

			case "pop": {
				if (stk.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(stk.pollLast());
				}
				break;

			}

			case "size": {
				System.out.println(stk.size());
				break;

			}
			case "empty": {
				if (stk.isEmpty())
					System.out.println(1);
				else
					System.out.println(0);
				break;

			}
			case "top": {
				if (stk.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(stk.peekLast());
				}
				break;

			}
			}
		}
	}
}
