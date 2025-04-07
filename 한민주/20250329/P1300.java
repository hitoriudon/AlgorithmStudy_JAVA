package algorithm;

// 메모리: 11712 KB, 시간: 112 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1300 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		long k = Long.parseLong(br.readLine());

		long e = n * n;
		long s = 1;
		while (s <= e) {
			long mid = (s + e) / 2;
//			System.out.println(mid);
			long small = 0;
			for (int i = 1; i <= n; i++) {
				small += Math.min(mid / i, n);
			}
			if (small >= k) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		System.out.println(s);
	}

}
