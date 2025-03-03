import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
	static ArrayList<Integer>[] friends;
	static boolean[] isVisited;
	static boolean isFound = false;
	static int N, M;

	static void search(int depth, int curr) {
		// 연속으로 5명을 방문한 경우 종료
		if (depth == 5) {
			isFound = true;
			return;
		}

		if (!friends[curr].isEmpty()) {
			for (int next : friends[curr]) {
				if (!isVisited[next]) {
					isVisited[next] = true;
					search(depth + 1, next);
					isVisited[next] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isVisited = new boolean[N];
		friends = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<>();
		}

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			friends[a].add(b);
			friends[b].add(a);
		}

		// 한 번에 5명을 연속으로 방문할 수 있으면 성공
		for (int i = 0; i < N; i++) {
			if (isFound)
				break;

			isVisited[i] = true;
			search(1, i);
			isVisited[i] = false;
		}

		System.out.println(isFound ? 1 : 0);
	}
}
