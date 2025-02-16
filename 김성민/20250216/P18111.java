import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int z = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int myBlock = Integer.parseInt(st.nextToken());

		int[][] map = new int[z][x];
		// 가장 낮은 높이
		int minY = 256;
		// 가장 높은 높이
		int maxY = 0;

		for (int i = 0; i < z; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < x; j++) {
				int y = Integer.parseInt(st.nextToken());
				map[i][j] = y;
				if (y < minY) {
					minY = y;
				}
				else if (y > maxY) {
					maxY = y;
				}
			}
		}

		int time = Integer.MAX_VALUE;
		int resultY = 0;
		// 같은 시간일 경우 높은 Y를 출력해야하기 때문에
		// 저장 낮은 높이부터 가장 높은 높이까지 탐색
		for (int curY = minY; curY <= maxY; curY++) {
			// 탐색마다 시간과 블럭 수 초기화
			int curTime = 0;
			int curBlock = myBlock;
			for (int curZ = 0; curZ < z; curZ++) {
				for (int curX = 0; curX < x; curX++) {
					// 현재 Y 목표보다 map의 Y가 높을 경우 블럭 제거
					if (curY < map[curZ][curX]) {
						curTime += ((map[curZ][curX] - curY) * 2);
						curBlock += (map[curZ][curX] - curY);
						
					} 
					// 현재 Y 목표보다 map의 Y가 낮을 경우 블럭 설치
					else {
						curTime += (curY - map[curZ][curX]);
						curBlock -= (curY - map[curZ][curX]);
					}
				}
			}
			
			if (curBlock >= 0 && curTime <= time) {
				time = curTime;
				resultY = curY;
			}
		}
		
		System.out.println(time + " " + resultY);
	}

}
