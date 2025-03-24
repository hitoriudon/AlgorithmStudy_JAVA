package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P11444 {
	static final long MOD_NUM = 1000000007;
	static long n;

	static long[] fibonacci(long n) {
		if (n == 0)
			return new long[] { 1, 0 };
		else if (n == 1)
			return new long[] { 0, 1 };
		else if (n == 2)
			return new long[] { 1, 1 };
		else if (n == 3)
			return new long[] { 1, 2 };
		else if (n == 4)
			return new long[] { 2, 3 };
		long[] fibo = fibonacci(n / 2 + 1);
		long[][] temp = new long[][] { { (fibo[1]  - fibo[0] + MOD_NUM) % MOD_NUM, fibo[0] },
				{ fibo[0] % MOD_NUM, fibo[1] % MOD_NUM } };
		long returnM[][] = realMulti(temp, temp);

		if (n % 2 == 0) {
			return new long[] { returnM[0][0], returnM[0][1] };
		}
		return new long[] { returnM[1][0], returnM[1][1] };
	}

	static long[][] realMulti(long[][] m1, long[][] m2) {
		long[][] temp = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp[i][j] += ((m1[i][k] * (m2[k][j] % MOD_NUM)) % MOD_NUM);
					temp[i][j] %= MOD_NUM;
				}
			}
		}
		return temp;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Long.parseLong(br.readLine());
		long answer[] = fibonacci(n);
		System.out.println(answer[1]);
	}
}
