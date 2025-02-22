/**
 * 보물섬 전략
 * 
 * 이차원 배열 완탐으로 진행
 * 모든 땅에서 BFS를 진행하고, 최대값을 갱신해 나감 (최대값이 보물이 가장 멀리 떨어져 있는 거리)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2589 {
    static int n;
    static int m;
    static char[][] grid;
    static final int[] dxs = {1, 0, -1, 0};
    static final int[] dys = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        grid = new char[n][m];
        for (int i = 0; i < n; i++){
            String inputString = br.readLine();
            for (int j = 0; j < m; j++){
                grid[i][j] = inputString.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j] == 'L'){
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }
        // System.out.println(bfs(3, 0));
        System.out.println(answer);
    }

    public static int bfs(int x, int y){
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int[] start = {x, y, 0};
        que.offer(start);
        boolean[][] vis = new boolean[n][m];
        vis[x][y] = true;
        
        int distance = 0;
        while (!que.isEmpty()){
            int[] temp = que.poll();
            x = temp[0];
            y = temp[1];
            int dist = temp[2];
            for (int d = 0; d < 4; d++){
                int nx = x + dxs[d];
                int ny = y + dys[d];

                if (isRange(nx, ny) && !vis[nx][ny] && grid[nx][ny] == 'L'){
                    vis[nx][ny] = true;
                    int[] temp2 = {nx, ny, dist + 1};
                    que.offer(temp2);
                    distance = Math.max(distance, dist + 1);
                }
            }
        }

        return distance;
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
