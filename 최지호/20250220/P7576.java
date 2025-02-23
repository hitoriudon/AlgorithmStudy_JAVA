import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576 {
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int[][] box;
    static int m;
    static int n;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        box = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) {
                    q.add(new int[] {i,j});
                }
            }
        }
        System.out.println(bfs());
    }


    private static int bfs() {
        int days = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            // isRipe : 익었는지 안익었는지 확인
            boolean isRipe = false;

            for (int i = 0; i < size; i++) {
                int[] now = q.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = now[0] + dx[k];
                    int ny = now[1] + dy[k];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && box[nx][ny] == 0) {
                        box[nx][ny] = box[now[0]][now[1]] + 1;
                        q.offer(new int[]{nx, ny});
                        isRipe = true;
                    }
                }
            }
            //익었으면 날짜 ++
            if (isRipe) {
                days++;
            }
        }

        // 다 익었는지 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    return -1;
                }
            }
        }
        return days;
    }
}
