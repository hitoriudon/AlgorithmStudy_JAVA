import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N, M, maxVal, res;
		// 큐 안에 [인덱스, 우선순위] 순의 배열 저장
		int[] qTmp;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 큐에 값 삽입
			q.clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				q.addLast(new int[] {i, Integer.parseInt(st.nextToken())});
			}
			
			res = 1;
			while(!q.isEmpty()) {
				// 큐에서 앞의 배열 pop
				qTmp = q.removeFirst();
				
				maxVal = 0;
				// 남은 큐에서 최댓값 검색
				for (int[] curr : q) {
					maxVal = Math.max(maxVal, curr[1]);
				}
				
				// 만약 pop한 값이 최대인 경우
				if (qTmp[1] >= maxVal){
					// 찾는 문서라면
					if (qTmp[0] == M) {
						break;
					}
					res++;
				} else {
					// 최대가 아니라면 다시 큐에 저장
					q.addLast(qTmp);
				}
			}
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
}