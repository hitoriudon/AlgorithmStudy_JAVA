
// [13023, ABCDE]
// 인싸 무리 (5명) 찾기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static ArrayList<Integer>[] friends;
	static boolean[] visited;
	static boolean possible = false;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // n명의 사람
		m = Integer.parseInt(st.nextToken()); // m개의 관계

		friends = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			friends[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) { // m개의 관계 입력
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());

			friends[p1].add(p2);
			friends[p2].add(p1);
		}

		visited = new boolean[n + 1];
		for(int i = 0; i <= n-1; i++) {			
			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;

			if(possible) break;
		}

		if (possible)
			System.out.println(1);
		else
			System.out.println(0);

	}

	static void dfs(int cur, int depth) {
//		System.out.println(cur + ": 내 친구는" + friends[cur]);
//		System.out.println("깊이; " + depth);
		if(possible) return;
		
		if(depth == 5) {
			possible = true;
			return;
		}
		
		if(friends[cur].size() == 0) {
			return;
		}
		
		for (int friend : friends[cur]) {
			if (!visited[friend]) {
				visited[friend] = true;
				dfs(friend, depth+1);
				visited[friend] = false;
                if (possible) return;
			}
		}

	}
}