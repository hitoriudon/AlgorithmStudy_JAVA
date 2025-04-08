
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2512 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int totalCountryBudget = Integer.parseInt(br.readLine());

        long totalRequest = 0;
        long max = 0;

        int[] request = new int[n];
        for (int i = 0; i < n; i++){
            int ret = Integer.parseInt(st.nextToken());
            if (max < ret) 
                max = ret;
            
            request[i] = ret;
            totalRequest += (long)ret;
        }
        
        if (totalRequest <= totalCountryBudget){
            System.out.println(max);
            return;
        }

        long min = 0;
        long mid = 0;

        while (min < max){
            mid = (min + max) / 2;

            long currentBudget = 0;
            for (int i = 0; i < n; i++){
                currentBudget += (request[i] > mid ? mid : request[i]);
            }

            if (currentBudget > totalCountryBudget)
                max = mid;
            else 
                min = mid + 1;
        }

        System.out.println(min - 1);
    }
}
