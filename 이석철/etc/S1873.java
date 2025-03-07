
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상호의 배틀 필드
 * 걍 뭐 구현
 */

public class S1873 {
    static int n;
    static int m;
    static char[][] grid;
    static int x;
    static int y;
    static int dir;
    static final int[] dxs = {-1, 1, 0, 0};
    static final int[] dys = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            grid = new char[n][m];
            for (int i = 0; i < n; i++){
                String inputString = br.readLine();
                for (int j = 0; j < m; j++){
                    char ret = inputString.charAt(j);
                    if (ret == '<' || ret == 'v' || ret == '^' || ret == '>'){
                        x = i; 
                        y = j;
                        if (ret == '^')
                            dir = 0;
                        else if (ret == 'v')
                            dir = 1;
                        else if (ret == '<')
                            dir = 2;
                        else
                            dir = 3;
                    }       
                    grid[i][j] = ret;
                }
            }
            
            int inputCount = Integer.parseInt(br.readLine());
            String command = br.readLine();

            for (int i = 0; i < inputCount; i++){
                char c = command.charAt(i);
                
                if (c == 'U'){
                    grid[x][y] = '^';
                    dir = 0;
                    if (isRange(x + dxs[0], y + dys[0]) && canMove(0))
                        move(0);
                }
                if (c == 'D'){
                    grid[x][y] = 'v';
                    dir = 1;
                    if (isRange(x + dxs[1], y + dys[1]) && canMove(1))
                        move(1);
                }
                if (c == 'L'){
                    grid[x][y] = '<';
                    dir = 2;
                    if (isRange(x + dxs[2], y + dys[2]) && canMove(2))
                        move(2);
                }
                if (c == 'R'){
                    grid[x][y] = '>';
                    dir = 3;
                    if (isRange(x + dxs[3], y + dys[3]) && canMove(3))
                        move(3);
                }
                else if (c == 'S')
                    shoot();
                
            }
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    sb.append(grid[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void shoot(){
        int tx = x;
        int ty = y;
        while (true){
            int nx = tx + dxs[dir];
            int ny = ty + dys[dir];

            if (!isRange(nx, ny))
                return;
            
            if (grid[nx][ny] == '*'){
                grid[nx][ny] = '.';
                return;
            }

            if (grid[nx][ny] == '#'){
                return;
            }
            tx = nx;
            ty = ny;
        }
    }
    
    public static void move(int d){
        grid[x][y] = '.';
        x += dxs[d];
        y += dys[d];
        
        if (d == 0)
            grid[x][y] = '^';
        else if (d == 1)
            grid[x][y] = 'v';
        else if (d == 2)
            grid[x][y] = '<';
        else if (d == 3)
            grid[x][y] = '>';
    }

    public static boolean canMove(int dir){
        return grid[x + dxs[dir]][y + dys[dir]] == '.';
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }    
}
// 2
// 3 7
// ***....
// *-..#**
// #<.****
// 23
// SURSSSSUSLSRSSSURRDSRDS
// 3 7
// ***....
// *-..#**
// #<.****
// 23
// SURSSSSUSLSRSSSURRDSRDS