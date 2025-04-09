// [골드1] 1300번. K번째 수
// 메모리 : 11600 KB, 시간 : 104 ms

import java.io.*;

public class P1300 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		long k = Integer.parseInt(br.readLine());
		
		long answer = 0, start = 1, end = k;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			
			long cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}
			
			if (cnt >= k) {
				answer = mid;
				end = mid - 1;
			}
			
			else start = mid + 1;
		}
		System.out.println(answer);
	}
}