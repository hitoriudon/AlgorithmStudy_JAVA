import java.util.*;
import java.io.*;

// [1654 랜선 자르기] 
// 랜선의 길이가 2^32-1이므로 이분탐색이 아니면 터짐 -> int 범위 벗어나므로 long으로 해야함
// 목표 : n개의 랜선을 얻을 때, 최대 길이 구함

public class B1654 {
	static int k, n;
	static ArrayList<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		k = Integer.parseInt(st.nextToken()); // k개의 랜선 보유
		n = Integer.parseInt(st.nextToken()); // n개의 랜선 필요

		long left = 1; // divide 0 방지
		long right = 0;

		for (int i = 0; i < k; i++) {
			int input = Integer.parseInt(br.readLine());
			arr.add(input);
			right = Math.max(right, input);
		}

		long res = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			
			if (check(mid)) {
				res = mid;
				left = mid + 1; // 더크게 잘라봄
			} else {
				right = mid - 1;
			}
		}

		System.out.println(res);
	}

	static boolean check(long mid) {
		int cnt = 0;
		for (int a : arr) {
			cnt += a / mid;
		}
		if (cnt >= n)
			return true;
		return false;

	}
}