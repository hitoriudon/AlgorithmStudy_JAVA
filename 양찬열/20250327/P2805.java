package BinarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 나무 자르기: 높이 H를 binary search로 탐색 -> 나무 길이가 M 이상이면 최소 증가, M 미만이면 최대 감소
public class P2805 {
	static int N, M;
	static int min, max;
	static int[] trees;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		
		min = 0;
		max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			trees[i] = height;
			max = Math.max(max, height);  // 나무 중 가장 높은 높이를 최대로 설정
		}
		
		while(min <= max) {
			int middle = (min + max)/2;
			long totalHieght = 0;  // 실수: 최악의 경우, 합은 N*h=10^6*10^9=10^15 -> 2^31=약 10^9 -> int 범위 넘어섬
			for (int height: trees) {
				totalHieght += height > middle?height - middle:0;  // 자른 나무 길이
			}
			
			if (totalHieght >= M) {  // 조건 충족
				min = middle + 1;  // 최소 증가
			}
			else {
				max = middle - 1;  // 최대 감소
			}
		}
		System.out.println(max);	
	}
}
