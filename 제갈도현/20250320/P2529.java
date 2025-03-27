import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2529 {
	static int k;
	static long minVal = Long.MAX_VALUE, maxVal = Long.MIN_VALUE;
	static String minRes, maxRes;
	static char[] sign;
	
	static void checkVal(String num) {
		long val = Long.parseLong(num);
		
		if (val < minVal) {
			minVal = val;
			minRes = num;
		}
		if (val > maxVal) {
			maxVal = val;
			maxRes = num;
		}
	}

	static void dfs(int before, int depth, int flag, String num) {
		if (depth > k) {
			checkVal(num);
			return;
		}

		for (int i = 0; i < 10; i++) {
			// 체크용 플래그
			if ((flag & 1 << i) != 0)
				continue;

			if (sign[depth - 1] == '>' && before > i)
				dfs(i, depth + 1, flag | 1 << i, num + i);
			else if (sign[depth - 1] == '<' && before < i)
				dfs(i, depth + 1, flag | 1 << i, num + i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());

		// 부등호 저장
		sign = new char[k];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			sign[i] = st.nextToken().charAt(0);
		}

		for (int i = 0; i < 10; i++) {
			dfs(i, 1, 0 | 1 << i, Integer.toString(i));
		}
		
		System.out.println(maxRes);
		System.out.println(minRes);
	}
}
