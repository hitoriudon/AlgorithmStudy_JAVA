// [실버2] 1654번. 랜선 자르기
// 메모리 : 15464 KB, 시간 : 112 ms

import java.io.*;
import java.util.*;

class P1654 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

        int[] lines = new int[K];
        long maxLen = 0;
        for (int k = 0; k < K; k++) {
            lines[k] = Integer.parseInt(br.readLine());
            maxLen = Math.max(maxLen, lines[k]);
        }

        long start = 1;
        long end = maxLen;
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            for (int line : lines) {
                cnt += (line / mid);
            }

            if (cnt >= N) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }
}