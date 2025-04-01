
/** 
 * 나무 자르기
 * 119408kb 
 * 476ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2805 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());

        int[] tree = new int[n];

        int right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int ret = Integer.parseInt(st.nextToken());
            if (ret > right)
                right = ret;

            tree[i] = ret;
        }

        int left = 0;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += (tree[i] - mid > 0 ? tree[i] - mid : 0);
            }

            if (sum < len)
                right = mid;
            else
                left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
