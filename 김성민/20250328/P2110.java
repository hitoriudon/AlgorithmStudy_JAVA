import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int houseAmount = Integer.parseInt(st.nextToken());
		int wifiAmount = Integer.parseInt(st.nextToken());

		int[] houses = new int[houseAmount];

		for (int houseCount = 0; houseCount < houseAmount; houseCount++) {
			houses[houseCount] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(houses);

		int min = 1;
		int max = houses[houseAmount - 1] - houses[0] + 1;
		int mid = 0;
		int result = 0;
		
		while (min <= max) {
			mid = (min + max) / 2;

			int last = 0; // 마지막 설치 위치 (처음은 시작부터)
			int wifiCount = 1; // 설치한 갯수
			for (int houseCount = 1; houseCount < houseAmount; houseCount++) {
				// 마지막 설치 와 다음 집 거리가 설정 거리보다 클 때
				if (houses[houseCount] - houses[last] >= mid) {
					last = houseCount;
					wifiCount++;
				}
			}

			if (wifiCount >= wifiAmount) { 
				min = mid + 1;
				result = mid;
			} else {
				max = mid - 1;
			}
		}

		System.out.println(result);
	}
}