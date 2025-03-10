// [골드4] 2573번. 빙산
// 메모리 : 162352 KB, 시간 : 520 ms

import java.io.*;
import java.util.*;

public class P2573 {

    static int N, M, result;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            result = 0;
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j])
                        cntIceberg(i, j); // 빙산 덩어리 확인
                }
            }

            // 빙산이 두 덩어리 이상으로 분리된 경우
            if (result >= 2) {
                System.out.println(year);
                return;
            }

            // 빙산이 다 녹을 때까지 분리되지 않은 경우
            if (isEnd()) {
                System.out.println(0);
                return;
            }

            meltIceberg(); // 빙산 녹기
            year++;
        }
    }

    // 빙산 덩어리 개수 count
    static void cntIceberg(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { r, c });
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (rangeChk(nx, ny) && (map[nx][ny] != 0) && !visited[nx][ny]) {
                    q.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        result++;
    }

    // 빙산 녹기
    static void meltIceberg() {
        int[][] tmp = new int[N][M];

        for (int r = 1; r < N - 1; r++) {
            for (int c = 1; c < M - 1; c++) {
                if (map[r][c] != 0) {
                    int cnt = 0;
                    for (int[] d : dir) {
                        int nr = r + d[0];
                        int nc = c + d[1];

                        if (map[nr][nc] == 0)
                            cnt++;
                    }
                    tmp[r][c] = Math.max(map[r][c] - cnt, 0);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            map[i] = tmp[i];
        }
    }

    // 빙산이 모두 녹았는지 확인
    static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    static boolean rangeChk(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < M);
    }
}
