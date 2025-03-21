package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부등호: 부등호 모양이 주어질 때, 조건을 만족하는 정수 집합 계산 -> 자릿수를 고려하므로, 앞쪽부터 차례로 처리
// 순열 완탐
public class P2529 {
	static int N;
	static char[] nonEquals;
	static boolean[] isVisited;
	static int[] result;
	static String maxResult, minResult;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nonEquals = new char[N];
		isVisited = new boolean[10];
//		Arrays.fill(minResult, Integer.MAX_VALUE);  // 초기화
		result = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nonEquals[i] = st.nextToken().charAt(0);
		}
		
		tryMin(0);
		tryMax(0);
		
		System.out.println(maxResult);
		System.out.println(minResult);
	}

	// 어차피 앞부터 최솟 값으로 채우는 특성 상 처음과 마지막이 최소 & 최대 -> 아예 1번만 계산
	private static void tryMin(int idx) {
		if (idx == N + 1) {
			StringBuilder sb = new StringBuilder();
			for (int i: result) {
				sb.append(i);
//				value += result[i]*Math.pow(10, N - i);  // 실수: java에서 제곱은 ^이 아니다...
			}
			
			minResult = sb.toString();
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			// 방문 조건
			if (isVisited[i]) {  // 이미 선택 시 pass
				continue;
			}
			
			// 크기 비교 조건
			if (idx != 0) {  // 시작 X
				if (nonEquals[idx - 1] == '<' && result[idx - 1] > i) {  // 조건 만족 X
					continue;
				}
				else if (nonEquals[idx - 1] == '>' && result[idx - 1] < i) {  // 조건 만족 X
					continue;
				}
			}
			
			// 최소 & 최대 여부 조건: 실수=!isBigger && !isSmaller인 경우, 한쪽이 업데이트되면 다른 쪽은 업데이트 기회 박탈
			//실수: 애초에 최소 & 최대를 해당 파트에서 비교하면 true 설정 이후의 분기가 변화할 때 대응하지 못한다는 문제 발생
//			boolean flagBig, flagSmall;
//			flagBig = flagSmall = false;
//
//			if (!isBigger) {
//				if (i > maxResult[idx]) {
//					isBigger = true;
//				}
//				else if (i < maxResult[idx]) {  // 최대보다 작은 경우
//					flagBig = true;
//				}
//			}
//			
//			if (!isSmaller) {
//				if (i < minResult[idx]) {
//					isSmaller = true;
//				}
//				else if (i > minResult[idx]) {  // 최소보다 큰 경우
//					flagSmall = true;
//				}
//				
//				// 어차피 isSmaller가 true인 경우, flagSamll = false라 실패
//				if (flagBig && flagSmall) {
//					continue;  // 새로운 최대도, 최소도 되지 못하는 경우
//				}
//			}

			isVisited[i] = true;
			result[idx] = i;
			tryMin(idx + 1);
			isVisited[i] = false;  // 초기화
			if (minResult != null) {
				break;
			}
		}
	}
	
	private static void tryMax(int idx) {
		if (idx == N + 1) {
			StringBuilder sb = new StringBuilder();
			for (int i: result) {
				sb.append(i);
			}
			maxResult = sb.toString();
			return;
		}
		
		for (int i = 9; i >= 0; i--) {  // 범위 역으로
			// 방문 조건
			if (isVisited[i]) {  // 이미 선택 시 pass
				continue;
			}
			
			// 크기 비교 조건
			if (idx != 0) {  // 시작 X
				if (nonEquals[idx - 1] == '<' && result[idx - 1] > i) {  // 조건 만족 X
					continue;
				}
				else if (nonEquals[idx - 1] == '>' && result[idx - 1] < i) {  // 조건 만족 X
					continue;
				}
			}
			
			isVisited[i] = true;
			result[idx] = i;
			tryMax(idx + 1);
			isVisited[i] = false;  // 초기화
			if (maxResult != null) {
				break;
			}
		}
	}
}
