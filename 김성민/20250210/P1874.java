package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class P1874 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int numAmount = Integer.parseInt(br.readLine());

		Deque<Integer> nums = new LinkedList<>();
		// 현재 들어갈 숫자
		int curNum = 1;
		for (int i = 1; i <= numAmount; i++) {
			int target = Integer.parseInt(br.readLine());

			// 현재 숫자 들어갈 숫자보다 타켓의 숫자가 크거나 같을 때
			// 우선 스택에 넣어준다
			while (curNum <= target) {
				nums.addLast(curNum++);
				sb.append("+").append("\n");
			}

			// 스택의 맨 위가 현재 숫자랑 같으면 빼준다
			if (target == nums.peekLast()) {
				nums.removeLast();
				sb.append("-").append("\n");
			} 
			// 현재 스택 맨 위에 타겟 숫자가 없다면
			// 타겟보다 큰 숫자가 나온 후에야 나올 수 있기에 불가능 정지
			else {
				break;
			}
		}

		// 스택을 전부 꺼냈다면 완료
		// 못 꺼냈다면 타겟이 막힘 NO
		if (nums.isEmpty()) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}

	}

}
