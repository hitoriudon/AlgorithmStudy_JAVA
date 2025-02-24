package zho;

import java.io.*;
import java.util.*;

//적록색약
public class P10026 {
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        visited = new boolean[n][n];
        count = 0;
        System.out.print(bfs() + " ");

        visited = new boolean[n][n];
        count = 0;
        System.out.print(eyeBfs());

    }

    private static int bfs() {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    q.add(new int[] { i, j });

                    while (!q.isEmpty()) {
                        int[] now = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = now[0] + dx[k];
                            int ny = now[1] + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]
                                    && map[nx][ny] == map[now[0]][now[1]]) {
                                visited[nx][ny] = true;
                                q.add(new int[] { nx, ny });
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int eyeBfs() {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    q.add(new int[] { i, j });

                    while (!q.isEmpty()) {
                        int[] now = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = now[0] + dx[k];
                            int ny = now[1] + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                                if (isSameColor(map[now[0]][now[1]], map[nx][ny])) {
                                    visited[nx][ny] = true;
                                    q.add(new int[] { nx, ny });
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean isSameColor(char color1, char color2) {
        if ((color1 == 'R' || color1 == 'G') && (color2 == 'R' || color2 == 'G')) {
            return true;
        }
        return color1 == color2;
    }
}
