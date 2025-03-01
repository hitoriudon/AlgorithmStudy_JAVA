import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1707 {
	static ArrayList<Integer>[] graph;
	// true와 false로 A, B 두 그룹으로 분리
	static boolean[] nodeColor, isVisited;
	static boolean isCorrect;

	static void checkNode(int node, boolean isA) {
		// isA가 true면 A 그룹, false면 B 그룹으로 색칠
		nodeColor[node] = isA;

		for (int nextNode : graph[node]) {
			// 연결된 노드가 방문했던 노드가 아닌 경우 -> 아직 색칠하지 않은 노드
			if (!isVisited[nextNode]) {
				// 노드 방문 처리 후 이번 노드와 반대 색상으로 넘기기
				isVisited[nextNode] = true;
				checkNode(nextNode, !isA);
			} else {
				// 방문했던 노드인 경우 -> 색칠이 된 노드
				// 다음 노드와 색상이 같다면 이분 그래프가 아님
				if (nodeColor[nextNode] == isA) {
					isCorrect = false;
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int V, E;

		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			isCorrect = true;

			st = new StringTokenizer(br.readLine(), " ");
			// 그래프의 정점 개수 V, 간선 개수 E
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			// graph 배열에 ArrayList 할당
			graph = new ArrayList[V + 1];
			for (int i = 1; i < V + 1; i++) {
				graph[i] = new ArrayList<>();
			}

			int u, v;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());

				// 그래프 연결
				graph[u].add(v);
				graph[v].add(u);
			}

			// 노드 색상 값 저장
			nodeColor = new boolean[V + 1];
			// 방문 처리
			isVisited = new boolean[V + 1];
			for (int i = 1; i <= V; i++) {
				// 이분 그래프가 아니라면 for문 종료
				if (!isCorrect)
					break;
				
				// 방문 기록이 없는 노드인 경우
				if (!isVisited[i]) {
					// 방문 처리 후 노드 체크 시작
					isVisited[i] = true;
					checkNode(i, true);
				}
			}

			sb.append(isCorrect ? "YES" : "NO").append("\n");
		}

		System.out.println(sb);
	}
}