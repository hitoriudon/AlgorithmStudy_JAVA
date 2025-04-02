package test.day05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// P2110 공유기 설치: 인접 거리에 대한 이진탐색(각 공유기 간격 중 최소 거리)
public class P2110 {
	static int N, C;
	static int[] houses, gases;
	static int maxDistance;

	// 방역: 주어진 좌표값에 방역 가스를 설치해 인접 거리의 최대 거리 구하기 -> 범위를 보니 완탐은 시간 초과 -> 다른 방법?
	// 이진 탐색을 사용해야 한다 -> 특정 거리 이상이면 선택하는 식으로 개수 계산, 거리에 대해 이분탐색 시도(설치를 더 많이 하면 최소 증가, 설치를 더 적게 하면 최대 감소, 중간값과 비교하며 이분 탐색)
	public static void main(String[] args) throws Exception {
		//--------------솔루션 코드를 작성하세요.--------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];
		gases = new int[C];

		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houses);  // 최소 좌표부터 배열
		
		int start = 0, end = houses[houses.length - 1]/(C - 1) ;  // 최소 인접 거리의 최댓값: 최대 거리/C -> 실수!: 시작점을 포함하므로, C - 1로 나누어야 한다.
		while (start <= end) {
			int middle = (start + end)/2;
			
			int num = getCount(middle);
			
			if (num >= C) {
				start = middle + 1;
			} else {
				end = middle - 1;
			}
		}
		System.out.println(end);
		
//		maxDistance = 0;
//		checkGas(0, 0, 0, Integer.MAX_VALUE);  // idx, 가스 설치 개수, 이전 선택지, 현재 최솟값
//		System.out.println(maxDistance);
	}

	private static int getCount(int middle) {
		int num = 1;
		int prePos = houses[0];
		for (int i = 1; i < N; i++) {
			if (houses[i] - prePos >= middle) {
				prePos = houses[i];
				num++;
			}
		}
		return num;
	}

//	private static void checkGas(int idx, int numGas, int pastPos, int min) {
//		if (min < maxDistance) {  // 가지치기
//			return;
//		}
//		
//		if (numGas == C) {
//			maxDistance = min;
//			return;
//		}
//		
//		for (int i = idx; i < N; i++) {
//			gases[numGas] = houses[i];
//			// 실수: 처음에는 이전 값이 없음에도 계산을 진행해 오류 발생
//			checkGas(i + 1, numGas + 1, houses[i], numGas == 0?min:Math.min(min, houses[i] - pastPos));  // 가스 추가
//			checkGas(i + 1, numGas, pastPos, min);  // 가스 추가 X
//		}
//	}
}