import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9663 {
	static boolean visited[];
	static boolean visitedcross[][];
	static int N, answer = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());


		//가로, 세로, 대각선, 역대각선
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visitedcross = new boolean[2][2*N];
			visited[i] = true;
			visitedcross[0][N+0-i] = true;
			visitedcross[1][i] = true;
			dfs(1);
		}
		System.out.println(answer);
	}

	public static void dfs(int x) {
		if(x >= N) {
			answer++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(!visited[i] && !visitedcross[0][N+x-i] && !visitedcross[1][i+x]) {
				visited[i] = true;
				visitedcross[0][N+x-i] = true;
				visitedcross[1][i+x] = true;
				dfs(x+1);
				visited[i] = false;
				visitedcross[0][N+x-i] = false;
				visitedcross[1][i+x] = false;
			}
		}
	}
}
