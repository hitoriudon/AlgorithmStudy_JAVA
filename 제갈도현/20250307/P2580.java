import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P2580 {
	static int[][] sudoku = new int[9][9];
	static LinkedList<int[]> q = new LinkedList<>();

	// 행 체크
	static boolean checkRow(int x, int y, int n) {
		for (int d = 0; d < 9; d++)
			if (sudoku[x][d] == n)
				return false;

		return true;
	}

	// 열 체크
	static boolean checkCol(int x, int y, int n) {
		for (int d = 0; d < 9; d++)
			if (sudoku[d][y] == n)
				return false;

		return true;
	}

	// 네모 칸 체크
	static boolean checkBox(int x, int y, int n) {
		boolean[] isUsed = new boolean[10];

		for (int i = (x / 3) * 3; i < (x / 3) * 3 + 3; i++) {
			for (int j = (y / 3) * 3; j < (y / 3) * 3 + 3; j++) {
				if (sudoku[i][j] == n)
					return false;
			}
		}

		return true;
	}
	
	static void printResult() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		// 출력 후 바로 종료
		System.exit(0);
	}

	static void inputNumber(int index) {
		// 만약 모든 칸을 다 채운 경우 출력
		if (index == q.size()) {
			printResult();
			return;
		}

		// 큐에서 채울 빈칸을 선택
		int[] curr = q.get(index);
		int cx = curr[0];
		int cy = curr[1];

		for (int n = 1; n <= 9; n++) {
			// 조건 검사
			if (checkRow(cx, cy, n) && checkCol(cx, cy, n) && checkBox(cx, cy, n)) {
				// 숫자 기록
				sudoku[cx][cy] = n;
				// 다음 dfs
				inputNumber(index + 1);
				// 숫자 해제
				sudoku[cx][cy] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0이 기록된 칸의 좌표 기록
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					q.add(new int[] { i, j });
				}
			}
		}
		
		// 스도쿠 풀이 시작
		inputNumber(0);
	}
}
