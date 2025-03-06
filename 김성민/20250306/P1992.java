import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1992 {
	static StringBuilder sb = new StringBuilder();
	
	static int mapSize;
	static int[][] map;
	
	static void dfs(int curLength, int startY, int startX) {
		// 가장 처음 숫자로 탐색
		int curNum = map[startY][startX];
		
		// 다 같은 숫자만 있는지 확인
		boolean flag = true;
		for(int yCount = startY; yCount < startY + curLength; yCount++) {
			for(int xCount = startX; xCount < startX + curLength; xCount++) {
				// 다른 숫자가 있다면 탐색 중지
				if(curNum != map[yCount][xCount]) {
					flag = false;
				}
			}
			
			if(!flag) {
				break;
			}
		}
		
		// 압축된 경우 숫자 추가
		if(flag) {
			sb.append(curNum);
		} 
		// 다른 숫자가 있는 경우 탐색길이를 반으로 줄이고 4등분으로 나눠 다시 확인
		else {
			// 압축이 안될 때마다 괄호 씌우기
			sb.append("(");
			int halfLength = curLength / 2;
			dfs(halfLength, startY, startX);
			dfs(halfLength, startY, startX + halfLength);
			dfs(halfLength, startY + halfLength, startX);
			dfs(halfLength, startY + halfLength, startX + halfLength);
			sb.append(")");
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		
		map = new int[mapSize][mapSize];
		for(int yCount = 0; yCount < mapSize; yCount++) {
			String line = br.readLine();
			for(int xCount = 0; xCount < mapSize; xCount++) {
				map[yCount][xCount] = Integer.parseInt(line.charAt(xCount) + "");
			}
		}

		dfs(mapSize, 0, 0);
		
		System.out.println(sb);
	}
	
}
