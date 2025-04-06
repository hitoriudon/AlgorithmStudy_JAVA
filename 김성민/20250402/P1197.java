import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1197 {
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int nodeAmount;
	static int[] parents;

	static void make() {
		parents = new int[nodeAmount + 1];
		for (int nodeCount = 1; nodeCount <= nodeAmount; nodeCount++) {
			parents[nodeCount] = nodeCount;
		}
	}

	static int find(int node) {
		if (node == parents[node])
			return node;
		return parents[node] = find(parents[node]);
	}

	static boolean union(int node1, int node2) {
		int node1Root = find(node1);
		int node2Root = find(node2);
		if (node1Root == node2Root)
			return false;

		parents[node2Root] = node1Root;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		nodeAmount = Integer.parseInt(st.nextToken());
		int edgeAmount = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[edgeAmount];
		for (int edgeCount = 0; edgeCount < edgeAmount; edgeCount++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[edgeCount] = new Edge(from, to, weight);
		}

		make();
		Arrays.sort(edges);

		int count = 0;
		long sumWeight = 0;
		for (Edge edge : edges) {
			if (union(find(edge.from), find(edge.to))) {
				sumWeight += edge.weight;
				if (++count == nodeAmount - 1) {
					break;
				}
			}
		}

		System.out.println(sumWeight);
	}
}
