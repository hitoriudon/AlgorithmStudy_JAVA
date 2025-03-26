package practice_LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P14002 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n]; // 인풋
        int[] dp = new int[n]; // LIS 길이 저장하는 가짜 경로
        int[] prev = new int[n]; // 어디서 왔는지 경로 추적용 배열

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; // 최소 길이는 처음에 자기 자신
            prev[i] = -1; // 없다
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j; // 추적용
                }
            }
        }

        int maxLen = 0, lastIdx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIdx = i;
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (lastIdx != -1) {
            stack.push(arr[lastIdx]);
            lastIdx = prev[lastIdx];
        }

        System.out.println(maxLen);
        for (int i = 0; i < maxLen; i++)
            System.out.println(stack.pop() + " ");
    }

}