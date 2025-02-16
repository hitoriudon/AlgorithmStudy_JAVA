import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// ArrayDeque를 이용한 스택 구현
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		String order;
		
		int N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			order = st.nextToken();
			switch(order) {
				case "push":
					stack.addLast(Integer.parseInt(st.nextToken()));
					break;
				case "pop":
					sb.append(stack.isEmpty() ? "-1" : stack.removeLast()).append("\n");
					break;
				case "size":
					sb.append(stack.size()).append("\n");
					break;
				case "empty":
					sb.append(stack.isEmpty() ? "1" : "0").append("\n");
					break;
				case "top":
					sb.append(stack.isEmpty() ? "-1" : stack.getLast()).append("\n");
					break;
				default:
					break;
			}
		}
		
		System.out.println(sb);
	}
}