package BOJ.practice_LIS;

// 14492kb, 128ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가장 긴 증가하는 부분 수열, S2
 * n이 최대 1000까지라서, O(n^2) 가능
 * 
 * dp[i]:= i번째 원소까지 봤을 때, i를 마지막 원소로 하는 LIS 길이.
 * i번째 숫자 앞에 있는 숫자들(j) 중에서 나보다 작은 숫자가 있다면,
 * 그 숫자를 마지막으로 하는 LIS 길이에 나를 추가할 수 있으니까,
 * 그 중 최대를 갱신하는 것
 */

public class P11053 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
