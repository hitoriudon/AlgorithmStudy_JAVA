import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class P1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int maxRC = Math.min(N, M);
        int[][] arr = new int[N][M];
        int answer = 1;

        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                arr[i][j] = row[j] - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = answer; k < maxRC; k++) {
                    if(i+k < N && j+k < M && arr[i][j] == arr[i][j+k] && arr[i][j] == arr[i][j+k] && arr[i][j+k] == arr[i+k][j] && arr[i+k][j] == arr[i+k][j+k]) {
                        answer = k+1;
                    }
                }
            }
        }

        System.out.println(answer * answer);

    }
}