package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068 {
	static int answer = 0;
	static int n, remove;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tree.add(new ArrayList<Integer>());
		}

		int root = 0;
		for (int i = 0; i < n; i++) {
			int idx = Integer.parseInt(st.nextToken());
			if (idx == -1) {
				root = i;
			} else {
				tree.get(idx).add(i);
			}
		}

		remove = Integer.parseInt(br.readLine());

		if(root != remove)
			find(root);

		System.out.println(answer);
	}

	// 리프노드 찾기
	static void find(int parent) {
		// 자식이 없다면(리프노드)
		if (tree.get(parent).isEmpty()) {
			answer++;
			return;
		}
		
		// 자식 수 세기(제거된 자식만 있을 수 있으니까)
		int childCnt = 0;
		for (int i = 0; i < tree.get(parent).size(); i++) {
			int child = tree.get(parent).get(i);
			if(child == remove) continue;
			find(child);
			childCnt++;
		}
		// 자식 0이라면 
		if(childCnt == 0) answer++;
	}

}
