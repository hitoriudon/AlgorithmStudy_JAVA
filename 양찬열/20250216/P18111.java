// 18111 마인크래프트: 3차원 배열의 평탄화, 높이가 높은 것을 출력
// 높이를 받아서 기준점을 중심으로 큰 쪽과 작은 쪽으로 나누어 최소 시간 계산 -> 전체 시간 = 추가할 수 * 1 + 제거할 수 * 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {
	static int N, M, B;  // 세로, 가로, 초기 블록 수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] height;  // 평탄화는 좌표 필요 X
	static int maxHeight, minHeight, heightResult;
	static int sumLow, sumHigh, sumTotal, minSum;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		height = new int[N*M];
		maxHeight = 0;
		minHeight = Integer.MAX_VALUE;
		minSum = Integer.MAX_VALUE;
		heightResult = 0;
		
		// height 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				height[i*M + j] = value;  // 실수: j가 모두 돌아야 다음으로 이동하는 것이니 i에는 N이 아니라 M을 곱해야 한다!
				if (minHeight > value) minHeight = value;
				if (maxHeight < value) maxHeight = value;
			}
		}
//		System.out.println(Arrays.toString(height));
		
		for (int i = maxHeight; i >= minHeight; i--) {  // 최대 높이부터 탐색
			sumHigh = 0;
			sumLow = 0;

			for (int j = 0; j < N*M; j++) {
				if (height[j] > i) sumHigh += height[j] - i;
				else if (height[j] < i) sumLow += i - height[j];
			}
			sumTotal = sumHigh * 2 + sumLow;
//			System.out.println(i + " "+ sumTotal);
			
			if (minSum > sumTotal && B + sumHigh - sumLow >= 0) {  // 사용 가능한 블록 수 확인
				minSum = sumTotal;
				heightResult = i;
			}
		}
		
		System.out.println(minSum+" "+heightResult);
	}
}
