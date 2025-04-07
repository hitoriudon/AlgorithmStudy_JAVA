package algorithm;

// 메모리: 11624 KB, 시간: 68 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1043 {
	static int union[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		// 진실을 아는 사람 수
		int k = Integer.parseInt(st.nextToken());
		union = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			union[i] = i;
		}

		for (int i = 0; i < k; i++) {
			union[Integer.parseInt(st.nextToken())] = -1;
		}

		ArrayList<ArrayList<Integer>> party = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			// 파티에 참석한 사람 수
			int l = Integer.parseInt(st.nextToken());
			int root = Integer.parseInt(st.nextToken());
			ArrayList<Integer> list = new ArrayList<>();
			list.add(root);
			find(root);

			for (int j = 1; j < l; j++) {
				int come = Integer.parseInt(st.nextToken());
				if (find(come) == -1) {
					if (union[root] != -1)
						union[union[root]] = -1;
				} else if (union[root] == -1) {
					union[union[come]] = -1;
				} else {
					union[union[come]] = union[root];
				}
				list.add(come);
			}
			party.add(list);

		}

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			boolean no = false;
			for (int j = 0; j < party.get(i).size(); j++) {
				int now = party.get(i).get(j);
				if (find(now) == -1) {
					no = true;
					break;
				}
			}
			if (!no) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	private static int find(int x) {
		if (union[x] == -1)
			return -1;
		else if (union[x] == x)
			return x;
		else
			return union[x] = find(union[x]);
	}
}