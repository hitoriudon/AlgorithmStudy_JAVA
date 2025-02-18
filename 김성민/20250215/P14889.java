import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class P14889 {
	
	static int[][] map;
	static int mapSize;
	static boolean[] visit;
	
	static int minDiff = Integer.MAX_VALUE;
 
	// idx는 현재 번호, count 현재 팀원 카운트
	static void dfs(int idx, int count) {
		// 현재 팀 카운트가 전체 인원의 반이 되었을 때 능력치 차 계산
		if(count == mapSize / 2) {
			checkDiff();
			return;
		}
 
		for(int i = idx; i < mapSize; i++) {
            visit[i] = true;
            dfs(i + 1, count + 1);
            visit[i] = false;
		}
	}
 
	static void checkDiff() {
		int startTeam = 0;
		int linkTeam = 0;
 
		for (int i = 0; i < mapSize - 1; i++) {
			for (int j = i + 1; j < mapSize; j++) {
                // 두 사람 모두가 true 일 때 스타트팀 , 모두 false 일 때 링크팀에 능력치 계산
				if (visit[i] == true && visit[j] == true) {
					startTeam += (map[i][j] + map[j][i]);
				}
				else if (visit[i] == false && visit[j] == false) {
					linkTeam += (map[i][j] + map[j][i]);
				}
			}
		}
		
		int curDiff = Math.abs(startTeam - linkTeam);
		
		if (curDiff < minDiff) {
            minDiff = curDiff;
        }	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
 
    	mapSize = Integer.parseInt(br.readLine());
 
		map = new int[mapSize][mapSize];
		visit = new boolean[mapSize];
 
		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
 
		dfs(0, 0);
		System.out.println(minDiff);
	}
 
}