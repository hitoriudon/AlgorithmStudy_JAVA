import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P4386 {
	static class Star {
		int num;
		double x;
		double y;

		Star(int num, double x, double y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static double distance(Star p1, Star p2) {
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

	static int find(int star) {
		if (star == parents[star]) {
			return star;
		}

		return parents[star] = find(parents[star]);
	}

	static boolean union(int star1, int star2) {
		int star1Root = find(star1);
		int star2Root = find(star2);

		if (star1Root == star2Root) {
			return false;
		}
		
		parents[star2Root] = star1Root;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int starAmount = Integer.parseInt(br.readLine());
		Star[] stars = new Star[starAmount];
		parents = new int[starAmount];
		for (int i = 0; i < starAmount; i++) {
			parents[i] = i;
		}
		for (int starCount = 0; starCount < starAmount; starCount++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			stars[starCount] = new Star(starCount, x, y);
		}

		edgeList = new ArrayList<>();
		for (int star1 = 0; star1 < starAmount; star1++) {
			for (int star2 = star1 + 1; star2 < starAmount; star2++) {
				double weight = distance(stars[star1], stars[star2]);

				edgeList.add(new Edge(stars[star1].num, stars[star2].num, weight));
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

		System.out.println(minLength);
	}
}