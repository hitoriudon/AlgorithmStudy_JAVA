// [9963 N-Queen]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static int res;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		res = 0;
		
		board = new int[n][n];
		
		dfs(0);
		
		System.out.println(res);
		
	}
	
	static void dfs(int row) {
		if(row == n) {
			res++;
			return;
		}
		
		for(int col = 0; col < n; col++) {
			if(can(row, col)) {
				board[row][col] = 1;
				dfs(row+1);
				board[row][col] = 0;
			}
		}
	}
	
	static boolean can(int row, int col) {
		// 세로 체크
		for(int i = 0; i < n; i++) {
			if(board[i][col] == 1) return false;
		}
		// 대각선(/) 체크
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i + j == row + col) {
					if(board[i][j] == 1) return false;
				}
			}
		}
		
		// 대각선(\) 체크
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i - j == row - col) {
					if(board[i][j] == 1) return false;
				}
			}
		}
		
		return true;
	}
}
