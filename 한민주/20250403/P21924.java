package algorithm;

// 메모리: 180512 KB, 시간: 1136ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int first;
	int second;
	int ex;

	public Edge(int first, int second, int ex) {
		this.first = first;
		this.second = second;
		this.ex = ex;
	}

	@Override
	public int compareTo(Edge o) {
		return ex - o.ex;
	}
}

public class P21924 {
	static PriorityQueue<Edge> edges;
	static int[] union;

	static int find(int x) {
		if (union[x] == x)
			return x;
		else
			return union[x] = find(union[x]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		long all = 0;
		union = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			union[i] = i;
		}

		edges = new PriorityQueue<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Edge e = new Edge(a, b, w);
			edges.add(e);
			all += w;
		}

		int cnt = 0;
		long weight = 0;
		while (!edges.isEmpty()) {
			Edge now = edges.poll();
			if (find(now.first) != find(now.second)) {
				union[union[now.first]] = union[now.second];
				cnt++;
				weight += now.ex;
				if (cnt == n - 1) {
					break;
				}
			}
		}

		if (cnt != n - 1)
			System.out.println(-1);
		else
			System.out.println(all - weight);
	}
}