
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] table = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            
            for (int j = 1; j <= n; j++){
                sum += Integer.parseInt(st.nextToken());
                table[i][j] = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            
            int ans = 0;
            for (int x = sx; x <= ex; x++){
                ans += table[x][ey] - table[x][sy - 1];
            }
            
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }    
}
