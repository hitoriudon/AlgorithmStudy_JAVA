
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1707 {

	static ArrayList<Integer>[] graph;
	// true인지 false인 그룹 구분할 리스트
	static boolean[] groups;
	// 그룹이 이미 있는 애인지 visited로 확인
	static boolean[] visited;
	// 이분 그룹이 되는지 확인하는 변수
	// true, false로 그룹 나누기
	static boolean isGroup;

	static void checkGroup(int curNode, boolean group) {
		boolean nxetGroup = !group;

		for (int nextNode : graph[curNode]) {
			// 방문 했으면 지금 그룹이랑 다른지 확인
			// 지금 그룹과 다르다면 이분 그룹 X 중지
			if (visited[nextNode]) {
				if (groups[nextNode] != nxetGroup) {
					isGroup = false;
					return;
				}
			}
			// 방문 하지 않았다면 그룹 세팅 후 dfs
			else {
				visited[nextNode] = true;
				groups[nextNode] = nxetGroup;
				checkGroup(nextNode, nxetGroup);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int caseAmount = Integer.parseInt(br.readLine());
		while (caseAmount-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");

			int nodeAmount = Integer.parseInt(st.nextToken());
			int edgeAmount = Integer.parseInt(st.nextToken());
			graph = new ArrayList[nodeAmount + 1];
			for (int nodeCount = 0; nodeCount <= nodeAmount; nodeCount++) {
				graph[nodeCount] = new ArrayList<>();
			}
			groups = new boolean[nodeAmount + 1];
			visited = new boolean[nodeAmount + 1];

			// 그래프 만들기
			for (int edgeCount = 0; edgeCount < edgeAmount; edgeCount++) {
				st = new StringTokenizer(br.readLine(), " ");
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());

				graph[node1].add(node2);
				graph[node2].add(node1);
			}

			isGroup = true;
			for (int nodeCount = 1; nodeCount <= nodeAmount; nodeCount++) {
				if (!visited[nodeCount]) {
					visited[nodeCount] = true;
					groups[nodeCount] = true;
					checkGroup(nodeCount, true);
				}

				// 이분 그룹이 아닌게 나왔다면 더 이상 탐색 의미X 중지
				if (!isGroup) {
					sb.append("NO").append("\n");
					break;
				}
			}

			// 전부 이분 그룹이라면 YES
			if (isGroup) {
				sb.append("YES").append("\n");
			}
		}

		System.out.println(sb);

	}

}
