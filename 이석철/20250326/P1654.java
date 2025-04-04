
/** 
 * 랜선 자르기 
 * 17348kb
 * 160ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1654 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int amount = Integer.parseInt(st.nextToken());

        int[] line = new int[n];

        long right = 0;
        for (int i = 0; i < n; i++) {
            int ret = Integer.parseInt(br.readLine());
            if (ret > right)
                right = ret;
            line[i] = ret;
        }

        right++; // 반례: right = 1이면 left는 항상 0이니까 (0 + 1) / 2 = 0으로 ZeroDivision 런타임 에러 발생 가능
        long left = 0;
        long mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            long count = 0;
            for (int i = 0; i < n; i++) {
                count += (line[i] / mid);
            }

            if (count < amount)
                right = mid;
            else
                left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
