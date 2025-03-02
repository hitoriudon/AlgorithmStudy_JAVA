package study.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 트리: 트리 구조로 만들면 편하겠지만, 이번에는 인접 리스트 + DFS로 시도해 보자
public class P1068 {
	static int N, removeIdx;
	static int v, leafCount;
	static Node[] nodes;  // ArrayList보다 배열이 더 빠른 이유: 자동 형변환 X, 배열은 heap이 아니라 cache에 저장
	static class Node{
		int parent = -1;
		List<Integer> children = new ArrayList<>();
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		leafCount = N;  // 전부 leaf로 가정
		nodes = new Node[N];
		// 실수: 자식 노드의 번호가 부모 노드보다 작을 수 있다!
		for (int idx = 0; idx < N; idx++) {
			nodes[idx] = new Node();
		}
		
		// 연결 리스트 생성
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			v = Integer.parseInt(st.nextToken());
			if (v != -1) {
				if (nodes[v].children.isEmpty()) {  // leaf였던 노드에 처음 접근 시 감소
					leafCount--;
				}
				nodes[v].children.add(idx);
				nodes[idx].parent = v;
			}
		}
		
		removeIdx = Integer.parseInt(br.readLine());
		// 부모의 자신이 본인 뿐이었다면 리프 노드 1개 추가
		// 실수: 루트 노드를 제거하는 경우, -1에서 outOfBound!
		int parentIdx = nodes[removeIdx].parent;
		if (parentIdx != -1 && nodes[parentIdx].children.size() == 1){  // 자신만 존재
			leafCount++;
		}
		// dfs로 제거
		removeNodes(removeIdx);
		System.out.println(leafCount);
	}

	private static void removeNodes(int idx) {
		// TODO Auto-generated method stub
		if (nodes[idx].children.isEmpty()) {
			leafCount--;
			return;
		}
		
		for (int child : nodes[idx].children) {
			removeNodes(child);
		}
	}
}
