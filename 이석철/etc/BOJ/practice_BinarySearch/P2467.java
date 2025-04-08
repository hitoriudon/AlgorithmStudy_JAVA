import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2467 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;

        if (arr[left] >= 0){
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }

        if (arr[right] <= 0){
            System.out.println(arr[n - 2] + " " + arr[n - 1]);
            return;
        }

        long near = Math.abs(arr[right]) + Math.abs(arr[left]);
        int nearX = 0;
        int nearY = 0;
        while (left < right){
            long zeroDiff = Math.abs(arr[left] + arr[right]);
            
            if (near >= zeroDiff){
                near = zeroDiff;
                nearX = left;
                nearY = right;
            }

            if (arr[left] + arr[right] > 0) right -= 1;
            else left += 1;
        }

        System.out.println(arr[nearX] + " " + arr[nearY]);
    }
}
