package HW0327;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최소 스패닝 트리: 최소 신장 트리 계산-kruscal 알고리즘
public class P1197 {
	static int T, V, E;
	static Edge[] edges;
	static int[] parents, ranks;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void make() {
		parents = new int[V + 1];
		ranks = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		if (ranks[aRoot] == ranks[bRoot]) {
			ranks[aRoot] += 1;
			parents[bRoot] = aRoot;
		}
		else if (ranks[aRoot] > ranks[bRoot]) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = 1;
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new Edge[E];
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			make();
			Arrays.sort(edges);
			long totalWeight = 0;  // 실수: input size를 고려하면, int로는 overflow 발생(2*10^5*10^6 > 10^9)
			for (Edge edge: edges) {
				if (union(edge.from, edge.to)) {
					totalWeight += edge.weight;
				}
			}
			
			sb.append(totalWeight).append("\n");
		}
		System.out.println(sb.toString());
	}
}
