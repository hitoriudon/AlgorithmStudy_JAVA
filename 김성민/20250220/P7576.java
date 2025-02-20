import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[y][x];
		int emptyAmount = 0;
		Queue<int[]> tomatoes = new LinkedList<>();
		for(int yCount = 0; yCount < y; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int xCount = 0; xCount < x; xCount++) {
				map[yCount][xCount] = Integer.parseInt(st.nextToken());
				if(map[yCount][xCount] == 0) {
					emptyAmount++;
				} else if(map[yCount][xCount] == 1) {
					tomatoes.add(new int[] {yCount, xCount, 0});
				}
			}
		}
		
		int curDay = 0;
		while(!tomatoes.isEmpty()) {
			int[] tomato = tomatoes.remove();
			int tomatoY = tomato[0];
			int tomatoX = tomato[1];
			curDay = tomato[2];
			
			for(int dir = 0; dir < 4; dir++) {
				int nextY = tomatoY + dy[dir];
				int nextX = tomatoX + dx[dir];
				
				if(nextY >= 0 && nextY < y && nextX >= 0 && nextX < x && map[nextY][nextX] == 0) {
					map[nextY][nextX] = 1;
					emptyAmount--;
					tomatoes.add(new int[] {nextY, nextX, curDay + 1});
				}
			}
		}
		
		if(emptyAmount == 0) {
			System.out.println(curDay);
		} else {
			System.out.println(-1);
		}
	}
	
}
