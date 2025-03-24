// [골드4] 9663번. N-Queen
// 메모리 : 12160 KB, 시간 : 3012 ms

import java.io.*;

public class P9663 {

    static int N, result;
    static boolean[] usedCols, usedDiagonal1, usedDiagonal2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        usedCols = new boolean[N];
        usedDiagonal1 = new boolean[2 * N - 1];
        usedDiagonal2 = new boolean[2 * N - 1];
        result = 0;

        setQueens(0);
        System.out.println(result);
    }

    static void setQueens(int row) {
        if (row == N) { // N개의 퀸 설치 완료
            result++;
            return;
        }

        for (int col = 0; col < N; col++) {
            int tmp1 = row + col;
            int tmp2 = row - col + (N - 1);

            if (!usedCols[col] && !usedDiagonal1[tmp1] && !usedDiagonal2[tmp2]) {
                usedCols[col] = true;
                usedDiagonal1[tmp1] = true;
                usedDiagonal2[tmp2] = true;

                setQueens(row + 1);

                usedCols[col] = false;
                usedDiagonal1[tmp1] = false;
                usedDiagonal2[tmp2] = false;
            }
        }
    }
}