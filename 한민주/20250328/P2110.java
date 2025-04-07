package algorithm;

// 메모리: 39964 KB, 시간: 228 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {
	static int n, c;
	static int map[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[n];

		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(map);

		int s = 1;
		int e = (map[n - 1] - map[0] + 1);
		int ans = 0;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (install(mid, 0, 1)) {
				s = mid + 1;
				if (ans < mid) {
					ans = mid;
				} else {
					break;
				}
			} else {
				e = mid - 1;
			}
		}
		System.out.println(ans);
	}

	static boolean install(int dis, int prev, int cnt) {
		if (cnt == c) {
			return true;
		}
		boolean result = false;
		for (int i = prev + 1; i < n; i++) {
			if(n-i < c-cnt) {
				return false;
			}
			if (map[i] - map[prev] >= dis) {
				result = install(dis, i, cnt + 1);
				break;
			}
		}
		return result;
	}
}
