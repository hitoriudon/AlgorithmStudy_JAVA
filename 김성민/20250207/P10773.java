import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P10773 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> nums = new ArrayList<Integer>();

		int orderAmount = Integer.parseInt(br.readLine());
		while (orderAmount-- != 0) {
			int num = Integer.parseInt(br.readLine());
			// 0이면 마지막에 받은 요소 제거
			if (num == 0) {
				nums.remove(nums.size() - 1);
			} 
			// 0이 아니면 add로 마지막에 요소 추가
			else {
				nums.add(num);
			}
		}
		
		// 요소 합 구하기
		int sum = 0;
		for(int num : nums) {
			sum += num;
		}
		System.out.println(sum);
	}

}
