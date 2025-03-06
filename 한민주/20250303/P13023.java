package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		for (int i = 0; i < n; i++) {
			if (!graph.get(i).isEmpty()) {
				visited = new boolean[n];
				visited[i] = true;
				check(i, 0);
			}
			if (answer == 1)
				break;
		}

		System.out.println(answer);
	}

	static void check(int f1, int cnt) {
		
		if (cnt == 4) {
			answer = 1;
			return;
		}
		for (int i = 0; i < graph.get(f1).size(); i++) {
			if (answer == 1)
				return;
			int f2 = graph.get(f1).get(i);
			if (!visited[f2]) {
				visited[f2] = true;
				check(f2, cnt + 1);
				visited[f2] = false;
			}
		}
	}

}
