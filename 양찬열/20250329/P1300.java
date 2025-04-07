package BinarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// N*N인 배열 A, A[i][j]의 값은 i*j, 일차원 배열에 넣고 오름차순 정렬 시 B[k](1부터 시작)
// N은 최대 10^10 -> int 범위: 2^32 >= 2*10^9이므로 범위 X(실수: int 범위를 2*10^10으로 오해)
// 정렬 시 최소 N log N이므로, 시간 복잡도 초과(10^9 연산이 대략 1초)
// 모르겠어서 AI 사용...: 1, N*N을 범위로 잡은 후 절반의 값을 계산, 해당 값 이하의 원소 개수를 계산하여 K가 되는 지점 탐색
// -> count는 i*j를 지나야 변화하므로 자동으로 K-1에서 K로 변할 때의 값(종료 지점)은 i*j의 조건을 만족한다!
public class P1300 {
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		// 실수: int 범위 초과: N*N의 경우, int끼리의 곱이라 자동으로 int 처리되므로 (long) 타입 변환 필수!
		long start = 1, end = (long) N*N;
		while (start <= end) {
			long middle = (start + end)/2;
			
			long small = 0;
			for (int i = 1; i <= N; i++) {
				small += Math.min(N, middle/i);  // i*j가 middle 이하 -> j <= middle/i, middle/i가 N 이상인 경우(i가 매우 작다), 모두 middle 이하
			}
			
			// 실수: start
			if (small >= K) {
				end = middle - 1;
			}
			else start = middle + 1;
		}
		System.out.println(start);  // 실수: 일반적으로 end를 출력하지만, 여기서는 조건 충족 시 최소가 증가(조건을 만족하는 최댓값)하는 대신 감소하는 방향(조건을 만족하는 최솟값)
	}
}
	