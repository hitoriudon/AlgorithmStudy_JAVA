import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1707 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer>[] graph;
		boolean[] visited;
		int[] team;
		String answer;

		int V, E;
		int s, e;
		int num;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList[V];
			visited = new boolean[V];
			team = new int[V];
			Arrays.fill(team, -1);
			answer = "YES";
			for (int j = 0; j < V; j++) {
				graph[j] = new ArrayList<>();
			}
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken())-1;
				e = Integer.parseInt(st.nextToken())-1;
				graph[s].add(e);
				graph[e].add(s);
			}


			ArrayDeque<Integer> stack = new ArrayDeque<>();
			out : for (int j = 0; j < V; j++) {
				if (visited[j]) continue;
				visited[j] = true;
				for (int k = 0; k < graph[j].size(); k++) {
					int n = graph[j].get(k);
					if(visited[n]) {
						if (team[j] == -1) team[j] = (team[n]+1) % 2;
						if(team[j] == team[n]) {
							answer = "NO";
							break out;
						}
					}
				}
				for (int k = 0; k < graph[j].size(); k++) {
					int n = graph[j].get(k);
					if(!visited[n]) {
						visited[n] = true;
						team[n] = (team[j]+1) % 2;
						stack.add(n);
					}
				}

				while(!stack.isEmpty()) {
					num = stack.pollLast();
					for (int k = 0; k < graph[num].size(); k++) {
						int n = graph[num].get(k);
						if(visited[n]) {
							if (team[num] == -1) team[num] = (team[n]+1) % 2;
							if(team[num] == team[n]) {
								answer = "NO";
								break out;
							}
						}
						if(!visited[n]) {
							visited[n] = true;
							team[n] = (team[num]+1) % 2;
							stack.add(n);
						}
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
