package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1463 {
	static int arr[];
	static int answer = Integer.MAX_VALUE;

	static void calc(int num, int cnt) {
		if (num == 1) {
			answer = Math.min(answer, cnt);
			return;
		}

		if (answer <= cnt)
			return;

		if (arr[num] == 0 || arr[num] > cnt) {
			arr[num] = cnt;
			calc(num / 3, cnt + num % 3 + 1);
			calc(num / 2, cnt + num % 2 + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n+1];

		if (n == 1) {
			System.out.println(0);
			return;
		} else if (n == 2 || n == 3) {
			System.out.println(1);
			return;
		}

		calc(n, 0);

		System.out.println(answer);

	}

}
