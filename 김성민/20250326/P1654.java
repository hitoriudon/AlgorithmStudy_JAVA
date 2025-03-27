import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1654 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int lanAmount = Integer.parseInt(st.nextToken());
		int wantAmount = Integer.parseInt(st.nextToken());

		int[] lans = new int[lanAmount];
		for (int lanCount = 0; lanCount < lanAmount; lanCount++) {
			lans[lanCount] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(lans);

		long left = 1;
		long right = lans[lanAmount - 1];
		long mid = 1;
		long result = 0;
		while (left <= right) {
			long curAmount = 0;
			mid = (left + right) / 2;

			for (int lanCount = 0; lanCount < lanAmount; lanCount++) {
				curAmount += lans[lanCount] / mid;
			}

			if (curAmount >= wantAmount) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(result);
	}
}
