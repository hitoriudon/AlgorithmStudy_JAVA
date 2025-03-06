import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9663 {
	static int N, res;
	static boolean[] col, slash, bslash;

	private static void N_Queen(int row) {
		if (row >= N) {
			res++;
			return;
		}

		// col = 열
		// slash = i + j
		// bslash = i - j + 보정값
		for (int c = 0; c < N; c++) {
			if (!col[c] && !slash[row + c] && !bslash[row - c + (N - 1)]) {
				col[c] = slash[row + c] = bslash[row - c + (N - 1)] = true;
				N_Queen(row + 1);
				col[c] = slash[row + c] = bslash[row - c + (N - 1)] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		col = new boolean[N];
		slash = new boolean[N * 2 - 1];
		bslash = new boolean[N * 2 - 1];

		res = 0;
		N_Queen(0);
		System.out.println(res);
	}
}