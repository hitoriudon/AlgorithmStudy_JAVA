import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ArrayDeque<Integer> q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder("<");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = N;
		
		// 큐에 숫자 저장
		for(int i = 1; i <= N; i++) {
			q.addLast(i);
		}
		
		while (!q.isEmpty()) {
			// K-1번만큼 큐의 앞에서 pop한 뒤 바로 큐 뒤에 삽입
			for (int k = 0; k < K-1; k++) {
				q.addLast(q.removeFirst());
			}
			// K번째 수를 StringBuilder에 저장
			// 마지막 저장되는 수는 ", "가 들어가지 않기에, 삼항연산자로 검사
			sb.append(q.removeFirst()).append((--cnt > 0) ? ", " : "");
		}
		
		// '>'를 추가한 StringBuilder 출력
		System.out.println(sb.append(">"));
	}
}