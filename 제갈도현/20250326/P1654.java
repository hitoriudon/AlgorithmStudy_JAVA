// 메모리 15424kb, 시간 120ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int have = Integer.parseInt(st.nextToken());
		int need = Integer.parseInt(st.nextToken());

		int[] cables = new int[have];
		long minLen = 1, mid, maxLen = 0;
		for (int i = 0; i < have; i++) {
			cables[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, cables[i]);
		}

		long cnt, res = 0;
		while (minLen <= maxLen) {
			mid = (minLen + maxLen) / 2;
			
			cnt = 0;
			for (int i : cables)
				cnt += i / mid;
			
			if (cnt >= need) {
				minLen = mid + 1;
				res = mid;
			}	else
				maxLen = mid - 1;
		}
		
		System.out.println(res);
	}
}
