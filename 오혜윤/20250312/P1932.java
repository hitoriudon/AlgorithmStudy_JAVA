// [실버1] 1932번. 정수 삼각형
// 메모리 : 23180 KB, 시간 : 200 ms

import java.io.*;
import java.util.*;

public class P1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] now = new int[n];
        int[] prefix = new int[n];
        prefix[0] = Integer.parseInt(br.readLine());

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            now[0] = prefix[0] + Integer.parseInt(st.nextToken());
            for (int j = 1; j < i; j++) {
                now[j] = Integer.parseInt(st.nextToken()) + Math.max(prefix[j], prefix[j - 1]);
            }
            now[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());

            int[] temp = prefix;
            prefix = now;
            now = temp;
        }

        int result = 0;
        for (int p : prefix) {
            result = Math.max(p, result);
        }
        System.out.println(result);
    }
}
