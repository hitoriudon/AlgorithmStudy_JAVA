import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class s3_1021 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int half, toFind, dqSize, res = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// indexOf를 사용하기 위해 LinkedList로 구현
		LinkedList<Integer> dq = new LinkedList<>();
		for (int i = 1; i <= N; i++) dq.addLast(i); 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			// index의 절반을 구한 뒤, 찾아야 할 숫자의 index와 비교하여 조건문 수행
			half = dq.size() % 2 == 0 ?  dq.size() / 2 - 1 : dq.size() / 2;
			toFind = dq.indexOf(Integer.parseInt(st.nextToken()));
			
			if (toFind <= half) {
				// 찾아야할 숫자의 index가 절반보다 작거나 같은 경우
				for (int j = 0; j < toFind; j++) {
					// 왼쪽으로 이동
					dq.addLast(dq.removeFirst());
					res++;
				}
			} else {
				// 찾아야할 숫자의 index가 절반보다 큰 경우
				dqSize = dq.size();
				for (int j = 0; j < dqSize - toFind; j++) {
					// 오른쪽으로 이동
					dq.addFirst(dq.removeLast());
					res++;
				}
			}
			
			// 숫자 뽑아내기
			dq.removeFirst();
		}
		
		System.out.println(res);
	}
}