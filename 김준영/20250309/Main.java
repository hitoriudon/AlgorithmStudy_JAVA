import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [2580 스도쿠]

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] board = new int[9][9];
	static ArrayList<SD> arr = new ArrayList<>();
	static boolean can = false;
	
	static class SD {
		int row;
		int col;

		SD(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 1. 입력
		for (int row = 0; row < 9; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < 9; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == 0) {
					arr.add(new SD(row, col));
				}
			}
		}

		// 3. DFS
		for (SD s : arr) {
//			System.out.println("Row : " + s.row + ", Col : " + s.col);
		}
		dfs(0);

		// 2. 출력
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				sb.append(board[row][col]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int depth) {
		if (depth == arr.size()) { // 0 ~ arr.size() - 1 까지의 좌표를 사용 한 후, arr.size()번쨰 원소 접근하려 하면 리턴
			can = true;
			return;
		}

		SD cur = arr.get(depth);
		int curRow = cur.row;
		int curCol = cur.col;

		boolean chk = false;

		for (int num = 1; num <= 9; num++) { // 현재 좌표에 넣을 수 있는 수 탐색
			if (can(curRow, curCol, num)) { // 넣을 수 있다면
				chk = true;
				board[curRow][curCol] = num;
				dfs(depth + 1);
				if(!can) board[curRow][curCol] = 0;

			}
		}
		if (chk == false)
			return; // 넣을게 없다면
	}

	static boolean can(int row, int col, int num) {
		// 가로 체크
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num)
				return false;
		}
		// 세로 체크
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num)
				return false;
		}
		// 네모 체크
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if(board[i][j] == num)
					return false;
			}
		}

		return true;
	}
}