// [실버2] 2630번. 색종이 만들기
// 메모리 : 13080 KB, 시간 : 96 ms

import java.io.*;
import java.util.*;

public class P2630 {

    static int[][] board;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cntColors(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void cntColors(int x, int y, int len) {
        // start ~ len 구역이 모두 같은 색인 경우
        if (chkSameColor(x, y, len)) {
            if (board[x][y] == 1)
                blue++;
            else
                white++;
        }

        // 다른 색이 섞인 경우
        else {
            int mid = len / 2;
            cntColors(x, y + mid, mid); // 1사분면
            cntColors(x, y, mid); // 2사분면
            cntColors(x + mid, y, mid); // 3사분면
            cntColors(x + mid, y + mid, mid); // 4사분면
        }
    }

    // 해당 구역이 모두 같은 색으로 되어 있는지 확인
    static boolean chkSameColor(int x, int y, int len) {
        int now = board[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (board[i][j] != now)
                    return false;
            }
        }
        return true;
    }
}
