import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2146 {
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] map;
    static int[][] newMap;
    static int[][] distanceMap;
    static int n;
    static int count;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 0은 바다
        // 1은 육지
        newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 2;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }

        // 구역간 최소거리찾기
        distanceMap = new int[n][n];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newMap[i][j] != 0) {
                    answer = Math.min(answer, bfsDis(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfsDis(int i, int j) {
        boolean[][] visitedDist = new boolean[n][n];
        int[][] distanceMap = new int[n][n];

        // 시작점 방문
        visitedDist[i][j] = true;
        distanceMap[i][j] = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { i, j });

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (newMap[nx][ny] == 0 && !visitedDist[nx][ny]) {
                        visitedDist[nx][ny] = true;
                        distanceMap[nx][ny] = distanceMap[x][y] + 1;
                        q.add(new int[] { nx, ny });
                    } else if (newMap[nx][ny] != 0 && newMap[nx][ny] != newMap[i][j]) {
                        return distanceMap[x][y];
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static void bfs(int i, int j) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[i][j] = true;
        q.add(new int[] { i, j });
        newMap[i][j] = count;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] != 0 && !visited[nx][ny]) {
                        newMap[nx][ny] = count;
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
    }
}