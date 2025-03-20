import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//종이의 개수
public class P1780 {
	static int[] count = new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n]; 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(map, 0, 0, n);
		for(int i = 0; i < 3; i++) {
			System.out.println(count[i]);
		}
	}

	private static void dfs(int[][] map, int x, int y, int size) {
		if(check(map, x, y, size)) {
			count[map[x][y] + 1]++;
			return;
		}
		
		int newSize = size / 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				dfs(map, x + i * newSize, y + j * newSize, newSize);
			}
		}
	}

	private static boolean check(int[][] map, int x, int y, int size) {
		int num = map[x][y];
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(map[i][j] != num) {
					return false;
				}
			}
		}
		return true;
	}
}
