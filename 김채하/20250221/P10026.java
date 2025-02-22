import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class P10026 {
	static boolean isRange(int x, int y,int N) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//두번 bfs 돈다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] field = new int[N][N];
		int[] directX = new int[] {0,1,0,-1};
		int[] directY = new int[] {1,0,-1,0};
		
		
		for (int i = 0; i < N; i++) {
			int j = 0;
			for(char val : br.readLine().toCharArray()) {
				if(val == 'B') {
					field[i][j] = 1;
				}
				if(val == 'G') {
					field[i][j] = 2;
				}
				j++;
			}
		}
		
		//비색약인 bfs
		LinkedList<int[]> queue = new LinkedList();
		boolean[][] visited = new boolean[N][N];
		int colorZone = 0;
		int nx,ny,dx,dy;
		int[] value;
		
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				if(visited[x][y]) continue;
				
				queue.add(new int[] {x,y});
				colorZone++;
				visited[x][y] = true;
				
				while(!queue.isEmpty()) {
					value = queue.pollFirst();
					nx = value[0];
					ny = value[1];
					for (int i = 0; i < directX.length; i++) {
						dx = nx + directX[i];
						dy = ny + directY[i];
						
						if(isRange(dx, dy, N) && field[dx][dy] == field[x][y] && !visited[dx][dy]) {
							visited[dx][dy] = true;			
							queue.addLast(new int[] {dx,dy});
						}
					}
				}
			}
		}
		
		queue = new LinkedList<>();
		visited = new boolean[N][N];
		int zone = 0;
		
		//색약인 bfs
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				if(visited[x][y]) continue;
				
				queue.add(new int[] {x,y});
				zone++;
				visited[x][y] = true;
				
				while(!queue.isEmpty()) {
					value = queue.pollFirst();
					nx = value[0];
					ny = value[1];
					visited[nx][ny] = true;			
					for (int i = 0; i < directX.length; i++) {
						dx = nx + directX[i];
						dy = ny + directY[i];
						
						if(isRange(dx, dy, N) && (field[dx][dy] %2) == (field[x][y] % 2)&& !visited[dx][dy]) {
							visited[dx][dy] = true;			
							queue.addLast(new int[] {dx,dy});
						}
					}
				}
			}
		}
		
		System.out.println(colorZone+" "+zone);
		
		
	}
}
