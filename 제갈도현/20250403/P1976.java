import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976 {
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
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		make();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					union(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int first = find(Integer.parseInt(st.nextToken())), res = 1;
		for (int i = 1; i < M; i++) {
			if (find(Integer.parseInt(st.nextToken())) != first) {
				res = 0;
				break;
			}
		}
		
		System.out.println(res == 1 ? "YES" : "NO");
	}
}