// [골드4] 2110번. 공유기 설치
// 메모리 : 28396 KB, 시간 : 244 ms

import java.io.*;
import java.util.*;

public class P2110 {
	static int N, C;
	static int[] houses;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 집의 개수
		C = Integer.parseInt(st.nextToken());	// 공유기의 개수
		
		houses = new int[N];
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houses);
		
		int start = 1, end = houses[N-1] - houses[0];
		while (start <= end) {
			int mid = (start + end) / 2;	// 공유기 설치 간격
			
			// 설치 불가능 -> 간격 더 줄여보기
			if (!install(mid, new ArrayList<>())) end = mid - 1;
			
			// 설치 가능 -> 간격 더 늘려보기
			else start = mid + 1;
		}
		System.out.println(end);
	}
	
	static boolean install(int mid, List<Integer> current) {
		int cnt = 1;	// 현재까지 설치된 공유기 개수
		int last = houses[0];	// 가장 최근 설치한 집
		
		for (int i = 1; i < N; i++) {
			if (cnt >= C) return true;
			
			if (houses[i] - last < mid) continue;
			last = houses[i];
			cnt++;
		}
		
		if (cnt < C) return false;
		return true;
	}
}