import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2805 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int treeAmount = Integer.parseInt(st.nextToken());
		long targetSum = Integer.parseInt(st.nextToken());

		long[] trees = new long[treeAmount];
		long max = 0;

		st = new StringTokenizer(br.readLine());
		for (int treeCount = 0; treeCount < treeAmount; treeCount++) {
			long height = Long.parseLong(st.nextToken());
			trees[treeCount] = height;
			
			// 가장 큰 나무
			max = Math.max(max, height);
		}
		
		// 1인 나무를 위한 +1
		max++;
		long min = 0;
		long mid = 0;
		long result = 0;

		while (min <= max) {
			long heightSum = 0;
			mid = (max + min) / 2;

			for (long height : trees) {
				// 자르는 높이보다 큰 경우만
				if (height - mid > 0) {
					heightSum += height - mid;
				}
			}
			
			// 합이 이상이면 저장하고 작은거는 하나 더 위로
			if (heightSum >= targetSum) {
				min = mid + 1;
				result = mid;
			} else {
				max = mid - 1;
			}
		}
		System.out.print(result);
	}
}
