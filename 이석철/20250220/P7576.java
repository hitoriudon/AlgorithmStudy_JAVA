import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P7576 {
    static int n;
    static int m;
    static int[][] box;
    static boolean[][] vis;
    static final int[] dxs = { -1, 0, 1, 0 };
    static final int[] dys = { 0, 1, 0, -1 };

    // 연습: Point Class로 관리하기
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

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        vis = new boolean[n][m];

        ArrayDeque<Point> dq = new ArrayDeque<>(); // 연습. innerClass Point 생성해서 넣기

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int ele = Integer.parseInt(st.nextToken());
                box[i][j] = ele;

                if (ele == 1) {
                    Point p = new Point(i, j, ele);
                    dq.offer(p);
                    vis[i][j] = true;
                }
            }
        }

        // bfs
        while (dq.size() > 0) {
            Point p = dq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dxs[d];
                int ny = p.y + dys[d];

                if (isRange(nx, ny) && box[nx][ny] == 0 && !vis[nx][ny]) {
                    Point np = new Point(nx, ny, p.v + 1);
                    box[nx][ny] = p.v + 1;
                    vis[nx][ny] = true;
                    dq.offer(np);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, box[i][j]); // 박스 내 최대값이 걸린 시간 + 1 값이다. (출력할 때 빼주면 됨)
            }
        }

        boolean isAllTomatosDone = true;
        out: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && box[i][j] == 0) { // 방문하지 않았는데 박스 값이 0이면(벽도 아니고 익은 것도 아니면), 다 익은 게 아님
                    isAllTomatosDone = false;
                    break out;
                }
            }
        }

        answer = isAllTomatosDone ? answer - 1 : -1;
        System.out.println(answer);
    }
}
