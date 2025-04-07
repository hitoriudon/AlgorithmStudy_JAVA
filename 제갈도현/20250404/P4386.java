// 메모리 12672kb, 시간 92ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4386 {
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	static int N;
	static int[] parents;
	static Edge[] edgeList;
	static double[][] stars;

	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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

	static double dist(int a, int b) {
		return Math.sqrt(Math.pow(stars[a][0] - stars[b][0], 2) + Math.pow(stars[a][1] - stars[b][1], 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		stars = new double[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			stars[i][0] = x;
			stars[i][1] = y;
		}

		edgeList = new Edge[N * (N - 1)];
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;

				// stars의 번호를 넣어줌
				edgeList[index++] = new Edge(i, j, dist(i, j));
			}
		}

		make();
		Arrays.sort(edgeList);

		int cnt = 0;
		double sum = 0;
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {
				sum += e.weight;
				cnt++;
				if (cnt == N - 1)
					break;
			}
		}

		System.out.println(sum);
	}
}
