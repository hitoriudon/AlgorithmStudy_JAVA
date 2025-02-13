import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1051 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String tmpStr;
		int maxSize, res = 0;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] origSquare = new int[N][M];
		
		// 기본 직사각형 입력
		for (int i = 0; i < N; i++) {
			tmpStr = br.readLine();
			for (int j = 0; j < M; j++)
				origSquare[i][j] = tmpStr.charAt(j) - '0'; 
		}
		
		// 직사각형 순회
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 특정 좌표에서 그릴 수 있는 최대의 정사각형 크기 계산
				maxSize = Math.min(N-i, M-j);
				// 꼭짓점에 쓰여 있는 수가 모두 같은 경우 체크
				for (int k = 0; k < maxSize; k++) {
					if (origSquare[i][j] == origSquare[i+k][j] &&
							origSquare[i][j] == origSquare[i][j+k] &&
							origSquare[i][j] == origSquare[i+k][j+k])
						// 결과 저장
						res = Math.max(res, (k+1)*(k+1));
				}
			}
		}
		
		System.out.println(res);
	}
}