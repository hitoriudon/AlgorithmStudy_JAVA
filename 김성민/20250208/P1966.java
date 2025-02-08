import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		String orderLine = br.readLine();
		st = new StringTokenizer(orderLine, " ");

		int numAmount = Integer.parseInt(st.nextToken());
		int jump = Integer.parseInt(st.nextToken());

		// 1 ~ numAmount 만큼 채우기
		Queue<Integer> myQue = new LinkedList<>();
		for (int num = 1; num <= numAmount; num++) {
			myQue.add(num);
		}
		
		sb.append("<");
		// 마지막 콤마 빼기 위해 1까지만
		while(myQue.size() != 1) {
			// jump 횟수 - 1 만큼 빼고 넣기
			for(int jumpCount = 0; jumpCount < jump - 1; jumpCount++) {
				myQue.add(myQue.poll());
			}
			sb.append(myQue.poll()).append(", ");
		}
		sb.append(myQue.poll()).append(">");

		System.out.println(sb);
	}
}
