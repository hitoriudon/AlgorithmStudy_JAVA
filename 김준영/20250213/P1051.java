import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[1051, 숫자 정사각형]
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int arr[][] = new int[n][m];

		int gap = Math.min(n, m);
		int res = 1;

		for (int row = 0; row < n; row++) {
			String input = br.readLine();
			for (int col = 0; col < m; col++) {
				arr[row][col] = input.charAt(col) - '0';
			}
//			System.out.println();
//			System.out.println(Arrays.toString(arr[row]));
		}
		
		out : while (gap > 1) {			

			for (int row = 0; row <= n - gap; row++) {
				for (int col = 0; col <= m - gap; col++) {
					if (arr[row][col] == arr[row + gap - 1][col]) {
						if (arr[row][col] == arr[row][col + gap - 1]) {
							if (arr[row][col] == arr[row + gap - 1][col + gap - 1]) {
								res = (gap)*(gap);
								break out;
							}
						}
					}
				}
			}
			gap--;
		}
//		System.out.println("gap:"+gap);

		System.out.println(res);
}
}