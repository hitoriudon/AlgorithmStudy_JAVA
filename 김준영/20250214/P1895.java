import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [1895, 필터]

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()); // 배열의 row
		int c = Integer.parseInt(st.nextToken()); // 배열의 col

		int arr[][] = new int[r][c];

		for (int row = 0; row < r; row++) { // 배열의 값
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < c; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(arr[row]));
		}

		int t = Integer.parseInt(br.readLine()); // t 이상의 값 계산 필요

		int res = 0;
		for (int row = 0; row < r - 2; row++) {
			for (int col = 0; col < c - 2; col++) {
				ArrayList<Integer> temp = new ArrayList<>();
				for(int tempRow = row; tempRow < row+3; tempRow++) {
					for(int tempCol = col; tempCol < col+3; tempCol++) {
						temp.add(arr[tempRow][tempCol]);
					}
				}
				Collections.sort(temp);

				res += (temp.get(4)>=t)?1:0;
			}
		}
		System.out.println(res);
	}
}
