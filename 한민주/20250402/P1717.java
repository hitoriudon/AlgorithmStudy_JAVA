package algorithm;

// 메모리: 51376 KB, 시간: 352ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717 {
	static int u[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		u = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			u[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cal == 0) {
				find(a);
				u[find(b)] = u[a];
			} else {
				find(a);
				find(b);
				if (u[a] == u[b]) {
					sb.append("yes");
				} else {
					sb.append("no");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	static int find(int x) {
		if (x != u[x]) {
			return u[x] = find(u[x]);
		} else {
			return x;
		}
	}

}
