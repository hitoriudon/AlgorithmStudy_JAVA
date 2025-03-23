import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1086 {
	static ArrayList<Integer> graph[];
	static int delete;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int p,s = -1;



		for (int i = 0; i < N; i++) {
			p = Integer.parseInt(st.nextToken());
			if(p == -1) {
				s = i;
				continue;
			}
			graph[p].add(i);
		}

		delete = Integer.parseInt(br.readLine());
		if(s != delete) {
			dfs(s);
		}
		System.out.println(answer);
	}

	public static void dfs(int node) {
		if(graph[node].isEmpty()) {
			answer++;
			return;
		}
		int n;
		boolean turn = true;
		for(int idx = 0; idx < graph[node].size(); idx++) {
			n = graph[node].get(idx);
			if(n == delete) continue;
			dfs(n);
			turn = false;
		}

		if(turn) answer++;
	}

}
