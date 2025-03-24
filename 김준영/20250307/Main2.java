import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] paper;
	static int[] res;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		paper = new int[n][n];

		// 입력
		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < n; col++) {
				paper[row][col] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrasys.toString(paper[row]));
		}

		res = new int[3];
		dfs(0, 0, n);

		for (int r : res) {
			System.out.println(r);
		}
	}

	static void dfs(int startRow, int startCol, int size) {
		if (!canSplit(startRow, startCol, size)) { // 못나누는 경우
			res[paper[startRow][startCol] + 1]++;
			return;
		}

		dfs(startRow, startCol, size / 3);
		dfs(startRow, startCol + size / 3, size / 3);
		dfs(startRow, startCol + size / 3 * 2, size / 3);

		dfs(startRow + size / 3, startCol, size / 3);
		dfs(startRow + size / 3, startCol + size / 3, size / 3);
		dfs(startRow + size / 3, startCol + size / 3 * 2, size / 3);

		dfs(startRow + size / 3 * 2, startCol, size / 3);
		dfs(startRow + size / 3 * 2, startCol + size / 3, size / 3);
		dfs(startRow + size / 3 * 2, startCol + size / 3 * 2, size / 3);
	}

	static boolean canSplit(int startRow, int startCol, int size) {

		int firstNum = paper[startRow][startCol];

		for (int row = startRow; row < startRow + size; row++) {
			for (int col = startCol; col < startCol + size; col++) {
				if (paper[row][col] != firstNum)
					return true;
			}
		}

		return false;
	}
}
