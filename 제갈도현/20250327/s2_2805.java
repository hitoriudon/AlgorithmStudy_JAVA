package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s2_2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int T = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
				
		// 이분 탐색용 변수 선언
		int left = 0, right = 0, res = 0, cnt = 0, mid;
		long len;
		
		// 나무 정보 저장
		int[] trees = new int[T];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < T; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, trees[i]);
		}
		
		// 이분 탐색
		while (left <= right) {
			mid = (left + right) / 2;
			
			cnt = 0;
			for (int i : trees) {
				len = i - mid;
				if (len > 0) {
					cnt += len;
				}
			}
			
			if (cnt >= L) {
				left = mid + 1;
				res = mid;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(res);
	}
}