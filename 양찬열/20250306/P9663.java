package study.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// N-Queen
public class P9663 {
	static int N;
	static int count;
	static int cols, rightdown, leftdown;  // 방문 처리 bit masking
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		cols = 0;
		rightdown = 0;  // 차 + (N-1) -> 최대 2(N-1) -> 24이니 ok!
		leftdown = 0;  // 합 -> 최대 2(N-1)
		checkedQueen(0, cols, rightdown, leftdown);
		System.out.println(count);
	}

	// 행별로 처리
	private static void checkedQueen(int depth, int cols, int rightdown, int leftdown) {
		// TODO Auto-generated method stub
		if (depth == N) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((cols & (1 << i)) != 0 || (rightdown & (1 << (depth - i + N - 1))) != 0 || (leftdown & (1 << (depth + i))) != 0) {
				continue;
			}
			// 실수: 초기화! -> 필요한 값이 초기화 필요하다 싶으면 매개변수로
			checkedQueen(depth + 1, cols | (1 << i), rightdown | 1 << (depth - i + N - 1), leftdown | 1 << (depth + i));
		}
	}
}
