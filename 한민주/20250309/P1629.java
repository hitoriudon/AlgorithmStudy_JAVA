package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1629 {
	static int c, b;

	static long multi(int a, int cnt) {
		if (cnt == 1)
			return a;
		long halfpow = multi(a, cnt / 2) % c;
		if (cnt % 2 == 1) {
			return (a * (halfpow * halfpow % c) % c);
		}
		return halfpow * halfpow % c;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		a %= c;
		System.out.println(multi(a, b));
	}

}
