import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            triangle[i] = new int[i + 1]; // 초기화 해주기 크기
            for(int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //bottom up 아래에서 올라오면서 큰거 골라주기 권법!
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                // triangle[i+1][i] 아래 왼쪽
                // triangle[i+1][i+1] 아래 오른쪽
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        System.out.println(triangle[0][0]);
    }
}
