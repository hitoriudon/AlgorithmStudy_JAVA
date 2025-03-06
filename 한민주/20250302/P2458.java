package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2458 {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean visited[];
	static int tall[];
	static int small[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		tall = new int[n];
		small = new int[n];

		graph = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(b);
		}

		for (int i = 0; i < n; i++) {
			if (!graph.get(i).isEmpty()) {
				visited = new boolean[n];
				find(i, i);
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (tall[i] + small[i] == n - 1) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	static void find(int parent, int original) {
		for (int i = 0; i < graph.get(parent).size(); i++) {
			int child = graph.get(parent).get(i);
			if (!visited[child]) {
				visited[child] = true;
				small[child]++;
				find(child, original);
			}
		}

		if (parent == original)
			for (int i = 0; i < graph.size(); i++) {
				if (visited[i]) {
					tall[parent]++;
				}
			}
	}
}
