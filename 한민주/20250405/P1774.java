package algorithm;

// 메모리: 35640 KB, 시간: 436 ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class God {
	int id;
	double x;
	double y;

	public God(int id, double x, double y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}
}

class Distance implements Comparable<Distance>{
	God g1;
	God g2;
	double dis;

	public Distance(God g1, God g2, double dis) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.dis = dis;
	}

	@Override
	public int compareTo(Distance o) {
		return Double.compare(dis, o.dis);
	}

}

public class P1774 {
	static int link[];

	static double getDistance(God g1, God g2) {
		return Math.sqrt(Math.pow(Math.abs(g1.x - g2.x), 2) + Math.pow(Math.abs(g1.y - g2.y), 2));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		God[] gods = new God[n + 1];
		link = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			gods[i] = new God(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			link[i] = i;
		}

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int g1 = Integer.parseInt(st.nextToken());
			int g2 = Integer.parseInt(st.nextToken());
			find(g1);
			find(g2);
			if (link[g1] != link[g2]) {
				cnt++;
			}
			link[link[g1]] = link[g2];
		}

		PriorityQueue<Distance> distances = new PriorityQueue<Distance>();

		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				distances.add(new Distance(gods[i], gods[j], getDistance(gods[i], gods[j])));
			}
		}
		
		double ans = 0;
		while (cnt != -1 && !distances.isEmpty()) {
			Distance now = distances.poll();
			God g1 = now.g1;
			God g2 = now.g2;
			find(g1.id);
			find(g2.id);
			if(link[g1.id] != link[g2.id]) {
				link[link[g1.id]] = link[g2.id];
				ans += now.dis;
			}
		}
		
		System.out.printf("%.2f", ans);

	}

	static int find(int g) {
		if (link[g] == g) {
			return g;
		} else {
			return link[g] = find(link[g]);
		}
	}
}
