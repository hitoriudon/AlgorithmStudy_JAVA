// [골드4] 1987번. 알파벳
// 메모리 : 12452 KB, 시간 : 912 ms

import java.io.*;
import java.util.*;

public class P1987 {

    static int R, C, result;
    static char[][] board;
    static boolean[] visited;
    static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        result = 0;
        visited = new boolean[26]; // 알파벳의 개수 26개
        visited[board[0][0] - 'A'] = true;
        DFS(0, 0, 1);

        System.out.println(result);
    }

    static void DFS(int r, int c, int step) {
        result = Math.max(result, step);

        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (rangeCheck(nr, nc) && !visited[board[nr][nc] - 65]) {
                visited[board[nr][nc] - 'A'] = true;
                DFS(nr, nc, step + 1);
                visited[board[nr][nc] - 'A'] = false;
            }
        }
    }

    static boolean rangeCheck(int r, int c) {
        return (r >= 0 && r < R && c >= 0 && c < C);
    }
}