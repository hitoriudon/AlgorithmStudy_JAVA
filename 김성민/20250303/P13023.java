import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	static boolean check = false;

	static void dfs(int curHuman, int depth) {
		// 이미 관계를 찾았거나 현재 깊이가 5인 친구 관계라면
		// 더 이상 탐색 필요X
		if (check || depth == 5) {
			check = true;
			return;
		}
		
		// 다음 친구 관계 들어가기
		visited[curHuman] = true;
		for (int nextHuman : graph[curHuman]) {
			if (!visited[nextHuman]) {
				dfs(nextHuman, depth + 1);
			}
		}
		visited[curHuman] = false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int humanAmount = Integer.parseInt(st.nextToken());
		int relationAmount = Integer.parseInt(st.nextToken());

		graph = new ArrayList[humanAmount];
		for (int humanCount = 0; humanCount < humanAmount; humanCount++) {
			graph[humanCount] = new ArrayList<>();
		}
		
		// 친구 관계 등록
		for (int i = 0; i < relationAmount; i++) {
			st = new StringTokenizer(br.readLine());
			int human1 = Integer.parseInt(st.nextToken());
			int human2 = Integer.parseInt(st.nextToken());
			graph[human1].add(human2);
			graph[human2].add(human1);
		}

		// dfs로 친구 관계 확인
		for (int humanCount = 0; humanCount < humanAmount; humanCount++) {
			visited = new boolean[humanAmount];
			
			// 깊이가 5인 친구 모임 찾기
			dfs(humanCount, 1);
			
			// check가 true면 깊이가 5까지 들어가는 관계가 있다는 뜻
			if (check) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}

}