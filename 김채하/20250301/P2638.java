import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class P2638 {
	static int[][] field, melting;

	static boolean[][] visited;
	static int N,M;
	static ArrayDeque<int[]> queue;
	static int[] directX = {0,0,-1,1};
	static int[] directY = {-1,1,0,0};

	public static boolean isRange(int x, int y ) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		field = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = Integer.parseInt(st.nextToken()) * 2;

			}
		}
		queue = new ArrayDeque();
		for (int i = 0; i < N; i++) {
			visited[i][0] = true;
			queue.add(new int[] {i,0});
			visited[i][M-1] = true;
			queue.add(new int[] {i,M-1});
		}
		for (int i = 0; i < M; i++) {
			visited[0][i] = true;
			queue.add(new int[] {0,i});
			visited[N-1][i] = true;
			queue.add(new int[] {N-1,i});
		}

		bfs(0);

	}

	static void bfs(int time) {
		while(!queue.isEmpty()) {
			ArrayDeque<int[]> nextQueue = new ArrayDeque<>();
			while (!queue.isEmpty()) {
				int[] val = queue.pollFirst();
				int nx = val[0];
				int ny = val[1];
				for (int d = 0; d < 4; d++) {
					int dx = nx + directX[d];
					int dy = ny + directY[d];

					if (isRange(dx, dy) && !visited[dx][dy]) {
						if (field[dx][dy] == 0) {
							queue.add(new int[] {dx, dy});
							visited[dx][dy] = true;
						} else if(field[dx][dy] == 1) {
							field[dx][dy] = 0;
							visited[dx][dy] = true;
							nextQueue.add(new int[] {dx,dy});
						}else {
							field[dx][dy]--;
						}
					}
				}
			}
			if(nextQueue.isEmpty()) break;
			time++;
			queue = nextQueue;
		}
		System.out.println(time);
	}

}
