import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P1021 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int queSize = Integer.parseInt(st.nextToken());
		int outAmount = Integer.parseInt(st.nextToken());

		// index 확인을 위해 LinkedList로 구현
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int i = 1; i <= queSize; i++) {
			deque.addLast(i);
		}

		int[] targets = new int[outAmount];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < outAmount; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0;
		for (int outCount = 0; outCount < outAmount; outCount++) {

			// target의 현재 index 구하기
			int targetIdx = deque.indexOf(targets[outCount]);
			// 중간지점 구하기
			int half_idx = (deque.size() % 2 == 0 ? deque.size() / 2 - 1 : deque.size() / 2);


			// 중간지점 이거나 중간지점보다 앞에 있을 경우
			// 앞에 원소들 모두 뒤로 보내기
			if (targetIdx <= half_idx) {
				for (int i = 0; i < targetIdx; i++) {
					deque.addLast(deque.removeFirst());
					count++;
				}
			} 
			// 중간지점 보다 뒤에 있을 경우
			// 뒤에 원소들 모두 앞으로 보내기
			else {
				for (int j = targetIdx; j < deque.size(); j++) {
					deque.addFirst(deque.removeLast());
					count++;
				}

			}
			deque.removeFirst(); // 연산이 끝나면 맨 앞 원소를 삭제
		}

		System.out.println(count);

	}

}