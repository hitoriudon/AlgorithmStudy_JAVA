import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P3109 빵집 전략:
 * 왼쪽 열 시작점부터 DFS
 * 우선순위: 대각선 위, 오른쪽, 대각선 아래 (위로 놔야 나중에 오는 파이프라인이 놓을 기회가 더 많아짐)
 */
public class P3109 {
    static int r;
    static int c;
    static char[][] grid;
    static boolean[][] vis;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        grid = new char[r][c];

        for (int i = 0; i < r; i++){
            String inputString = br.readLine();
            for (int j = 0; j < c; j++){
                grid[i][j] = inputString.charAt(j);
            }
        }
        vis = new boolean[r][c];
        for (int i = 0; i < r; i++){
            vis[i][0] = true;
            dfs(i, 0);
            
        }
        System.out.println(answer);
    }

    public static boolean dfs(int x, int y){
        vis[x][y] = true; //
        if (y == c - 1){ // 종료 조건
            answer++;
            return true;
        }
        
        if (isRange(x-1, y+1) && !vis[x-1][y+1] && grid[x-1][y+1] == '.'){
            if (dfs(x-1, y+1))
                return true;
            
        }
        if (isRange(x, y+1) && !vis[x][y+1] && grid[x][y+1] == '.'){
            if (dfs(x, y+1))
                return true;
        }
        if (isRange(x+1, y+1) && !vis[x+1][y+1] && grid[x+1][y+1] == '.'){
            if (dfs(x+1, y+1))
                return true;
        }    

        // 이 코드까지 왔다면 갈 수 있는 방향이 없다는 것
        return false;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < r && y < c;
    }
}