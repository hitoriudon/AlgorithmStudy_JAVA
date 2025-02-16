import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 땅의 최대 높이, 최소 높이, 최소시간 결과, 최대높이 결과
		int minH = 256, maxH = 0, resTime = (int) 1e9, resHeight = (int) -1e9;
		int tempHeight, tempBlock, tempTimeRes, diff;
		boolean tempFlag = true;

		// N, M, B 입력
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		// 땅 정보 입력
		int[][] ground = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				tempHeight = Integer.parseInt(st.nextToken());
				// 최대 높이와 최소 높이 구하기
				maxH = Math.max(maxH, tempHeight);
				minH = Math.min(minH, tempHeight);
				// 땅 정보 배열에 저장
				ground[i][j] = tempHeight;
			}
		}

		// 최소 높이에서 최대 높이 모두 탐색
		for (int targetH = minH; targetH <= maxH; targetH++) {
			// 임시변수 초기화
			tempBlock = B;
			tempTimeRes = 0;
			tempFlag = true;
			
			// 땅을 먼저 걷어내고 채울 수 있기에 먼저 걷어낼 땅 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 제거 시 2초, 인벤토리 블록 수 증가
					if (ground[i][j] > targetH) {
						diff = ground[i][j] - targetH;
						tempTimeRes += 2 * diff;
						tempBlock += diff;
					}
				}
			}

			// 이후 목표 높이보다 낮으면 블록 설치
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (ground[i][j] < targetH) {
						diff = targetH - ground[i][j];
						
						// 블록 없으면 설치 불가
						if (tempBlock < diff) {
							tempFlag = false;
							break;
						}
						// 설치 시 1초, 인벤토리 블록 수 감소
						tempTimeRes += diff;
						tempBlock -= diff;
					}
				}
			}

			// 만약 정상적으로 작업했다면 결과 저장
			if (tempFlag) {
				if(tempTimeRes <= resTime) {
					resTime = tempTimeRes;
					resHeight = targetH;
				}
			}
		}

		System.out.printf("%d %d", resTime, resHeight);
	}
}