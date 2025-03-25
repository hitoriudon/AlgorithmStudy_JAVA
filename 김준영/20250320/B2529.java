import java.io.*;
import java.util.*;

// [2529 부등호]
// 시간 : 144 / 메모리 : 18100

public class B2529 {

	static int k, n;
	static char[] bdh;
	static int[] num;
	static long min = Long.MAX_VALUE;
	static long max = Long.MIN_VALUE;
	static boolean[] visit = new boolean[10];
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		k = Integer.parseInt(br.readLine()); // k개의 부등호
		n = k + 1; // n개의 숫자
		bdh = new char[k];
		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			bdh[i] = st.nextToken().charAt(0);
		}
//		System.out.println(Arrays.toString(bdh));

		dfs(0); // 0개의 숫자가 결정된 경우

		System.out.printf("%0" + n + "d", max);
		System.out.println();
		System.out.printf("%0" + n + "d", min);

	}

	static void dfs(int depth) {
		if (depth == n) { // n개의 숫자 뽑은 경우
			sb = new StringBuilder();
			for (int n : num) {
				sb.append(n);
			}
			long res = Long.parseLong(sb.toString());

			min = Math.min(min, res);
			max = Math.max(max, res);
			

			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (visit[i] == false) {
				visit[i] = true;
				num[depth] = i;
				if(can(depth)) {
					dfs(depth + 1);
				}
				visit[i] = false;
			}
		}
	}
	static boolean can(int depth) { // num[depth-1], num[depth]가 bdh[depth-1]을 만족하는 경우
		if(depth == 0) {
			return true;
		}
		if(bdh[depth - 1] == '>') {
			if(num[depth-1] <= num[depth]) return false;
		}else {
			if(num[depth-1] >= num[depth]) return false;
		}
		
		return true;
	}

}
