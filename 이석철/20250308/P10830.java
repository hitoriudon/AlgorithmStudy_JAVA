import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행렬 제곱
 * 
 */
public class P10830 {
    static int n;
    static int[][] matrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        matrix = new int[n][n];

        // 처음부터 모둘러를 생각하면 편함
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] answer = func(matrix, b);
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] func(int[][] m, long b){
        if (b == 1L){
            return m;
        }

        int[][] temp = func(m, b / 2);

        temp = mul(temp, temp);

        if (b % 2 == 1L)
            temp = mul(temp, matrix); // 곱셈 문제처럼 한 번 더 곱해줘야 함. 여기선 초기값 행렬이겠네.
        
        return temp;
    }

    public static int[][] mul(int[][] m1, int[][] m2){
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++){
                    temp[i][j] += m1[i][k] * m2[k][j]; // 행렬 곱셈(이거 모르면 못 풂)
                    temp[i][j] %= 1000;
                }
            }
        }
        return temp;
    }
    
}