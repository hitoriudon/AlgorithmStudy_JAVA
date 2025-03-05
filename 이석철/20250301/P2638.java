/**
 * 치즈
 * BFS로 풀었음 빙산 문제랑 비슷해보여서
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2638 {
    static int n;
    static int m;
    static final int[] dxs = {0, -1, 0, 1};
    static final int[] dys = {1, 0, -1, 0};
    static int[][] grid;
    static boolean[][] vis;
    
    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        grid = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true){
            vis = new boolean[n][m];
            boolean isAllAir = checkExternalAir(0, 0);
            
            if (isAllAir){
                System.out.println(time);
                System.exit(0);
            }
            
            
            for (int x = 0; x < n; x++){
                for (int y = 0; y < m; y++){
                    if (grid[x][y] == 1){
                        int airCount = 0;
                        for (int dir = 0; dir < 4; dir++){
                            int nx = x + dxs[dir];
                            int ny = y + dys[dir];
                            
                            if (isRange(nx, ny) && grid[nx][ny] == 0 && vis[nx][ny]){
                                airCount++;
                            }
                        }
                        
                        if (airCount >= 2){
                            vis[x][y] = true; // true로만 바꿔놓고 이따 한 번에 1을 0으로 바꾸기
                        }
                    }
                }
            }
        
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    if (grid[i][j] == 1 && vis[i][j])
                        grid[i][j] = 0; 
                }
            }
            
            time++;
        }
    }
    
    public static boolean checkExternalAir(int x, int y){
        ArrayDeque<Point> que = new ArrayDeque<>();
        que.offer(new Point(x, y));
        vis[x][y] = true;
        
        int count = 1;
        while (!que.isEmpty()){
            Point p = que.poll();
            
            for (int dir = 0; dir < 4; dir++){
                int nx = p.x + dxs[dir];
                int ny = p.y + dys[dir];
                
                if (isRange(nx, ny) && grid[nx][ny] == 0 && !vis[nx][ny]){
                    que.offer(new Point(nx, ny));
                    vis[nx][ny] = true;
                    count++;
                }
            }
        }
        
        return count == n * m;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
