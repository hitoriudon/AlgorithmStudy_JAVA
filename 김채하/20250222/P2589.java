import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2589 {
	static int N, M;
	static boolean isRange(int x,int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine())	;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] field = new boolean[N][M];
		char[] val;
		
		Queue<int[]> bfsQueue = new ArrayDeque();
		
		for(int x = 0; x < N; x++) {
			val = br.readLine().toCharArray();
			for(int y = 0; y < M; y++) {
				if(val[y] == 'L') {
					field[x][y] = true;
				}
			}
		}
		
		int[] directX = {0,0,1,-1};
		int[] directY = {1,-1,0,0};
		int dx, dy, r, c, l;
		int[] value;
		int answer = 0;
		boolean[][] visited = new boolean[N][M];
		
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < M; y++) {
				if(!field[x][y]) continue;
				visited = new boolean[N][M];
				bfsQueue.add(new int[] {x,y,0});
				visited[x][y] = true;
				while(!bfsQueue.isEmpty()){
					value = bfsQueue.poll();
					r = value[0];
					c = value[1];
					l = value[2] + 1;
					for (int i = 0; i < directX.length; i++) {
						dx = r + directX[i];
						dy = c + directY[i];
						if(isRange(dx, dy) && !visited[dx][dy] && field[dx][dy]) {
							visited[dx][dy] = true;
							answer = Math.max(l, answer);
							bfsQueue.add(new int[] {dx,dy, l});
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}

