package algorithm;

// 메모리: 75692KB, 시간: 1288ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Tunnel implements Comparable<Tunnel> {
	int p1;
	int p2;
	int exp;

	public Tunnel(int p1, int p2, int exp) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.exp = exp;
	}

	@Override
	public int compareTo(Tunnel o) {
		return exp - o.exp;
	}

}

class Planet {
	int id;
	int x;
	int y;
	int z;

	public Planet(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class XComp implements Comparator<Planet> {
	@Override
	public int compare(Planet o1, Planet o2) {
		return o1.x - o2.x;
	}
}

class YComp implements Comparator<Planet> {
	@Override
	public int compare(Planet o1, Planet o2) {
		return o1.y - o2.y;
	}
}

class ZComp implements Comparator<Planet> {
	@Override
	public int compare(Planet o1, Planet o2) {
		return o1.z - o2.z;
	}
}

public class P2887 {
	static int union[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		union = new int[n];
		Planet[] xp = new Planet[n];
		Planet[] yp = new Planet[n];
		Planet[] zp = new Planet[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Planet p = new Planet(i, x, y, z);
			xp[i] = p;
			yp[i] = p;
			zp[i] = p;
			union[i] = i;
		}

		Arrays.sort(xp, new XComp());
		Arrays.sort(yp, new YComp());
		Arrays.sort(zp, new ZComp());

		PriorityQueue<Tunnel> tunnels = new PriorityQueue<Tunnel>();

		// x값 기준 거리 구하기
		for (int i = 0; i < n - 1; i++) {
			int dis = Math.abs(xp[i + 1].x - xp[i].x);
			Tunnel t = new Tunnel(xp[i].id, xp[i + 1].id, dis);
			tunnels.add(t);
		}

		// y값 기준 거리 구하기
		for (int i = 0; i < n - 1; i++) {
			int dis = Math.abs(yp[i + 1].y - yp[i].y);
			Tunnel t = new Tunnel(yp[i].id, yp[i + 1].id, dis);
			tunnels.add(t);
		}

		// z값 기준 거리 구하기
		for (int i = 0; i < n - 1; i++) {
			int dis = Math.abs(zp[i + 1].z - zp[i].z);
			Tunnel t = new Tunnel(zp[i].id, zp[i + 1].id, dis);
			tunnels.add(t);
		}

		long all = 0;
		int cnt = 0;

		while (!tunnels.isEmpty()) {
			Tunnel now = tunnels.poll();
			int p1 = now.p1;
			int p2 = now.p2;

			find(p1);
			find(p2);

			if (union[p1] != union[p2]) {
				union[union[p1]] = union[p2];
				all += now.exp;
				cnt++;
				if (cnt == n - 1)
					break;
			}
		}

		System.out.println(all);
	}

	static int find(int p) {
		if (p == union[p])
			return p;
		else
			return union[p] = find(union[p]);
	}
}
