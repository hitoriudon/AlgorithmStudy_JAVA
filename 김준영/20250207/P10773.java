import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/*
 * 잘못된 수 일때 0 -> 가장 쓴 수 지움
 */

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> stk = new ArrayDeque<Integer>();
		
		int input;
		
		// 연산 수행
		for(int i = 0; i < k; i ++) {
			input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(stk.isEmpty()) {
					System.out.println("empty stk");;
				}
				else {
					stk.pop();
				}
			}
			else {
				stk.push(input);
			}
		}
		
		// 합 계산
		int res = 0;
		for(int num : stk) {
			res+=num;
		}
		System.out.println(res);
	}
	
}
