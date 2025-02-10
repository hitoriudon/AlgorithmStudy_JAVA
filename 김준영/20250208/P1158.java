/*
[S4] [1158, 요세푸스 문제] [큐]
	+ 서큘러 큐..?
[문제]
1~N 까지의 사람이 원을 이루어 앉아있고, K번째 사람 제거 N명의 사람이 모두 제거될 때 까지 지속
[입력]
N (사람 수) K (K 번째 사람 제거)
[출력]
요세포스 순열 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();

		for(int i = 1; i <= n; i++) { // 큐 초기화
			q.offer(i);
		}
		
//		System.out.println(q);
		System.out.print('<');
		while(!q.isEmpty()) {
			int kk = k;
			while(kk-- > 1) {
				q.offer(q.poll());
			}
			System.out.print(q.poll());
			if(q.size()>=1) {
				System.out.print(", ");
			}
		}
		System.out.print('>');

	}
}