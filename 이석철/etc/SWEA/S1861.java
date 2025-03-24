package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * S1861 정사각형 방
 * BFS
 */
public class S1861 {
    static int n;
    static int[][] grid;
    static ArrayDeque<Point> que;
    static final int[] dxs = { 1, 0, -1, 0 };
    static final int[] dys = { 0, 1, 0, -1 };

    static class Point {
        int x;
        int y;
        int v;

        Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int minRoomNumber = n * n + 1;
            int maxDistance = 1;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    que = new ArrayDeque<>();
                    que.offer(new Point(x, y, grid[x][y]));
                    int startRoomNumber = grid[x][y];
                    int dist = 1;
                    while (!que.isEmpty()) {
                        Point p = que.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = p.x + dxs[d];
                            int ny = p.y + dys[d];

                            if (isRange(nx, ny) && (grid[nx][ny] == p.v + 1)) {
                                que.offer(new Point(nx, ny, grid[nx][ny]));
                                dist++;
                                break; // 어차피 한 방향만 정답임 숫자가 다 독립적이라
                            }
                        }
                    }

                    if (dist > maxDistance) {
                        maxDistance = dist;
                        minRoomNumber = startRoomNumber;
                    } else if (dist == maxDistance && minRoomNumber > startRoomNumber) {
                        minRoomNumber = startRoomNumber;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(minRoomNumber).append(" ").append(maxDistance).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
