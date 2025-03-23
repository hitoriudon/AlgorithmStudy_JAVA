import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P2458 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] graph = new ArrayList[N];
		ArrayList<Integer>[] graphR = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			graphR[i] = new ArrayList<>();
		}

		int answer = 0;

		int sV, eV;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sV = Integer.parseInt(st.nextToken())-1;
			eV = Integer.parseInt(st.nextToken())-1;
			graph[sV].add(eV);
			graphR[eV].add(sV);
		}

		for (int i = 0; i < N; i++) {
			HashSet<Integer> visited = new HashSet<>(); //나보다 크고 작은게 확실한 녀석들
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			visited.add(i);
			stack.addLast(i);
			while(!stack.isEmpty()) {
				int val = stack.pollLast();
				for (int j = 0; j < graph[val].size(); j++) {
					if(!visited.contains(graph[val].get(j))) {
						visited.add(graph[val].get(j));
						stack.add(graph[val].get(j));
					}
				}
			}
			stack.addLast(i);
			while(!stack.isEmpty()) {
				int val = stack.pollLast();
				for (int j = 0; j < graphR[val].size(); j++) {
					if(!visited.contains(graphR[val].get(j))) {
						visited.add(graphR[val].get(j));
						stack.add(graphR[val].get(j));
					}
				}
			}
			if(visited.size() >= N) {
				answer++;
			}

		}
		System.out.println(answer);
	}

}
