package BinarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 랜선 자르기: 랜선의 길이 N을 binary search로 탐색 -> 만들 수 있는 개수가 N 이상이면 최소 증가, N 미만이면 최대 감소
public class P1654 {
	static int K, N;
	static int min, max;
	static int[] lines;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lines = new int[K];
		
		min = 0;
		max = 0;
		for (int i = 0; i < K; i++) {
			int length = Integer.parseInt(br.readLine());
			lines[i] = length;
			max = Math.max(max, length);  // 랜선 중 가장 긴 길이를 최대로 설정
		}
		
		// 실수: middle로 두면 해당 값에서 고정되는 경우 발생
		// 실수: 조건을 만족하는 중앙값을 찾기 어렵다 -> <= 조건에서 max 값 사용
		while(min <= max) {
			long middle = ((long) min + max)/2;  // 실수: N이 int 최대치까지 증가하므로, overflow 발생 가능
			if (middle == 0) break;
			int numLines = 0;
			for (int length: lines) {
				numLines += length/middle;
			}
			
			if (numLines >= N) {  // 조건 충족
				min = (int) middle + 1;  // 최소 증가
			}
			else {
				max = (int) middle - 1;  // 최대 감소
			}
		}
		System.out.println(max);
	
	}
}
