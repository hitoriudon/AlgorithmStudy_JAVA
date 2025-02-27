import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2573 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] directX = new int[]{0,0,-1,1};
		int[] directY = new int[]{1,-1,0,0};
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][]	field = new int[N][M];
		int[][]	zerofield = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(zerofield[i], 4);
		}
		
		ArrayDeque<int[]> deque = new ArrayDeque<>(); //섬 개수 셀때 필요한 deque
		ArrayDeque<int[]> bfsQueue = new ArrayDeque<>(); //bfs 돌때 deque
		ArrayDeque<int[]> zeroDeque = new ArrayDeque<>(); //섬이 물에 잠겼을때 처리해줄 deque
		
		int bx = 0, by = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				if(field[i][j] != 0) {
					deque.add(new int[] {i,j,0});
					bx = i;
					by = j;
					for (int j2 = 0; j2 < directX.length; j2++) {
						zerofield[i+directX[j2]][j+directY[j2]]--;
					}
				}
			}
		}
		
		int[] coor, zero, bfsCoor;
		int year = -1;
		int x, y, t;
		int zx, zy;
		int dx, dy;
		boolean[][] visited;
		int island = 0, bfsIsland;
		
		go : while(!deque.isEmpty()) {
			coor = deque.pollFirst();
			x = coor[0];
			y = coor[1];
			t = coor[2];
			if(t > year) { //다음 년도로 넘어가야함 잠기는 섬들 처리
				while(!zeroDeque.isEmpty()) {
					zero = zeroDeque.pollFirst();
					zx = zero[0];
					zy = zero[1];
					for (int j2 = 0; j2 < directX.length; j2++) {
						zerofield[zx+directX[j2]][zy+directY[j2]]++;
					}
				}
				year = t;
				
				visited = new boolean[N][M];
				bfsQueue = new ArrayDeque<>();
				bfsQueue.add(new int[] {bx,by});
				bfsIsland = 0;
				while(!bfsQueue.isEmpty()) {
					bfsCoor = bfsQueue.pollFirst();
					bx = bfsCoor[0];
					by = bfsCoor[1];
					
					for (int j2 = 0; j2 < directX.length; j2++) {
						dx = bx + directX[j2]; 
						dy = by + directY[j2];
						if(!visited[dx][dy] && field[dx][dy] > 0) {
							bfsIsland++;
							bfsQueue.add(new int[] {dx,dy});
							visited[dx][dy] = true;
						}
					}
				}
				
				if(bfsIsland < island) break go;
				
				island = 0;
			}
			
			if(zerofield[x][y] >= field[x][y]) {
				field[x][y] = 0;
				zeroDeque.add(new int[] {x,y});
			}else {
				field[x][y] -= zerofield[x][y];
				bx = x;
				by = y;
				island++;		
				deque.addLast(new int[] {x,y,t+1});
			}
		}
		if(deque.isEmpty()) {
			System.out.println(0);			
		}else {			
			System.out.println(year);			
		}
	}
}
