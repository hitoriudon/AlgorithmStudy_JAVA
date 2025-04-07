package algorithm;

// 메모리: 12280KB, 시간: 76ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Star {
	int id;
	double x;
	double y;

	public Star(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
}

class Distance implements Comparable<Distance> {
	Star s1;
	Star s2;
	double value;

	public Distance(Star s1, Star s2, double value) {
		this.s1 = s1;
		this.s2 = s2;
		this.value = value;
	}

	@Override
	public int compareTo(Distance o) {
		return Double.compare(value, o.value);
	}

}

public class P4386 {
	static Star union[];

	static double getDistance(Star a, Star b) {
		return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Star stars[] = new Star[n];
		union = new Star[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
			union[i] = stars[i];
		}

		PriorityQueue<Distance> distances = new PriorityQueue<>();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				Distance d = new Distance(stars[i], stars[j], getDistance(stars[i], stars[j]));
				distances.add(d);
			}
		}

		int cnt = 0;
		double ans = 0;
		while (!distances.isEmpty()) {
			Distance now = distances.poll();
			if (!find(now.s1).equals(find(now.s2))) {
				union[union[now.s1.id].id] = union[now.s2.id];
				cnt++;
				ans += now.value;
				if(cnt == n-1) {
					System.out.printf("%.2f", ans);
					break;
				}
			}
		}
	}

	static Star find(Star s) {
		if (union[s.id].equals(s)) {
			return s;
		} else {
			return union[s.id] = find(union[s.id]);
		}
	}

}