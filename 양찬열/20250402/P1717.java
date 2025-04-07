package HW0326;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 서로소 집합: union-find
public class P1717 {
	static int T, N, M;
	static int[] parents, rank;
	
	static void make() {
		parents = new int[N + 1];  // 1부터 시작
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int node) {
		if (node == parents[node]) return node;
		return parents[node] = find(parents[node]);  // path 압축
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
//		if (aRoot == bRoot) return false;
		
		// rank
		if (rank[aRoot] == rank[bRoot]) {
			parents[bRoot] = aRoot;
			rank[aRoot] += 1;
		}
		else if (rank[aRoot] > rank[bRoot]) {
			parents[bRoot] = aRoot;
		}
		else {
			parents[aRoot] = bRoot;
		}
		return true;
	}
	
	static String test(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return "YES";
		
		return "NO";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		T = Integer.parseInt(br.readLine());
		T = 1;
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			
//			sb.append("#").append(t).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (cmd == 0) {
					union(a, b);
				}
				if (cmd == 1) {
					sb.append(test(a, b)).append("\n");
				}
			}
//			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
