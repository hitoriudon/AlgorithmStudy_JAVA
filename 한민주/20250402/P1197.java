package algorithm;

// 메모리: 47884 KB, 시간: 464 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
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

public class P1197 {
	static int union[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> edges = new PriorityQueue<>();

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			Edge newE = new Edge(a, b, w);
			edges.add(newE);
		}

		int cnt = 0;
		union = new int[v+1];
		for (int i = 1; i < v + 1; i++) {
			union[i] = i;
		}
		int ans = 0;
		while (true) {
			Edge now = edges.poll();
//			System.out.println(now.from + " " + now.to);
			if (find(now.from) == find(now.to))
				continue;
			union[union[now.from]] = union[now.to];
			ans += now.weight;
			cnt+=2;
			if(cnt >= v) {
				boolean noans = false;
				for(int i = 2; i < v+1; i++) {
					if(find(1) != find(i)) {
						noans = true;
						break;
					}
				}
				if(!noans) {
					break;
				}
			}
		}

		System.out.println(ans);
	}

	private static int find(int x) {
		if (union[x] == x)
			return x;
		else {
			return union[x] = find(union[x]);
		}
	}

}
