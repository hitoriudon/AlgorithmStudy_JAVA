// [골드2] 12015번. 가장 긴 증가하는 부분 수열 2
// 메모리 : 125320 KB, 시간 : 444 ms

import java.io.*;
import java.util.*;

public class P12015 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		int size = 0;
		int[] LIS = new int[N];
		for (int i = 0; i < N; i++) {
			int num = A[i];
			
			int start = 0, end = size;
			while (start < end) {
				int mid = (start + end) / 2;
				if (LIS[mid] < num) start = mid + 1;
				else end = mid;
			}
			
			LIS[start] = num;
			if (start == size) size++;
		}
		
		System.out.println(size);
	}
}