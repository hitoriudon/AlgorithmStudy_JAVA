import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717 {
	static int N;
	static int[] parents;

	static void make() {
		parents = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (a == parents[a])
			return a;
		
		// 경로 압축
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (order == 0) {
				union(a, b);
			} else if (order == 1) {
				if (find(a) == find(b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
	}
}