import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2146 {
	static int N;
	static boolean isRange(int x,int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int[][] field = new int[N][N];
		int val;
		
		// 메모리가 쓰레기가 된 원인1 큐 3개
		
		//섬을 나누기 위해 육지를 저장하는 큐
		ArrayDeque<int[]> queue = new ArrayDeque();
		
		//섬을 구분하기 위해 bfs에 사용하는 큐
		ArrayDeque<int[]> bfsQueue = new ArrayDeque();
		
		//다리 건설을 위해 다리의 진척도?를 저장하는 큐
		ArrayDeque<int[]> dari = new ArrayDeque();
		
		//값 입력 받기 육지는 -1로 초기화
		for(int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y = 0; y < N; y++) {
				val =  Integer.parseInt(st.nextToken());
				if(val == 1) {
					field[x][y] = -1;
					queue.add(new int[] {x,y});
				}
			}
		}
		
		int[] directX = {0,0,1,-1};
		int[] directY = {1,-1,0,0};
		
		int x, y, dx, dy;
		int[] value;
		int idx = 1;
		boolean[][] visited = new boolean[N][N];
		
		//저장된 육지의 값들을 순회
		while(!queue.isEmpty()) {
			value = queue.pollFirst();
			//이미 방문한 육지인지 확인 방문 안했으면 번호 매기기
			if(visited[value[0]][value[1]]) continue;
			bfsQueue.add(value);
			field[value[0]][value[1]] = idx;
			visited[value[0]][value[1]] = true;
			dari.addLast(new int[] {value[0],value[1],0, idx});
			
			//주변 육지가 있는지 확인 후 번호 매기기
			while(!bfsQueue.isEmpty()){
				value = bfsQueue.pollFirst();
				x = value[0];
				y = value[1];
				for (int i = 0; i < directX.length; i++) {
					dx = x + directX[i];
					dy = y + directY[i];
					if(isRange(dx, dy) && !visited[dx][dy] && field[dx][dy] == -1) {
						visited[dx][dy] = true;
						field[dx][dy] = idx;
						bfsQueue.addLast(new int[] {dx,dy});
						dari.addLast(new int[] {dx,dy,0, idx});
					}
				}				
			}
			idx++;
		}
		
		//섬번호별로 visited 관리 - 메모리가 쓰레기가 된 원인2
		boolean[][][] visitedIsland = new boolean[N][N][idx];
		int answer = Integer.MAX_VALUE;
		int len, s;
		
		//모든 육지에서 다리를 건설해서 가장 빨리 연결한 다리 길이 반환
		go : while(!dari.isEmpty()) {
			value = dari.pollFirst();
			x = value[0];
			y = value[1];
			len = value[2];
			s = value[3];
			
			
			for (int i = 0; i < directX.length; i++) { // 동서남북 모두 탐색
				dx = x + directX[i];
				dy = y + directY[i];
				if(isRange(dx, dy) && !visitedIsland[dx][dy][s]) {
					visitedIsland[dx][dy][s] = true;
					
					if(field[dx][dy] == 0) { //다리를 더 연결 할 수 있음			
						dari.addLast(new int[] {dx,dy,len+1,s});
					}else if(field[dx][dy] != s) { //다른 섬 도착! 연결된 다리 완성
						answer = len;
						break go;
					}
				}
			}
		}
		System.out.println(answer);
	}
}

