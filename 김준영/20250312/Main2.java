import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [1932 정수 삼각형]

public class Main {
	static int n, m, k;
	static int[][] arr;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 배열의 크기 N

		// 입력
		arr = new int[n][n];
		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			
			for (int col = 0; col <= row; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// DP
		for (int row = 1; row < n; row++) {
			for (int col = 0; col <= row; col++) {
				if(col -1 >= 0) { // 맨 왼쪽 원소가 아닌 경우			
					arr[row][col] += Math.max(arr[row-1][col-1], arr[row-1][col]); // 위 값의 왼쪽 오른쪽 값 중의 최대값을 더함
				} else { // 맨 왼쪽 원소인 경우
					arr[row][col] += arr[row-1][col];
				}
			}
		}
		
		
		res = 0;
		for(int col = 0; col < n; col++) {
			res = Math.max(arr[n-1][col], res);
		}
		
		System.out.println(res);
	}
}