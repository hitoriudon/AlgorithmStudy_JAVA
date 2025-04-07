package algorithm;

// 메모리: 15624 KB, 시간: 112ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976 {
	static int[] union;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		union = new int[n + 1];
		StringTokenizer st;

		for (int i = 1; i < n + 1; i++) {
			union[i] = i;
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int link = Integer.parseInt(st.nextToken());
				if (link == 1) {
					union[find(i)] = find(j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int root = find(first);
		while (st.hasMoreTokens()) {
			int temp = Integer.parseInt(st.nextToken());
			if (root != find(temp)) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");
	}

	private static int find(int a) {
		if (union[a] == a)
			return a;
		else
			return union[a] = find(union[a]);
	}

}