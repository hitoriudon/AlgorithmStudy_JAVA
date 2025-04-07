package algorithm;

// 메모리: 162540KB, 시간: 564 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P20040 {
	static int union[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		union = new int[n];
		for (int i = 0; i < n; i++) {
			union[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (find(a) == find(b)) {
				System.out.println(i + 1);
				return;
			}
			union[union[a]] = union[b];
		}
		System.out.println(0);
	}

	static int find(int x) {
		if (union[x] == x)
			return x;
		else
			return union[x] = find(union[x]);
	}

}
