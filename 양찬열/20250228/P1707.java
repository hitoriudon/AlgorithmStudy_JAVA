package study.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 이분 그래프: 문제 설명만으로는 이해가 어려워 이분 그래프에 대한 개념을 확인했다.... -> 연결된 정점을 순회하며 색을 번갈아 칠했을 때 색이 겹쳐지지 않는 경우
public class P1707 {
	static int T, V, E;
	static List<Integer>[] graph;
	static int[] isVisited;  // 1, -1로 flag 확인
	static boolean isBipartite;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			isVisited = new int[V + 1];
			graph = new List[V + 1];  // 1번부터 사용
			for (int idx = 1; idx <= V; idx++) {
				graph[idx] = new ArrayList<>();  // 초기화
			}
			
			// 인접 리스트 생성
			int first, second;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				first = Integer.parseInt(st.nextToken());
				second = Integer.parseInt(st.nextToken());
				
				graph[first].add(second);
				graph[second].add(first);
			}
			
			// 이분 그래프 판별: dfs를 사용하여 색을 칠하고, 방문한 곳을 재방문했을 때 다른 색을 칠해야 하면 no, 아니면 yes
			// 실수: 노드가 전부 연결되지 않을 수 있다 -> visited 확인 후 재시도 필요!
			isBipartite = true;
			for (int v = 1; v <= V; v++) {
				if (isVisited[v] == 0) {  // 방문 X
					isVisited[v] = 1;
					checkedBipartite(v, 1);
					if (!isBipartite) {
						sb.append("NO").append("\n");
						break;
					}
				}
			}
			if(isBipartite) {
				sb.append("YES").append("\n");
			}

			// 실수 1: << 연산보다 - 연산이 우위, 실수 2: 0의 자리는 사용하지 않으므로 비워야 한다.
//			isBipartite = (isVisited != ((1<<(V + 1)) - 2));  // 전체 방문 처리되지 않았으면 true
		}
		System.out.println(sb.toString());
	}

	private static void checkedBipartite(int v, int flag) {
		// TODO Auto-generated method stub
		flag = -flag;  // 실수: 현재 flag 상태를 받았으니, child의 flag는 반전 필요
		for (int child : graph[v]) {
			if (isVisited[child] == 0) {  // 방문 X
				isVisited[child] = flag;
				checkedBipartite(child, flag);
			}
			else if (isVisited[child] != flag) {  // 방문 & 같은 색 아님
				isBipartite = false;
			}
			if (!isBipartite) {  // 실수: false일 때 나가야 하는데, 습관적으로 !를 안 붙임..
				return;
			}
		}
		return;
	}
}
