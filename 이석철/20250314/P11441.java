
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11441 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i < n + 1; i++){
            sum += Integer.parseInt(st.nextToken());
            nums[i] = sum;
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int answer = nums[end] - nums[start - 1];
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }   
}
