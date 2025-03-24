import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2579 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numAmount = Integer.parseInt(br.readLine());
		
		int[] nums = new int[numAmount + 1];
		int[] dp = new int[numAmount + 1];
		

        for (int numCount = 1; numCount <= numAmount; numCount++) {
        	nums[numCount] = Integer.parseInt(br.readLine());
        }
        
        // 계단이 한계인 경우 초기화에서 아웃오보 바운드가 생김
        if(numAmount == 1) {
        	System.out.println(nums[1]);
        	return;
        }
		
		dp[1] = nums[1];
		dp[2] = nums[1] + nums[2];
		
		for (int numCount = 3; numCount <= numAmount; numCount++) {
			// 한 칸 전에서 올라온 것이 좋은가 or 두 칸 전에서 올라온 것이 좋은가 비교
			// 연속 3칸은 불가능하니 한 칸 전은 무조건 그 전에 2칸을 이동했다 생각하여 dp[numCount - 3]을 이용
			dp[numCount] = Math.max(dp[numCount - 3] + nums[numCount - 1], dp[numCount - 2]) + nums[numCount];
        }
		
		System.out.println(dp[numAmount]);
	}
}
