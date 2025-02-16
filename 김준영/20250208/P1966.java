/*
[1966, 프린터 큐] [큐] ㅠㅠ
	- 큐 뿐만 아니라, 어떤 자료구조든 배열로 넣을 수 있음!
	
[문제]
큐의 문서중 중요도가 가장 높은 문서만 인쇄 가능, 그렇지 않은경우 뒤에 재배치	
[입력]
T (테케)
N (문서의 개수) M (타겟 문서(0~))
N개 문서의 중요도 (1~9)
[출력]
각 테케에 대해 문서가 몇번째로 인쇄되는지 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			// =====

			LinkedList<int[]> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken()); // 문서 개수
			int m = Integer.parseInt(st.nextToken()); // 타겟 문서 id

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				q.offer(new int[] { i, Integer.parseInt(st.nextToken()) }); // 초기 위치, 중요도
			}

			int cnt = 0;

			while (!q.isEmpty()) {
				int[] temp = q.poll(); // 큐가 가장 큰 원소인지 판단

				boolean isMax = true;

				for (int i = 0; i < q.size(); i++) { // 큐에 남은 원소들과 비교
					if (temp[1] < q.get(i)[1]) { // 뽑힌 원소보다 중요도가 큰게 존재
						q.offer(temp);
						isMax = false;
						break;
					}
				}
				if (isMax) {// 뽑힌 원소가 가장 큰경우
					cnt++;
					if (temp[0] == m) {
						break;
					}
				}
			}

			System.out.println(cnt);

			// =====
		}
	}
}