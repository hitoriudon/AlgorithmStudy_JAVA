package study.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// ABCDE: ABCDE 순서로 친구 관계인 관계가 존재하는지 확인 -> 모든 노드에 대해 탐색을 진행하여 5개 이상 연속하는 경우를 판별(방문 처리 필요)
// DP를 활용하여 각 점에서 도달할 수 있는 친구의 최대 개수를 구하고, 방문하지 않은 노드에서 실행, 방문하지 않은 노드에서 방문한 노드에 도달하면 값을 더하는 방식으로도 가능
// DP의 경우, 자신이 친구에 포함되어서는 안 된다 -> 순환 등 관계가 복잡하게 얽힌 경우 처리 어려움
public class P13023 {
	static int N, M;
	static boolean isReal;
	static boolean[] isVisited;
	static List<Integer>[] friends;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		friends = new List[N];
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<>();
		}
		
		int first, second;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			first = Integer.parseInt(st.nextToken());
			second = Integer.parseInt(st.nextToken());
			friends[first].add(second);
			friends[second].add(first);
		}
		
		isReal = false;
		for (int idx = 0; idx < N; idx++) {
			isVisited = new boolean[N];
			isVisited[idx] = true;
			findFriends(idx, 0);
			if (isReal) {
				break;
			}
		}
		
		if (isReal) {
			System.out.println("1");
		}
		else {
			System.out.println("0");
		}
	}

	private static void findFriends(int idx, int count) {
		// TODO Auto-generated method stub
		if (count == 4) {
			isReal = true;
			return;
		}
		
		for (int child : friends[idx]) {
			if (!isVisited[child]) {
				isVisited[child] = true;  // 실수: 방문 처리 누락
				findFriends(child, count + 1);
				isVisited[child] = false;  // 실수: 해당 문제의 경우, 상위 노드만 겹치지 않으면 되므로 초기화 필요
			}
			if (isReal) {
				return;
			}
		}
	}
}
