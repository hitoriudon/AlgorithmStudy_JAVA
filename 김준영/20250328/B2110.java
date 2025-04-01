import java.util.*;
import java.io.*;

// 목표 : n개의 집 중 c개의 집에만 공유기 설치 -> 인접 공유기 거리 최대로 하여, 가장 인접한 두 공유기 사이의 거리 출력
// 주의 : 집의 좌표가 10억이어서, 비트마스킹 부분집합으로 하면 터질거같음..

public class B2110 {
	static int n, c;
	static ArrayList<Long> homes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // n개의 집
		c = Integer.parseInt(st.nextToken()); // c개의 공유기

		long left = 1;
		long right = 0;

		for (int i = 0; i < n; i++) {
			long input = Long.parseLong(br.readLine());
			homes.add(input);
			right = Math.max(right, input);
		}
		Collections.sort(homes);
//		System.out.println(homes);
		
		long res = 0;

		while(left <= right) {
			long mid = (left + right) / 2;

			if (check(mid)) { // 해당 길이보다 크게 떨어질 수 있는지..?
				res = mid;
				left = mid + 1; // 더 크게 떨어짐
			} else {
				right = mid - 1;
			}
		}
		System.out.println(res);
	}

	private static boolean check(long gap) {
		int cnt = 1;
		long last = homes.get(0);

		for(int i = 1; i< n; i++) {
			if(homes.get(i) - last >= gap) {
				cnt ++;
				last = homes.get(i);
			}
		}
		return cnt>=c;
	}
}