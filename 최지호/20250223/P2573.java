import java.io.*;
import java.util.*;

public class P2573 {
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] map;
    static int n, m;
    static int[][] newMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        // 여기서 돌면서 기존 map에서 newMap을 빼줌 빼준뒤 두 덩어리로 분리되는지 확인 하고 안되면 count++ 만일 다 녹을때까지 분리가
        // 안되면 0을 출력하기
        while (true) {
            newMap = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) {
                        bfsWarm(i, j);
                    }
                }
            }

            int iceCount = 0;
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j, visited);
                        iceCount++;
                    }
                }
            }

            if (iceCount >= 2) {
                System.out.println(count);
                return;
            }

            // 모든 얼음이 녹았는지 안녹았는지 확인
            int totalIce = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    totalIce += map[i][j];
                }
            }

            if (totalIce == 0) {
                System.out.println(0);
                return;
            }

            // map 업데이트해주기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] -= newMap[i][j];
                    if (map[i][j] < 0) {
                        map[i][j] = 0;
                    }
                }
            }
            count++;
        }
    }

    // 얼음 녹이기
    private static void bfsWarm(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (map[nx][ny] == 0) {
                    newMap[i][j] += 1;
                }
            }
        }
    }

    // 덩어리 확인
    private static void bfs(int i, int j, boolean visited[][]) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { i, j });
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && map[nx][ny] != 0) {
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
    }
}