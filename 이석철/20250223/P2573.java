
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2573 {
    static int n;
    static int m;
    static int[][] ice;
    static final int[] dxs = {1, 0, -1, 0};
    static final int[] dys = {0, 1, 0, -1};
    static boolean[][] vis;

    static class Point{
        int x;
        int y;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ice = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0;
        while (calculateIcePartition() == 1 && !isAllMelted()){
            melt();
            time++;
        }
        
        System.out.println(isAllMelted() ? 0 : time);
    }

    public static void melt(){
        int[][] afterMelt = new int[n][m];

        for (int x = 0; x < n; x++){
            for (int y = 0; y < m; y++){
                if (ice[x][y] == 0){
                    afterMelt[x][y] = 0;
                } else if (ice[x][y] > 0){
                    int countWater = 0;
                    for (int d = 0; d < 4; d++){
                        int nx = x + dxs[d];
                        int ny = y + dys[d];

                        if (ice[nx][ny] == 0){
                            countWater++;
                        }
                    }
                    afterMelt[x][y] = ice[x][y] - countWater > 0 ? ice[x][y] - countWater : 0;
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                ice[i][j] = afterMelt[i][j];
            }
        }
    }
    public static int calculateIcePartition(){
        vis = new boolean[n][m];
        int countIcePartition = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (ice[i][j] > 0 && !vis[i][j]){
                    Point p = new Point(i, j);
                    countIcePartition += bfs(p);
                }
            }
        }
        
        return countIcePartition;
    }
    
    public static int bfs(Point p){
        ArrayDeque<Point> que = new ArrayDeque<>();
        
        que.offer(p);
        vis[p.x][p.y] = true;

        while (!que.isEmpty()){
            p = que.poll();
            
            for (int d = 0; d < 4; d++){
                int nx = p.x + dxs[d];
                int ny = p.y + dys[d];

                if (ice[nx][ny] > 0 && !vis[nx][ny]){
                    vis[nx][ny] = true;
                    Point np = new Point(nx, ny);
                    que.offer(np);
                }
            }
        }

        return 1;
    }

    public static boolean isAllMelted(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (ice[i][j] > 0){
                    return false;
                }
            }
        }
        return true;
    }
}
