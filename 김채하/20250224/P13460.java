import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class P13460 {
	
 public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	boolean[][] cantMove = new boolean[N][M];
	//rxrybxby 7자리 숫자로 파란색과 빨강색 공의 위치 해싱
	boolean[] visited= new boolean[9090909];
	
	Queue<int[]> queue = new ArrayDeque<>();
	
	//서, 동, 남, 북
	int[] dx = {0,0,1,-1};
	int[] dy = {-1,1,0,0};	
	
	int c;
	int rx, ry, bx, by;
	int fx, fy;
	rx = ry = bx = by = 0;
	fx = fy = 0;
	
	for (int r = 0; r < N; r++) {
		c = 0;
		for(char val : br.readLine().toCharArray()) {
			if(val == 'R') {
				rx = r;
				ry = c;
			}
			if(val == 'B') {
				bx = r;
				by = c;
			}
			if(val == '#') {
				cantMove[r][c] = true;
			}
			if(val == 'O') {
				fx = r;
				fy = c;
			}
			c++;
		}
	}
	queue.add(new int[]{rx, ry, bx, by, 0});
	visited[rx * 1000000 + ry * 10000 + bx * 100 + by] = true;
	
	int answer = Integer.MAX_VALUE;
	
	boolean bGoal, rGoal;
	int drx, dry, dbx, dby;
	drx = dry = dbx = dby = 0;
	
	int location[];
	while(!queue.isEmpty()) {
		location = queue.poll();
		if(location[4] >= 10) continue;		
		for(int d = 0; d < 4; d++) {
			rx = location[0];
			ry = location[1];
			bx = location[2];
			by = location[3];
			bGoal = rGoal = false;

			if(rx * dx[d] + ry * dy[d] < bx * dx[d] + by * dy[d]) { //파랑이가 먼저 이동
				
				while(!cantMove[bx][by]){
					dbx = bx + dx[d];
					dby = by + dy[d];
					if(cantMove[dbx][dby] || (rx == dbx && ry == dby)) break;
					
					bx = dbx;
					by = dby;

					if(bx == fx && by == fy) {
						bx = -1;
						by = -1;
						bGoal = true;
						break;
					}
				}
				while(!cantMove[rx][ry]){
					drx = rx + dx[d];
					dry = ry + dy[d];
					
					//벽이거나 파란공이 있을때
					if(cantMove[drx][dry] || (drx == bx && dry == by)) break;
					
					
					rx = drx;
					ry = dry;

					if(rx == fx && ry == fy) {
						rx = -1;
						ry = -1;
						rGoal = true;
						break;
					}
				}
				
				
			}else { //빨강 이동하고 파랑
				
				while(!cantMove[rx][ry]){
					drx = rx + dx[d];
					dry = ry + dy[d];
					
					//벽이거나 파란공이 있을때
					if(cantMove[drx][dry] || (drx == bx && dry == by)) break;					
					rx = drx;
					ry = dry;
					
					if(rx == fx && ry == fy) {
						rx = -1;
						ry = -1;
						rGoal = true;
						break;
					}
				}

				while(!cantMove[bx][by]){
					dbx = bx + dx[d];
					dby = by + dy[d];
					if(cantMove[dbx][dby] || (rx == dbx && ry == dby)) break;
										
					bx = dbx;
					by = dby;
					
					if(bx == fx && by == fy) {
						bx = -1;
						by = -1;
						bGoal = true;
						break;
					}
				}
			}
			
			if(bGoal) continue; //파란공 골인
			
			if(rGoal) {
				answer = Math.min(location[4] + 1,answer);
				continue;
			}
			
			//이동 완료했는데 이미 왔던곳 == 최단경로가 아님
			if(visited[rx * 1000000 + ry * 10000 + bx * 100 + by]) continue;
			
			visited[rx * 1000000 + ry * 10000 + bx * 100 + by] = true;
			queue.add(new int[]{rx, ry, bx, by, location[4]+1});
		}
	}
	System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
 }
}
