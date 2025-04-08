
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n + 1];
        int sum = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++){
            sum += Integer.parseInt(st.nextToken());
            numbers[i] = sum;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int prefixSum = numbers[end] - numbers[start - 1];
            sb.append(prefixSum).append("\n");
        }
        
        System.out.println(sb);
    }
}
