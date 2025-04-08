
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1987 {
    static boolean[] isUsed = new boolean[26];
    static int[][] board;
    static int r;
    static int c;
    static final int[] dxs = {1, 0, -1, 0};
    static final int[] dys = {0, 1, 0, -1};
    static int answer = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        
        for (int i = 0; i < r; i++){
            String inputString = br.readLine();
            for (int j = 0; j < c; j++){
                int ret = (int)inputString.charAt(j) - 'A';
                board[i][j] = ret;
            }
        }
        
        isUsed[board[0][0]] = true;
        dfs(0, 0, 1);
        
        System.out.println(answer);
    }
    
    public static void dfs(int x, int y, int dist){
        answer = Math.max(answer, dist);

        for (int dir = 0; dir < 4; dir++){
            int nx = x + dxs[dir];
            int ny = y + dys[dir];
            
            if (isRange(nx, ny)){
                int alphabet = board[nx][ny];
                if (!isUsed[alphabet]){
                    isUsed[alphabet] = true;
                    dfs(nx, ny, dist + 1);
                    isUsed[alphabet] = false; // backtrack
                }
            } 
        }
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < r && y < c;
    }
}
