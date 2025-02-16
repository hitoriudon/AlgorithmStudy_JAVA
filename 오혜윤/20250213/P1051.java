// [실버3] 1051번. 숫자 정사각형

import java.io.*;
import java.util.*;

public class P1051 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // Index 숫자의 등장 회수 (ex. cnt[1] = 4 : 숫자 1이 square 배열에 4개 있음)
        int[] cnt = new int[10];
        int[][] square = new int[N][M];

        for (int i = 0; i < N; i++) {
            String nums = br.readLine();
            for (int j = 0; j < M; j++) {
                int n = nums.charAt(j) - '0';
                square[i][j] = n;
                cnt[n]++;
            }
        }

        int maxExtent = 1; // 최대 넓이 : 숫자가 적어도 1개씩은 등장 -> 최소한 넓이는 1 이상

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = square[i][j];
                if (cnt[n] < 4)
                    continue;

                int len = Math.max(N, M);
                while (len-- > 0) {
                    if (((i + len) < N) && ((j + len) < M)) {
                        if (n == square[i][j + len] &&
                                n == square[i + len][j] &&
                                n == square[i + len][j + len]) {
                            maxExtent = Math.max(maxExtent, (len + 1) * (len + 1));
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(maxExtent);
    }
}
