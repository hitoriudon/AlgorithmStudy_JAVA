import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1005 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			st = new StringTokenizer(br.readLine());
			int buildings = Integer.parseInt(st.nextToken());
			int orders = Integer.parseInt(st.nextToken());

			int[] buildtimes = new int[buildings + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= buildings; i++) {
				buildtimes[i] = Integer.parseInt(st.nextToken());
			}

			ArrayList<Integer>[] todo = new ArrayList[buildings + 1];
			for (int i = 1; i <= buildings; i++) {
				todo[i] = new ArrayList<>();
			}

			int[] inDegree = new int[buildings + 1];
			for (int i = 0; i < orders; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				todo[x].add(y);
				inDegree[y]++;
			}

			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= buildings; i++) {
				if (inDegree[i] == 0)
					q.add(i);
			}
			
			int[] totaltime = new int[buildings + 1];
			for (int i = 1; i <= buildings; i++) {
				totaltime[i] = buildtimes[i];
			}
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for (int next : todo[current]) {
					inDegree[next]--;
					
					totaltime[next] = Math.max(totaltime[next], totaltime[current] + buildtimes[next]);
					
					if (inDegree[next] == 0) {
						q.add(next);
					}
				}
			}
			
			int W = Integer.parseInt(br.readLine());
			sb.append(totaltime[W]).append("\n");
		}
		
		System.out.println(sb);
	}
}
