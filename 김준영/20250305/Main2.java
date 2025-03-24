import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] paper;
	static int whiteCnt, blueCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		paper = new int[n][n];

		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < n; col++) {
				paper[row][col] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(paper[row]));
		}

		whiteCnt = 0;
		blueCnt = 0;
		
		split(0, 0, n);

		System.out.println(whiteCnt + "\n" + blueCnt);

	}

	static void split(int startRow, int startCol, int size) {
		if (!canSplit(startRow, startCol, size)) { // 나눌수 있는 경우
			if (paper[startRow][startCol] == 0)
				whiteCnt++;
			else
				blueCnt++;
			return;
		}

		split(startRow, startCol, size / 2);
		split(startRow + size / 2, startCol, size / 2);
		split(startRow, startCol + size / 2, size / 2);
		split(startRow + size / 2, startCol + size / 2, size / 2);
	}

	static boolean canSplit(int startRow, int startCol, int size) { // 색이 같으면 못나눔

		int firstColor = paper[startRow][startCol];

		for (int row = startRow; row < startRow + size; row++) {
			for (int col = startCol; col < startCol + size; col++) {
				if (paper[row][col] != firstColor)
					return true;
			}
		}

		return false;
	}
}
