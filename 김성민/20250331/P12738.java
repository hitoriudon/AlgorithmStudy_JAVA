import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P12738 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int numAmount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int[] nums = new int[numAmount];
		for (int numCount = 0; numCount < numAmount; numCount++) {
			nums[numCount] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> LIS = new ArrayList<>();
		LIS.add(-1000000001);
		for (int numCount = 0; numCount < numAmount; numCount++) {
			int num = nums[numCount];

			// 마지막 요소보다 크면 동록
			if (LIS.get(LIS.size() - 1) < num) {
				LIS.add(num);
			}
			// 작으면 바이너리로 찾아서 교체
			else {
				int min = 0;
				int max = LIS.size() - 1;
				int mid = 0;
				while (min <= max) {
					mid = (min + max) / 2;
					if (LIS.get(mid) >= num) {
						max = mid - 1;
					} else {
						min = mid + 1;
					}
				}
				LIS.set(min, num);
			}
		}
		
		// 들어간 수열의 사이즈에서 0 빼고 출력
		System.out.println(LIS.size() - 1);
	}
}