package algorithm;
// 메모리: 81640 KB, 시간: 546ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2056 {

	public static void main(String[] args) throws Exception {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> works = new ArrayList<ArrayList<Integer>>(); // 선행 작업 기록
		int n = Integer.parseInt(br.readLine());
		int time[] = new int[n + 1]; // 작업 당 걸리는 시간
		int preWork[] = new int[n + 1]; // 선행 작업 개수
		StringTokenizer st;
		ArrayDeque<Integer> todo = new ArrayDeque<Integer>(); // 선행 작업 없는 일
		for (int i = 0; i <= n; i++) {
			works.add(new ArrayList<Integer>());
		}

		// 작업 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			preWork[i] = Integer.parseInt(st.nextToken());

			// 선행 작업 없으면 todo에 추가
			if (preWork[i] == 0)
				todo.add(i);

			// 선행 작업의 후행 작업에 추가
			for (int j = 0; j < preWork[i]; j++) {
				int p = Integer.parseInt(st.nextToken());
				works.get(i).add(p);
			}
		}

		// 선행 작업 중 가장 오래 걸리는거 시간에 더하기 => 최댓값
		int maxT = 0;
		for (int i = 1; i <= n; i++) {
			int maxPre = 0;
			for (int j = 0; j < works.get(i).size(); j++) {
				maxPre = Math.max(maxPre, time[works.get(i).get(j)]);
			}
			time[i] += maxPre;
			maxT = Math.max(maxT, time[i]);
		}

		System.out.println(maxT);

	}

}
