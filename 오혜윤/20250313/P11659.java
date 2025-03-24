// [실버3] 11659번. 구간 합 구하기 4
// 메모리 : 56576 KB, 시간 : 484 ms

import java.io.*;
import java.util.*;

public class P11659 {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수 N
        int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수 M

        int[] prefix = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        prefix[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            prefix[i] = Integer.parseInt(st.nextToken()) + prefix[i - 1];
        }

        // i부터 j까지의 합 구하기
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(prefix[j] - prefix[i - 1]).append("\n");
        }
        System.out.println(sb);
    }
}