import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
	static boolean visited[];
	static ArrayList<Integer>[] friendList;
	static int answer = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		friendList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			friendList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fri1 = Integer.parseInt(st.nextToken());
			int fri2 = Integer.parseInt(st.nextToken());
			friendList[fri1].add(fri2);
			friendList[fri2].add(fri1);
		}

		for (int idx = 0; idx < N; idx++) {
			visited = new boolean[N];
			visited[idx] = true;
			dfs(idx, 0);
		}
		System.out.println(answer);
	}

	public static void dfs(int node, int time) {
		if (answer == 1)
			return;
		if(time >= 4) {
			answer = 1;
			return;
		}

		for (int i = 0; i < friendList[node].size(); i++) {
			int n = friendList[node].get(i);
			if (!visited[n]) {
				visited[n] = true;
				dfs(n, time + 1);
				visited[n] = false;
			}
		}
	}
}
