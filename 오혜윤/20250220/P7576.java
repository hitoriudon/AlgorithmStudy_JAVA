// [골드5] 7576번. 토마토
// 메모리 : 109104 KB, 시간 : 504 ms

import java.io.*;
import java.util.*;

class P7576 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 수
        int M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 수
        
        int[][] box = new int[M][N];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());
                box[i][j] = info;
                if (info == 1)
                    q.add(new int[] { i, j, 0 });
            }
        }

        int day = 0;
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!q.isEmpty()) {
            int[] tmt = q.poll();
            int x = tmt[0], y = tmt[1];
            day = tmt[2];

            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && box[nx][ny] == 0) {
                    box[nx][ny] = 1;
                    q.offer(new int[] { nx, ny, day + 1 });
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day);

    }

}