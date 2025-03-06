import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9663 {
	static int N;
	static int[] pos;
	static int count;

	static boolean posCheck(int y, int x) {
		for (int yCount = 0; yCount < y; yCount++) {
			// 이전 라인들을 확인할 때 x좌표가 같은 라인이 있다면 먹힌다 불가능
			if (pos[yCount] == x) {
				return false;
			}

			// 이전 라인들을 확인할 때 대각선이 있다면 먹한다 불가능
			// 현재 우리가 확인할 대각선은 기울기의 절댓값이 1이다
			// y2 - y1
			// 기울기 = ------------- -> 1 or -1 라면 같은 대각선
			// x2 - x1
			// 위 아래 절댓값을 씌우고 x2 - x1을 넘기면
			// |y2 - y1| = |x2 - x1| 일 때 같은 대각선이다
			// 따라서 아래와 같은 경우 같은 대각선이다.
			if (Math.abs(yCount - y) == Math.abs(pos[yCount] - x)) {
				return false;
			}
		}

		// 모든 라인들과 x좌표가 다르고 대각선이 아니라면 true
		return true;
	}

	static void dfs(int y) {
		if (y == N) {
			count++;
			return;
		}

		// 모든 x 위치를 돌려본다
		for (int x = 0; x < N; x++) {
			if (posCheck(y, x)) {
				// 놓을 수 있는 위치라면 놓고 다음 라인으로
				pos[y] = x;
				dfs(y + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		pos = new int[N];

		dfs(0);

		System.out.println(count);
	}

}
