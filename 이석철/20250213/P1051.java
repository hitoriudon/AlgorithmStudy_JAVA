import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1051 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] rectangle = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = (int) s.charAt(j) - '0';
                rectangle[i][j] = num;
            }
        }

        int maxLength = Math.min(n, m); // 가로 세로 중 작은 값이 정사각형의 최대 길이
        int maxSquare = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int startPointValue = rectangle[i][j];

                for (int k = 0; k < maxLength; k++) { // 정사각형 한 변의 길이 k
                    if ((i + k) < n && (j + k) < m) { // 배열 인덱스 벗어나면 ㄴㄴ해
                        if (startPointValue == rectangle[i][j + k] && startPointValue == rectangle[i + k][j]
                                && startPointValue == rectangle[i + k][j + k]) {
                            maxSquare = Math.max(maxSquare, (k + 1) * (k + 1)); // 최대 넓이 갱신
                        }
                    }
                }
            }
        }
        System.out.println(maxSquare);
    }
}