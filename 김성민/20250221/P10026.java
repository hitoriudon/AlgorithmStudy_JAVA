import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P10026 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static int mapSize;
	static int[][] mapNo;
	static int[][] mapYes;
	
	static boolean[][] visited;
	static Queue<int[]> colorQue;
	
	static boolean isRange(int y, int x) {
		return (y >= 0 && y < mapSize && x >= 0 && x < mapSize);
	}
	
	static void bfs(boolean map) {
		int[][] curMap;
		if(map) {
			curMap = mapYes;
		} else {
			curMap = mapNo;
		}
		
		while(!colorQue.isEmpty()) {
			int[] colorInfo = colorQue.remove();
			int color = colorInfo[0];
			int curY = colorInfo[1];
			int curX = colorInfo[2];
			
			for(int dir = 0; dir < 4; dir++) {
				int nextY = curY + dy[dir];
				int nextX = curX + dx[dir];
				if(isRange(nextY, nextX) && !visited[nextY][nextX] && color == curMap[nextY][nextX]) {
					visited[nextY][nextX] = true;
					colorQue.add(new int[] {color, nextY, nextX});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		mapNo = new int [mapSize][mapSize];
		mapYes = new int [mapSize][mapSize];
		
		for(int y = 0; y < mapSize; y++) {
			String line = br.readLine();
			for(int x = 0; x < mapSize; x++) {
				int color = line.charAt(x);
				mapNo[y][x] = color;
				if(color == 'G') {
					color = 'R';
				}
				mapYes[y][x] = color;
			}
		}

		visited = new boolean[mapSize][mapSize];
		colorQue = new LinkedList<>();
		int areaAmountNo = 0;
		for(int y = 0; y < mapSize; y++) {
			for(int x = 0; x < mapSize; x++) {
				if(!visited[y][x]) {
					areaAmountNo++;
					visited[y][x] = true;
					colorQue.add(new int[] {mapNo[y][x], y, x});
					bfs(false);
				}
			}
		}

		visited = new boolean[mapSize][mapSize];
		colorQue = new LinkedList<>();
		int areaAmountYes = 0;
		for(int y = 0; y < mapSize; y++) {
			for(int x = 0; x < mapSize; x++) {
				if(!visited[y][x]) {
					areaAmountYes++;
					visited[y][x] = true;
					colorQue.add(new int[] {mapYes[y][x], y, x});
					bfs(true);
				}
			}
		}
		
		System.out.println(areaAmountNo + " " + areaAmountYes);
	}
	
}
