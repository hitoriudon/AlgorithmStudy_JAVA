import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
	static int[][] field;
	static int zero=0,plus=0,minus=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		field = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursion(0, 0, N);
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(plus);

	}

	static void recursion(int x, int y,int n) {
		if(n == 1) {
			if(field[x][y] == 0) {
				zero++;
			}else if(field[x][y] == -1){
				minus++;
			}else {
				plus++;
			}
			return;
		}
		int flag = field[x][y];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(flag != field[x+i][y+j]) {
					recursion(x, y, n/3);
					recursion(x+(n/3), y, n/3);
					recursion(x+(n/3*2), y, n/3);
					recursion(x, y+(n/3), n/3);
					recursion(x, y+(n/3*2), n/3);
					recursion(x+(n/3), y+(n/3), n/3);
					recursion(x+(n/3), y+(n/3*2), n/3);
					recursion(x+(n/3*2), y+(n/3), n/3);
					recursion(x+(n/3*2), y+(n/3*2), n/3);
					return;
				}
			}
		}
		if(field[x][y] == 0) {
			zero++;
		}else if(field[x][y] == -1){
			minus++;
		}else {
			plus++;
		}
	}
}
