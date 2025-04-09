// [실버2] 2805번. 나무 자르기
// 메모리 : 167844 KB, 시간 : 448 ms

import java.io.*;
import java.util.*;

public class P2805 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 나무의 수
		int M = Integer.parseInt(st.nextToken());	// 필요한 나무의 길이
		
		int start = 0, mid = 0, end = 0, result = 0;
		
		int[] heights = new int[N];	// 나무의 높이
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, heights[i]);
		}
		
		while (start <= end) {
			mid = (start + end) / 2;
			long sum = 0;
			
			for (int height : heights) {
				if (height > mid) sum += (height - mid);
			}
			
			if (sum < M) end = mid - 1;
			else start = mid + 1;
		}
		System.out.println(end);
	}
}