import java.util.*;
import java.io.*;

// 목표 : m미터의 나무를 가져가기 위한 최대 높이
// 주의 : m이 20억을 넘어가므로, 이분탐색 필요

public class B2805 {
	static long n, m;
	static ArrayList<Long> trees = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // n개의 나무
		m = Integer.parseInt(st.nextToken()); // m만큼의 나무 필요

		long left = 1; // divide 0 방지
		long right = 0;

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			long input = Integer.parseInt(st.nextToken());
			trees.add(input);
			right = Math.max(right, input);
		}

		long res = 0;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			System.out.println(left + " " + right + " " + mid);
			
			if (check(mid)) {
				res = mid;
				left = mid + 1; // 더크게 잘라봄
			} else {
				right = mid - 1;
			}
		}

		System.out.println(res);
	}

	static boolean check(long height) {
		long remain = 0;
		for (long tree : trees) {
			remain += Math.max(tree - height, 0);
		}
		if (remain >= m) // m 이상의 나무 가져가야함
			return true;
		return false;

	}
}