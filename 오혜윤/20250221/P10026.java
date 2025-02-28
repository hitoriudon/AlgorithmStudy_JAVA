// [골드5] 10026번. 적록색약
// 메모리 : 12972 KB, 시간 : 80 ms

import java.io.*;
import java.util.*;

class P10026 {

    static int N;
    static int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] painting1 = new int[N][N];    // 적록색약이 아닌 사람
        int[][] painting2 = new int[N][N];    // 적록색약인 사람
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (arr[j] == 'R') {
                    painting1[i][j] = 1;
                    painting2[i][j] = 1;
                }

                else if (arr[j] == 'G') {
                    painting1[i][j] = 2;
                    painting2[i][j] = 1;
                }

                else if (arr[j] == 'B') {
                    painting1[i][j] = 3;
                    painting2[i][j] = 3;
                }
            }
        }

        System.out.println(getSection(painting1) + " " + getSection(painting2));
    }

    static int getSection(int[][] painting) {
        boolean[][] visited = new boolean[N][N];
        int sections = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    sections++;
                    BFS(painting, visited, i, j);
                }
            }
        }
        return sections;
    }
    
    static void BFS(int[][] painting, boolean[][] visited, int x, int y) {
        int color = painting[x][y];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { x, y });
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];
            
            for (int[] d : dir) {
                int nx = cx + d[0];
                int ny = cy + d[1];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && painting[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }
    }
    
}