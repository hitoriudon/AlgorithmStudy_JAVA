import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14503 {
	static int N, M, r, c, d;
	static int cleanCount;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};  // 북, 동, 남, 서
	static int[] dy = {0, 1, 0, -1};
//	static int[][] isCleaned;  // map에 직접 처리하자
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			if (map[r][c] == 0) {
				map[r][c] = -1;
				cleanCount++;
			}
			if(checkedAround(r, c)) {
//				System.out.println("move");
				d = d==0?3:d - 1;  // 반시계 회전 0->3/1->0/2->1/3->2
				if (checkedRange(r + dx[d], c + dy[d]) && map[r + dx[d]][c + dy[d]] == 0) {
					r +=  dx[d];
					c += dy[d];
				}
			}
			else {
//				System.out.println("back");
				if (checkedRange(r - dx[d], c - dy[d]) && map[r - dx[d]][c - dy[d]] != 1) {
					r -= dx[d];
					c -= dy[d];
				}
				else {
					break;
				}
			}
		}
		
		System.out.println(cleanCount);
	}

	private static boolean checkedAround(int x, int y) {
		// TODO Auto-generated method stub
		for (int dir = 0; dir < 4; dir++) {
			if (checkedRange(x + dx[dir], y + dy[dir]) && map[x + dx[dir]][y + dy[dir]] == 0) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkedRange(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
