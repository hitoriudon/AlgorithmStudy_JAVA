package algorithm;

// 메모리: 15336kb, 시간: 124ms
// 자기반성: mid에서 int 최대 범위에 +연산 할 수도 있는데 왜 괜찮다고 생각했을까...

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1654 {
	static int k, n;
	static int[] len;

	static boolean cut(long some) {
		int sum = 0;
		for(int i = 0; i < k; i++) {
			sum += len[i] / some;
			if(sum >= n) break;
		}
		return sum >= n;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		len = new int[k];
		long maxLen = 0;
		long minLen = 1;

		for (int i = 0; i < k; i++) {
			len[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, len[i]);
		}

		long answer = 0;
		while (maxLen >= minLen) {
			
			long mid = (maxLen + minLen) / 2;
			if(cut(mid)) {
				if(mid <= answer) break;
				answer = mid;
				minLen = mid+1;
			}else {
				maxLen = mid-1;
			}
		}

		System.out.println(answer);
	}
}
