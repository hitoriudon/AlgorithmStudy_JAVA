import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int r, c;
	static char[][] alphabet;
	static boolean[] visit;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		alphabet = new char[r][c];
		
		for (int row = 0; row < r; row++) {
			String temp = br.readLine();
			for (int col = 0; col < c; col++) {
				alphabet[row][col] = temp.charAt(col);
			}
		}

		visit = new boolean['Z' + 1];
		visit[alphabet[0][0]] = true;
//		System.out.println(alphabet[0][0]);
		
		res = 0;
		
		makePath(0, 0, 1);
		
		System.out.println(res);
	}

	static void makePath(int row, int col, int depth) {
		
		res = Math.max(res, depth);
		
		for(int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if(nx >= 0 && nx < r && ny >= 0 && ny < c) {
				if(visit[alphabet[nx][ny]] == false) {
//					System.out.println(nx + " " + ny + " " + alphabet[nx][ny]);
					visit[alphabet[nx][ny]] = true;
					makePath(nx, ny, depth + 1);
					visit[alphabet[nx][ny]] = false;
					
				}

			}
		}
		
		
	}
}
