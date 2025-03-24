// [실버3] 1463번. 1로 만들기
// 메모리 : 15532 KB, 시간 : 92 ms

import java.io.*;

public class P1463 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] result = new int[N + 1];
        result[1] = 0;
        for (int i = 2; i <= N; i++) {
            result[i] = result[i - 1] + 1;
            if (i % 3 == 0)
                result[i] = Math.min(result[i], result[i / 3] + 1);
            if (i % 2 == 0)
                result[i] = Math.min(result[i], result[i / 2] + 1);
        }
        System.out.println(result[N]);
    }
}