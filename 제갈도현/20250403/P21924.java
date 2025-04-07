import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P21924 {
	static class Edge implements Comparable<Edge> {
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

	static int buildings, roads;
	static Edge[] edgeList;
	static int[] parents;

	static void make() {
		parents = new int[buildings + 1];

		for (int i = 1; i <= buildings; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		buildings = Integer.parseInt(st.nextToken());
		roads = Integer.parseInt(st.nextToken());
		
		// 각 도로의 최대 비용은 10^6, 도로는 최대 5×10^5개
		// 총 비용의 최대는 5×10^11이므로 long 사용
		long total = 0;

		edgeList = new Edge[roads];
		for (int i = 0; i < roads; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			total += w;

			edgeList[i] = new Edge(a, b, w);
		}

		make();
		Arrays.sort(edgeList);

		int count = 0;
		long minimum = 0;
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {
				minimum += e.weight;
				count++;
				if (count == buildings - 1) {
					break;
				}
			}
		}

		System.out.println(count == buildings - 1 ? total - minimum : -1);
	}
}