import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10828 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		// 스택으로 이용한 리스트
		int[] myStack = new int[10001];
		// 스택의 탑처럼 이용할 변수 -1 이면 가르키는게 없다
		int top = -1;
		int orderAmount = Integer.parseInt(br.readLine());
		for(int orderCount = 0; orderCount < orderAmount; orderCount++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			
			if (order.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				// 입력전에 top을 늘리면서 넣기
				myStack[++top] = num;
			} else if (order.equals("pop")) {
				// 가르키는 것이 있으면 꺼내고 top 내리기 없으면 -1
				sb.append(top == -1 ? -1 : myStack[top--]).append("\n");
			} else if (order.equals("size")) {
				// 배열 index로 가르키고 있어 + 1해서 사이즈 출력
				sb.append(top + 1).append("\n");
			} else if (order.equals("empty")) {
				// 가르키는 것이 없으면 1 있으면 0
				sb.append(top == -1 ? 1 : 0).append("\n");
			} else if (order.equals("top")) {
				// 맨 위 요소 찍기
				sb.append(top == -1 ? -1 : myStack[top]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
