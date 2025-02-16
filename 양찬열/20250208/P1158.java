package study.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P1158 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 요세푸스 순열: 원형 배열에서 K번째 사람을 제거할 때, 제거되는 사람의 index
		// linkedList로 원하는 위치 제거하며 반복, queue로는 어떻게? -> 빌 때까지 반복하며 K 배수일 때마다 출력
		// queue 사용 시 시간(476)이 linkedList 사용 시 시간(144)보다 길다. -> linkedList는 하나씩 시도하는 대신 명시된 위치를 제거해 시간 단축
//		Queue<Integer> queue = new ArrayDeque<>();
//		for (int i = 0; i < N; i++) {
//			queue.offer(i + 1);
//		}
//		
//		int count = 0;
//		StringBuilder sb = new StringBuilder("<");
//		while (!queue.isEmpty()) {
//			count++;
//			int item = queue.poll();
//			if (count%K == 0) {
//				sb.append(item);
//				if (queue.size() != 0) {
//					sb.append(", ");
//				}
//			}
//			else {
//				queue.offer(item);
//			}
//		}
//		sb.append(">");
//		System.out.println(sb.toString());
		
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}
		
		int idx = K - 1;
		StringBuilder sb = new StringBuilder("<");
		while (!list.isEmpty()) {
			sb.append(list.get(idx));
			list.remove(idx);
			idx += K - 1;
			if (list.size() == 0) {
				break;
			}
			int size = list.size();
			while (idx >= size) {
				idx -= size;
			}
			sb.append(", ");
		}
		sb.append(">");
		System.out.println(sb.toString());		
	}
}
