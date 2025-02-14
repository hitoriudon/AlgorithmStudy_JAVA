import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P14889 {
	// [ [teamA 사원들], [teamB 사원들] ] 순으로 저장하는 큐
	static ArrayDeque<int[][]> q = new ArrayDeque<>();
	// 사원 전체
	static int[] arr;
	// 조합용 체커
	static boolean[] check;
	
	// 조합 구하기
	static void combi(int start, int n, int r) {
		// 만약 모든 경우에 도달한 경우
		if (r == 0) {
			//항상 N은 짝수라 했으므로, 스타트팀 링크팀 멤버를 N/2로 고정
			int[] A = new int[n/2];
			int[] B = new int[n/2];
			int indexA = 0, indexB = 0;
			
			// 팀 분류
			for (int i = 0; i < n; i++) {
				if (check[i])
					A[indexA++] = arr[i];
				else
					B[indexB++] = arr[i];
			}
			
			// 큐에 저장
			q.addLast(new int[][] {A, B});
			return;
		}
		
		for (int i = start; i < n; i++) {
			check[i] = true;
			combi(i+1, n, r-1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// 배열 할당
		arr = new int[N];
		for(int i = 0; i < N; i++) arr[i] = i;
		check = new boolean[N];
		
		// 조합 구하기
		combi(0, N, N/2);
		
		// 능력치 입력
		int[][] stats = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 두 팀의 능력치 차이
		int minGap = (int) 1e9;
		
		// 큐를 돌며 능력치 비교
		int teamStart, teamLink;
		for(int[][] t : q) {
			// 팀 간 능력치 합 초기화
			teamStart = 0; teamLink = 0;
			// 배열을 돌며 능력치 합 추가
			for (int i = 0; i < N/2-1; i++) {
				for (int j = i+1; j < N/2; j++ ) {
					// 모든 경우의 Sij + Sji 값을 더해줌
					// 팀 Start
					teamStart += stats[t[0][i]][t[0][j]];
					teamStart += stats[t[0][j]][t[0][i]];
					// 팀 Link
					teamLink += stats[t[1][i]][t[1][j]];
					teamLink += stats[t[1][j]][t[1][i]];
				}
			}
			
			// 최솟값 비교 후 저장
			minGap = Math.min(minGap, Math.abs(teamStart-teamLink));
		}
		
		System.out.println(minGap);
	}
}