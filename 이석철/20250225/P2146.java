
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class P2146 {
    static int n;
    static int[][] world;
    static boolean[][] vis;
    static final int[] dxs = {1, 0, -1, 0};
    static final int[] dys = {0, 1, 0, -1};
    static int answer;
    static int[][] grid;

    static class Point {
        int x; int y; int v; // value(distance)

        Point(int x, int y, int v){
            this.x = x; this.y = y; this.v = v;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        world = new int[n][n];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                world[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 땅에 숫자 마킹(영역 구분 짓기)
        setLandNumber(); 

        answer = Integer.MAX_VALUE;
        // 땅인 지점들 다리 지을 수 있는지 체크하고, 지을 수 있으면 최단 거리 bfs 실행
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (world[i][j] != 0 && canBuildBridge(i, j)){
                    calcBridgeDistance(i, j, world[i][j]);
                    // answer = Math.min(answer, calcBridgeDistance(i, j, world[i][j]));
                }
            }
        }
        System.out.println(answer);

    }

    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    public static void setLandNumber(){
        vis = new boolean[n][n];
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int landNumber = 1;
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                if (world[x][y] == 1 && !vis[x][y]){
                    vis[x][y] = true;
                    int[] point = {x, y};
                    world[x][y] = landNumber;
                    que.offer(point);

                    // bfs
                    while (!que.isEmpty()){
                        point = que.poll();

                        for (int d = 0; d < 4; d++){
                            int nx = point[0] + dxs[d];
                            int ny = point[1] + dys[d];
                            
                            if (isRange(nx, ny) && world[nx][ny] == 1 && !vis[nx][ny]){
                                vis[nx][ny] = true;
                                world[nx][ny] = landNumber;
                                int[] np = {nx, ny};
                                que.offer(np);
                            }
                        }
                    }

                    landNumber++;
                } 
            }
        }
    }

    public static boolean canBuildBridge(int x, int y){ // 물이 있어야 다리를 짓죠
        for (int d = 0; d < 4; d++){
            int nx = x + dxs[d];
            int ny = y + dys[d];
            
            if (isRange(nx, ny) && world[nx][ny] == 0){ // 인접 네 방향에서 물이 있으면 다리 지을 수 있음
                return true;
            }
        }
        return false; // 다리 지을 수 없음
    }

    public static void calcBridgeDistance(int x, int y, int landNumber){
        vis = new boolean[n][n];
        ArrayDeque<Point> que = new ArrayDeque<>();
        
        int bridgeDistance = 0;
        Point p = new Point(x, y, bridgeDistance);
        vis[x][y] = true;
        que.offer(p);
        
        while (!que.isEmpty()){
            p = que.poll();

            if (p.v > answer){
                break;
            }
            
            for (int d = 0; d < 4; d++){
                int nx = p.x + dxs[d];
                int ny = p.y + dys[d];

                if (isRange(nx, ny) && !vis[nx][ny] && world[nx][ny] == 0){ // 방문 안 한 바다면 다리 건설(큐에 넣기)
                    Point np = new Point(nx, ny, p.v + 1);
                    que.offer(np);
                    vis[nx][ny] = true;
                } 
                // 만약 방문 안 했고 나랑 같은 넘버가 아닌 땅이라면 다리 만든 것. 
                // 그리고 이 조건을 처음으로 만족하는 좌표가 최단 거리가 된다(너비 우선 탐색 특징)
                else if (isRange(nx, ny) && !vis[nx][ny] && world[nx][ny] != landNumber && world[nx][ny] != 0){
                    answer = Math.min(answer, p.v);
                }
            }
        }
    }
}
// 10
// 1 1 1 0 0 0 0 1 1 1
// 1 1 1 1 0 0 0 0 1 1
// 1 0 1 1 0 0 0 0 1 1
// 0 0 1 1 1 0 0 0 0 1
// 0 0 0 1 0 0 0 0 0 1
// 0 0 0 0 0 0 0 0 0 1
// 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 1 1 0 0 0 0
// 0 0 0 0 1 1 1 0 0 0
// 0 0 0 0 0 0 0 0 0 0