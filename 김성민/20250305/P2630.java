import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
	static int mapSize;
	static int[][] map;
	
	static int whiteAmount = 0;
	static int blueAmount = 0;
	
	static void dfs(int curLength, int startY, int startX) {
		// 현재 맨 처음 색상 저장
		int curColor = map[startY][startX];
		
		// 다른 색상이 없는지 확인하느 flag
		boolean flag = true;
		for(int curY = startY; curY < startY + curLength; curY++) {
			for(int curX = startX; curX < startX + curLength; curX++) {
				// 다른 색상이 나온다면 정지
				if(map[curY][curX] != curColor) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				break;
			}
		}
		
		// 다른 색상이 있었다면 길이 절반으로 나누고 4등분해서 dfs
		if(!flag) {
			int halfLength = curLength / 2;
			dfs(halfLength, startY, startX);
			dfs(halfLength, startY, startX + halfLength);
			dfs(halfLength, startY + halfLength, startX);
			dfs(halfLength, startY + halfLength, startX + halfLength);
		} 
		// 다른 색상이 없었다면 맨 처음 색상에 맞춰 색상 색종이 추가
		else {
			if(curColor == 0) {
				whiteAmount++;
			} else {
				blueAmount++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		for(int yCount = 0; yCount < mapSize; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int xCount = 0; xCount < mapSize; xCount++) {
				map[yCount][xCount] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(mapSize, 0, 0);
		
		System.out.println(whiteAmount);
		System.out.println(blueAmount);
	}
	
}
