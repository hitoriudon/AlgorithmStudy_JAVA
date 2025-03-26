package algorithm;
// 메모리: 11508 KB, 시간: 64ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2529 {
	static boolean arr[];
	static boolean visited[];
	static int k;
	static StringBuilder sb = new StringBuilder();
	static boolean pick;

	static void maxPick(int pre, int cnt, StringBuilder sbParm) {
		if (cnt > k ) {
			sb.append(sbParm);
			sb.append("\n");
			pick = true;
			return;
		}

		int s = 0;
		int e = 9;

		if (arr[cnt]) {
			s = pre + 1;
		} else {
			e = pre - 1;
		}

		for (int i = e; i >= s; i--) {
			if (!visited[i]) {
				visited[i] = true;
				maxPick(i, cnt + 1, new StringBuilder(sbParm).append(i));
				if (pick)
					return;
				visited[i] = false;
			}
		}
	}

	static void minPick(int pre, int cnt, StringBuilder sbParm) {

		if (cnt > k ) {
			sb.append(sbParm);
			pick = true;
			return;
		}

		int s = 0;
		int e = 9;

		if (arr[cnt]) {
			s = pre + 1;
		} else {
			e = pre - 1;
		}

		for (int i = s; i <= e; i++) {
			if (!visited[i]) {
				visited[i] = true;
				minPick(i, cnt + 1, new StringBuilder(sbParm).append(i));
				if (pick)
					return;
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		arr = new boolean[k + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= k; i++) {
			if (st.nextToken().equals("<")) {
				arr[i] = true;
			}
		}

		visited = new boolean[10];
		for (int i = 9; i >= 0; i--) {
			visited[i] = true;
			maxPick(i, 1, new StringBuilder().append(i));
			if (pick)
				break;
			visited[i] = false;

		}

		pick = false;

		visited = new boolean[10];
		for (int i = 0; i <= 9; i++) {
			visited[i] = true;
			minPick(i, 1, new StringBuilder().append(i));
			if (pick)
				break;
			visited[i] = false;

		}

		System.out.println(sb);

	}

}
