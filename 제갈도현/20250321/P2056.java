import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        // 다음 작업을 저장할 배열
        ArrayList<Integer>[] todo = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            todo[i] = new ArrayList<>();
        }

        int[] worktime = new int[N+1];
        int[] inDegree = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            // i번 작업에 소요되는 시간
            worktime[i] = Integer.parseInt(st.nextToken());

            // 진입차수
            int id = Integer.parseInt(st.nextToken());
            inDegree[i] = id;
            
            for (int j = 0; j < id; j++ ) {
                int w = Integer.parseInt(st.nextToken());
                todo[w].add(i);
            }
        }
        
        // 위상정렬에 사용되는 큐
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
        	// 진입차수가 0이면 큐에 저장
        	if (inDegree[i] == 0) {
        		q.add(i);
        	}
        }
        
        // 전체 소요시간 배열을 각 작업의 소요시간으로 초기화
        int[] totaltime = new int[N+1];
        for (int i = 1; i <= N; i++) {
            totaltime[i] = worktime[i];
        }

        
        while(!q.isEmpty()) {
        	// 큐에서 작업을 가져옴
        	int current = q.poll();
        	
        	for (int next : todo[current]) {
        		// 다음 작업의 진입차수를 감소 (연결 해제)
        		inDegree[next]--;
        		
        		// 작업 시간 기록
        		totaltime[next] = Math.max(totaltime[next], totaltime[current] + worktime[next]);
        		
        		// 다음 작업 진입차수가 0이면 큐에 저장
        		if (inDegree[next] == 0) {
        			q.add(next);
        		}
        	}
        }
        
        int res = 0;
        for (int i = 1; i <= N; i++) {
        	res = Math.max(res, totaltime[i]);
        }
        
        System.out.println(res);
    }
}