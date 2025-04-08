import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P6497 {
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int find(int num) {
		if (num == parents[num]) {
			return num;
		}

		return parents[num] = find(parents[num]);
	}

	static boolean union(int num1, int num2) {
		int num1Root = find(num1);
		int num2Root = find(num2);

		if (num1Root == num2Root) {
			return false;
		}

		parents[num2Root] = num1Root;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			
			int numAmount = Integer.parseInt(st.nextToken());
			int edgeAmount = Integer.parseInt(st.nextToken());

			if (numAmount == 0 && edgeAmount == 0) {
				break;
			}

			parents = new int[numAmount];
			for (int numCount = 0; numCount < numAmount; numCount++) {
				parents[numCount] = numCount;
			}

			ArrayList<Edge> edges = new ArrayList<>();
			int fullSumWeight = 0;
			for (int edgeCount = 0; edgeCount < edgeAmount; edgeCount++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges.add(new Edge(from, to, weight));
				
				fullSumWeight += weight;
			}
			
			Collections.sort(edges);
			
			int minSumWeight = 0;
			for (Edge edge : edges) {
				if (union(edge.from, edge.to)) {
					minSumWeight += edge.weight;
				}
			}
			
			System.out.println(fullSumWeight);
			System.out.println(minSumWeight);
			System.out.println(fullSumWeight - minSumWeight);
		}
	}
}
