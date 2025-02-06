import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실버 4. 11399번 ATM
public class P11399 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 사람의 수
        int[] P = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++){
            P[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(P);
        int result = 0;
        for(int i = 0; i < N; i++) {
            result += (P[i] * (N - i));
        }
        System.out.println(result);
    }
}