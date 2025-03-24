// [실버5] 2167번. 2차원 배열의 합

import java.io.*;
import java.util.*;

public class P2167 {
    public static void main(String[] args) throws Exception {
        Solution1(); // 누적합 사용 X, 메모리 : 46068 KB, 시간 : 1672 ms
        Solution2(); // 누적합 사용 O, 메모리 : 25152 KB, 시간 : 240 ms
    }

    // Solution1. 누적합 사용 X
    // 메모리 : 46068 KB, 시간 : 1672 ms
    static void Solution1() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int sum = 0;
            for (int r = i; r <= x; r++) {
                for (int c = j; c <= y; c++) {
                    sum += arr[r][c];
                }
            }
            sb.append(sum).append(k != (K - 1) ? "\n" : "");
        }
        System.out.println(sb);
    }

    // Solution2. 누적합 사용 O
    // 메모리 : 25152 KB, 시간 : 240 ms
    static void Solution2() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] prefix = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(st.nextToken());
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + num;
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int sum = prefix[x][y] - prefix[i][y] - prefix[x][j] + prefix[i][j];
            sb.append(sum).append(k != (K - 1) ? "\n" : "");
        }
        System.out.println(sb);
    }
}