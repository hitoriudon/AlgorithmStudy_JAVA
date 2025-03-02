import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2458 {
	static ArrayList<Integer>[] smaller;
	static ArrayList<Integer>[] taller;
	static boolean visited[];
	static int N, M;

	// 자신보다 작은 학생을 체크
	static void countSmaller(int curr) {
		if (!smaller[curr].isEmpty()) {
			for (int next : smaller[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					countSmaller(next);
				}
			}
		}
	}

	// 자신보다 큰 학생을 체크
	static void countTaller(int curr) {
		if (!taller[curr].isEmpty()) {
			for (int next : taller[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					countTaller(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 자신보다 큰 학생과 작은 학생을 나눠 저장
		smaller = new ArrayList[N + 1];
		taller = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			smaller[i] = new ArrayList<>();
			taller[i] = new ArrayList<>();
		}

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			smaller[b].add(a);
			taller[a].add(b);
		}

		int res = 0;
		for (int i = 1; i < N + 1; i++) {
			visited = new boolean[N + 1];

			countSmaller(i);
			countTaller(i);

			int cnt = 0;
			for (boolean isVisited : visited) {
				if (isVisited)
					cnt++;
			}
			
			// 자신과 연결된 학생 수가 (전체 - 1)만큼이면 모든 키를 비교할 수 있는 학생
			if (cnt == N - 1)
				res++;
		}
		
		System.out.println(res);
	}
}