package study.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1966 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < N; test_case++) {
			StringTokenizer first = new StringTokenizer(br.readLine());
			int num_doc = Integer.parseInt(first.nextToken());
			int doc_idx = Integer.parseInt(first.nextToken());
			
			StringTokenizer second = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 0; i < num_doc; i++) {
				queue.offer(Integer.parseInt(second.nextToken()));
			}
			
			// 문제 해결 방법: Collections.max를 활용하여 더 큰 값이 있는지 확인, 경우에 따라 print or 출력, idx 업데이트
			int count = 0;
			while(doc_idx != -1) {
				int max_item = Collections.max(queue);
				int first_item = queue.poll();
				if (first_item != max_item) {
					queue.add(first_item);
					if (doc_idx == 0) {
						doc_idx = queue.size();
					}
				}
				else {
					count++;
				}
				doc_idx--;
			}
			System.out.println(count);
		}
	}
}
