import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P7576 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M;
		boolean[][] visited;
		LinkedList<int[]> queue = new LinkedList();
		int[] directX = {0,1,0,-1};
		int[] directY = {-1,0,1,0};
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		String word;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				word = st.nextToken();
				if(word.equals("1")) {
					queue.addLast(new int[] {i,j});
					visited[i][j] = true;
				}
				if(word.equals("-1")) 
					visited[i][j] = true;
				
			}
		}
		
		int answer = -1;
		int x, y, dx, dy;
		LinkedList<int[]> newQueue;
		
		int[] value;
		while(!queue.isEmpty()) {
			newQueue = new LinkedList<int[]>();
			while(!queue.isEmpty()) {
				value = queue.pollFirst();
				x = value[0];
				y = value[1];
				visited[x][y] = true;
				for (int d = 0; d < directX.length; d++) {
					dx = x + directX[d];
					dy = y + directY[d];
					if(0 <= dx && dx < N && 0 <= dy && dy < M && !visited[dx][dy]) {
						visited[dx][dy] = true;
						newQueue.addLast(new int[] {dx, dy});
					}
				}
			}
			answer++;
			queue = newQueue;
		}
		
		go:for (int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					answer = -1;
					break go;
				}
			}
		}
		
		System.out.println(answer);
		
	}
}
