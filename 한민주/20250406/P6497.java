package algorithm;

// 메모리: 262400KB, 시간: 972ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Light implements Comparable<Light> {
	int x;
	int y;
	long z;

	public Light(int x, int y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int compareTo(Light o) {
		return Long.compare(z, o.z);
	}
}

public class P6497 {
	static int link[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m ==0 && n == 0) break; // 종료

			PriorityQueue<Light> lights = new PriorityQueue<Light>();

			long total = 0;

			link = new int[m + 1];

			for (int i = 1; i <= m; i++) {
				link[i] = i;
			}

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long z = Long.parseLong(st.nextToken());
				total += z;

				lights.add(new Light(x, y, z));
			}

			int cnt = 0;
			long on = 0;
			while (!lights.isEmpty()) {
				Light now = lights.poll();

				find(now.x);
				find(now.y);

				if (link[now.x] != link[now.y]) {
					link[link[now.x]] = link[now.y];
					cnt++;
					on += now.z;
					if (cnt == m - 1) {
						break;
					}
				}

			}
			sb.append(total - on);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int find(int h) {
		if (h == link[h])
			return h;
		else
			return link[h] = find(link[h]);
	}
}
