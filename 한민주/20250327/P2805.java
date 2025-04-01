package algorithm;

// 메모리: 171764kb, 시간: 452ms
// 자기반성: 또 범위 체크 안했다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2805 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 최소: m미터 최대: 최고 나무높이 -1
		long maxH = 0;
		long minH = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long trees[] = new long[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(trees[i], maxH);
			minH = Math.min(trees[i], minH);
		}
		
	
		long answer = 0;
		while(minH <= maxH) {
			long mid = (minH + maxH)/2;
			long sum = 0;
			for(int i = 0; i < n; i++) {
				if(sum >= m) {
					break;
				}
				sum += Math.max(trees[i]-mid, 0);
			}
			if(sum >= m) {
				answer = mid;
				minH = mid+1;
			}else {
				maxH = mid-1;
			}
		}
		
		System.out.println(answer);
	}

}
