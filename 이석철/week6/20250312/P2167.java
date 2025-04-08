
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P2167
 */
public class P2167 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n + 1][m + 1];
        
        // 누적합 사용
        for (int i = 1; i <= n; i++){
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++){
                sum += Integer.parseInt(st.nextToken());
                grid[i][j] = sum;
            }
        }

        int k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int x = sx; x <= ex; x++){    
                sum += (grid[x][ey] - grid[x][sy - 1]);
            }

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}