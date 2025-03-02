import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P1707 {
	static boolean picked[], visited[];
	static ArrayList<ArrayList<Integer>> graph;
	static String answer;
	static int v, e;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			answer = "YES";
			graph = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[v + 1];
			for (int i = 0; i <= v; i++) {
				graph.add(new ArrayList<Integer>());
			}

			picked = new boolean[v + 1]; // 선택한 노드

			// 그래프 연결
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			// 그래프 탐색
			for (int i = 1; i <= v; i++) {
				if (!visited[i]) {
					visited[i] = true;
					search(i, true);
				}
			}

			sb.append(answer);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void search(int parent, boolean pick) {
		for (int i = 0; i < graph.get(parent).size(); i++) {
			if (answer.equals("NO")) {
				return;
			}

			int child = graph.get(parent).get(i);

			if (!visited[child]) {
				visited[child] = true; // 방문 처리
			} else {
				// 이전에 방문했을 때와 현재 뽑는 판단이 다르면 NO
				if (visited[child] && picked[child] != pick) {
					answer = "NO";
					return;
				}
				// 아니면 다음 노드 탐색
				continue;
			}

			if (pick) { // 선택
				picked[child] = true;
			}

			search(child, !pick); // 현재 뽑는 판단과 반대 전달
		}

	}

}
