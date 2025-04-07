// 메모리	244140kb, 시간 668ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P20040 {
	static int point;
	static int[] parents;

	static void make() {
		parents = new int[point + 1];
		for(int i = 1; i <= point; i++) {
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		point = Integer.parseInt(st.nextToken());
		int order = Integer.parseInt(st.nextToken());
		
		make();
		
		int res = 0;
		for (int i = 1; i <= order; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// find(a) == find(b)라면 a와 b는 이미 같은 그룹. 즉, a에서 b로 가는 경로가 이미 존재
			// a와 b 사이에 새 간선을 추가하면 a에서 b로 가는 경로가 두 개 이상 존재 -> 사이클 형성
			if(!union(a, b) && res == 0)
				res = i;
		}
		
		System.out.println(res);
	}
}