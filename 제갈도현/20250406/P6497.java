// 메모리 239796kb, 시간 668ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P6497 {
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

	static int house, road;
	static int[] parents;
	static Edge[] edgeList;

	static void make() {
		parents = new int[house];
		for (int i = 0; i < house; i++) {
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
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			house = Integer.parseInt(st.nextToken());
			road = Integer.parseInt(st.nextToken());
			
			if (house == 0 && road == 0)
				break;

			edgeList = new Edge[road];
			long maxMeter = 0;
			for (int i = 0; i < road; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				edgeList[i] = new Edge(a, b, w);
				maxMeter += w;
			}
			
			make();
			Arrays.sort(edgeList);

			int cnt = 0;
			long minMeter = 0;
			for (Edge e : edgeList) {
				if (union(e.from, e.to)) {
					minMeter += e.weight;
					cnt++;
					if (cnt == house - 1)
						break;
				}
			}
			
			System.out.println(maxMeter - minMeter);
		}
	}
}