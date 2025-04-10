package MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 그리드 네트워크: 격자에서의 MST -> 크루스칼(정렬 후 union-find) or 프림(그룹 전체와의 최단 거리 선택)
// 일반적으로 희소 그래프는 크루스칼, 밀집 그래프는 프링이 유리
// 입력값 형태 및 희소/밀집 판단 결과, 크루스칼이 적합할 듯 -> but, prim으로 시도해보자
public class P18769 {
	static int T, R, C;
	static boolean[] isVisited;  // 방문 처리
	static int[] minDistance;
	static List<int[]>[] adj;  // 인접 리스트, 500*500 < 10^9이므로, C*r + c를 index로 사용
	static PriorityQueue<Node> queue;  // 최단 거리 기준 정렬
	static int minCost;
	static class Node implements Comparable<Node> {
		int idx, minDis;
		
		Node(int idx){
			this.idx = idx;
			this.minDis = Integer.MAX_VALUE;  // 초기화
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.minDis, o.minDis);  // 최단 거리 기준 비교
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			adj = new List[R*C];
			for (int i = 0; i < R*C; i++) {
				adj[i] = new ArrayList<>(4);  // 최대 크기 4
			}
			
			// 좌우 노선
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c < C; c++) {
					int weight = Integer.parseInt(st.nextToken());
					adj[r*C + c - 1].add(new int[] {r*C + c, weight});
					adj[r*C + c].add(new int[] {r*C + c - 1, weight});
				}
			}
			
			// 상하 노선
			for (int r = 1; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					int weight = Integer.parseInt(st.nextToken());
					adj[(r - 1)*C + c].add(new int[] {r*C + c, weight});
					adj[r*C + c].add(new int[] {(r - 1)*C + c, weight});
				}
			}

			// 프림 알고리즘
			queue = new PriorityQueue<>();
			isVisited = new boolean[R*C];
			minDistance = new int[R*C];
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			minCost = 0;
			minDistance[0] = 0;
			Node start = new Node(0);  // 임의의 점으로 시작
			start.minDis = minDistance[start.idx];
			queue.offer(start);
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				if (isVisited[node.idx]) {
					continue;
				}
				
				minCost += node.minDis;
				isVisited[node.idx] = true;
				
				for (int[] adjacent: adj[node.idx]) {
					if (!isVisited[adjacent[0]] && minDistance[adjacent[0]] > adjacent[1]) {
						minDistance[adjacent[0]] = adjacent[1];
						Node newNode = new Node(adjacent[0]);
						newNode.minDis = minDistance[newNode.idx];
						queue.offer(newNode);  // 최단 노드 업데이트
					}
				}
			}
			sb.append(minCost).append("\n");
		}
		System.out.println(sb.toString());
	}
}
