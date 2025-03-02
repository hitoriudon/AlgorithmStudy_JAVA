import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// P2458 키 순서: 위상 정렬  + DFS
public class P2458 {
	static int T, N, M;
	static int knowHeight;
	static List<Integer>[] pre, post;  // 뒷쪽, 앞쪽 방향 그래프
	static boolean[] visited;
//	static int[] inDegree;  // 삽입차수

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		T = Integer.parseInt(br.readLine()); -> 입력 형식 변경
		T = 1;
		for (int test = 1; test <= T; test++) {
//			N = Integer.parseInt(br.readLine()); -> 입력 형식 변경
//			M = Integer.parseInt(br.readLine()); -> 입력 형식 변경
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
//			inDegree = new int[N];  // 정렬 필요성 X
			visited =  new boolean[N];
			pre = new ArrayList[N];
			post = new ArrayList[N];

			// 초기화
			for (int i = 0; i < N; i++) {
				pre[i] = new ArrayList<>();
				post[i] = new ArrayList<>();
			}
			
			// 양쪽 방향 그래프 생성
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken()) - 1;  // 1->0
				int second = Integer.parseInt(st.nextToken()) - 1;
				
				pre[second].add(first);
				post[first].add(second);
			}

			knowHeight = 0;
			for (int i = 0; i < N; i++) {
				resetVisited();  // visited 초기화
				int numPre = findNumber(i, pre);
				resetVisited();  // visited 초기화
				int numPost = findNumber(i, post);

				if (numPre + numPost == N-1) knowHeight++;
			}
//			sb.append("#").append(test).append(" ").append(knowHeight).append("\n"); -> 출력 형식 변경
			sb.append(knowHeight);
		}
		System.out.println(sb.toString());
	}

	private static void resetVisited() {
		// TODO Auto-generated method stub
		for (int i = 0; i < N; i++) {
			visited[i] = false;
		}
	}

	// DFS: 앞/뒤에 위치한 모든 노드 방문 및 개수 count
	// 이전에 방문한 자식이면 애초에 +1을 해서는 안 되므로, 자식 노드 확인 시 방문 처리 필요 -> 최단 경로 계산이 아니므로 업데이트가 필요하지 않아 상관 X
	private static int findNumber(int idx, List<Integer>[] connected) {
//		if(visited[idx]) return -1;  // 앞에서 처리 시
//		visited[idx] = true;
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < connected[idx].size(); i++) {
			int child = connected[idx].get(i);
			if (visited[child]) continue;  // 이전에 방문했던 자식이면 횟수 추가 X
			visited[child] = true;
			count += findNumber(child, connected) + 1;
		}
		return count;
	}
}
