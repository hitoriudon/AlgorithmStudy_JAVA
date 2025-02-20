// [실버1] 2615번. 오목
// 메모리 : 11772 KB, 시간 : 68 ms

import java.io.*;
import java.util.*;

class P2615 {

    static int[][] board = new int[19][19];

    // 가로, 세로, 대각선(↘), 대각선(↗)
    static int[] dr = {0, 1, 1, -1};
    static int[] dc = {1, 0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 승리 여부 체크
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0 && isWin(i, j)) {
                    System.out.println(board[i][j]);
                    System.out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }

        // 승리하는 경우 X
        System.out.println(0);
    }

    // 0 (알이 놓이지 않은 자리), 1 (검은 바둑알), 2 (흰 바둑알)
    static boolean isWin(int r, int c) {
        int stone = board[r][c];

        for (int d = 0; d < 4; d++) {
            // 이전 방향의 돌이 같은 돌이면 시작점이 아님
            int prevR = r - dr[d];
            int prevC = c - dc[d];
            if (isValid(prevR, prevC) && board[prevR][prevC] == stone) {
                continue;
            }

            int cnt = 1;
            int nr = r + dr[d];
            int nc = c + dc[d];

            while (isValid(nr, nc) && board[nr][nc] == stone) {
                cnt++;
                nr += dr[d];
                nc += dc[d];
            }

            // 5개인지 확인 (6개 이상이면 무효)
            if (cnt == 5) {
                int nextR = r + dr[d] * 5;
                int nextC = c + dc[d] * 5;
                if (isValid(nextR, nextC) && board[nextR][nextC] == stone) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < 19 && c >= 0 && c < 19;
    }
}