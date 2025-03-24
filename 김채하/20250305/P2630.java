import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
	static boolean[][] field;
	static int blue= 0, white = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		field = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = st.nextToken().equals("1")? true : false;
			}
		}
		recursion(0, 0, N);
		System.out.println(white);
		System.out.println(blue);

	}

	static void recursion(int x, int y,int n) {
		if(n == 1) {
			if(field[x][y]) {
				blue++;
			}else {
				white++;
			}
			return;
		}
		boolean flag = field[x][y];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(flag ^ field[x+i][y+j]) {
					recursion(x, y, n/2);
					recursion(x+(n/2), y, n/2);
					recursion(x, y+(n/2), n/2);
					recursion(x+(n/2), y+(n/2), n/2);
					return;
				}
			}
		}
		if(flag) {
			blue++;
		}else {
			white++;
		}
	}
}
