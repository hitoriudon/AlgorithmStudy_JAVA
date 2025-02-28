// [골드5] 2589번. 보물섬
// 메모리 : 218864 KB, 시간 : 412 ms

import java.io.*;
import java.util.*;

public class P2589 {

    static int R, C;
    static char[][] maps;
    static int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maps = new char[R][C];
        for (int i = 0; i < R; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] == 'L') result = Math.max(result, getMinTime(i, j));
            }
        }
        
        System.out.println(result);
    }

    // 이동하는 데에 걸리는 최단 시간 구하기
    static int getMinTime(int sx, int sy) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { sx, sy, 0 });
        visited[sx][sy] = true;
        int result = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], time = now[2];
            result = Math.max(result, time);

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && maps[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.offer(new int[] { nx, ny, time + 1 });
                }
            }
        }
        return result;
    }

}
