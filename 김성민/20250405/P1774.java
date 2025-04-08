import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1774 {
	static class God {
		int num;
		double x;
		double y;

		God(int num, double x, double y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static double distance(God p1, God p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (weight < o.weight) {
				return -1;
			}
			return 1;
		}

	}

	static int[] parents;
	static ArrayList<Edge> edgeList;

	static int find(int god) {
		if (god == parents[god]) {
			return god;
		}

		return parents[god] = find(parents[god]);
	}

	static boolean union(int god1, int god2) {
		int god1Root = find(god1);
		int god2Root = find(god2);

		if (god1Root == god2Root) {
			return false;
		}
		
		parents[god2Root] = god1Root;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int godAmount = Integer.parseInt(st.nextToken());
		int aleadyAmount = Integer.parseInt(st.nextToken());
		
		God[] gods = new God[godAmount + 1];
		parents = new int[godAmount + 1];
		for (int i = 0; i < godAmount; i++) {
			parents[i] = i;
		}
		for (int godCount = 1; godCount <= godAmount; godCount++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			gods[godCount] = new God(godCount, x, y);
		}
		
		while(aleadyAmount-- > 0) {
			st = new StringTokenizer(br.readLine());
			int god1 = Integer.parseInt(st.nextToken());
			int god2 = Integer.parseInt(st.nextToken());
			union(god1, god2);
		}

		edgeList = new ArrayList<>();
		for (int god1 = 1; god1 <= godAmount; god1++) {
			for (int god2 = god1 + 1; god2 <= godAmount; god2++) {
				if(find(god1) != find(god2)) {
					double weight = distance(gods[god1], gods[god2]);

					edgeList.add(new Edge(gods[god1].num, gods[god2].num, weight));
				}
			}
		}
		Collections.sort(edgeList);

		double minLength = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);

			if (union(edge.from, edge.to)) {
				minLength += edge.weight;
			}
		}

		System.out.println(String.format("%.2f", minLength));
	}
}