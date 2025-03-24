// [실버3] 11441번. 합 구하기
// 메모리 : 56888 KB, 시간 : 428 ms

import java.io.*;
import java.util.*;

public class P11441 {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] prefix = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        prefix[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(prefix[j] - prefix[i - 1]).append("\n");
        }
        System.out.println(sb);
    }
}