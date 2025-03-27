package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1516 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int buildings = Integer.parseInt(br.readLine());

		// 다음 테크
		ArrayList<Integer>[] nextBuildings = new ArrayList[buildings + 1];
		for (int i = 1; i <= buildings; i++) {
			nextBuildings[i] = new ArrayList<>();
		}

		// 진입차수
		int[] inDegree = new int[buildings + 1];
		// 건물 건설시간
		int[] buildtimes = new int[buildings + 1];
		for (int i = 1; i <= buildings; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			buildtimes[i] = Integer.parseInt(st.nextToken());

			while (true) {
				int input = Integer.parseInt(st.nextToken());

				// -1이면 입력 끝
				if (input == -1)
					break;

				// 다음 테크 기록
				nextBuildings[input].add(i);
				// 진입차수 증가
				inDegree[i]++;
			}
		}

		// 위상정렬을 위한 큐
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= buildings; i++) {
			// 진입차수 0이면 큐에 저장
			if (inDegree[i] == 0)
				q.add(i);
		}
		
		// 결과 배열 생성 후 기본적으로 각 건물의 건설시간 저장
		int[] totaltimes = new int[buildings + 1];
		for (int i = 1; i <= buildings; i++) {
			totaltimes[i] = buildtimes[i];
		}
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			for (int next : nextBuildings[current]) {
				// 다음 건물 연결 해제
				inDegree[next]--;
				
				// 건설시간 저장
				totaltimes[next] = Math.max(totaltimes[next], totaltimes[current] + buildtimes[next]);
				
				// 다음 건물 진입차수가 0이면 큐에 저장
				if (inDegree[next] == 0)
					q.add(next);
			}
		}
		
		for (int i = 1; i <= buildings; i++) {
			sb.append(totaltimes[i]).append("\n");
		}
		System.out.println(sb);
	}
}
