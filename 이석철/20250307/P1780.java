import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
    static int n;
    static int[][] paper;
    static int[] numbers = { 0, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cut(0, 0, n);
        for (int i = 0; i < 3; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static void cut(int x, int y, int len) {
        if (checkColor(x, y, len)) {
            numbers[paper[x][y] + 1]++; // -1 + 1 = 0, 0 + 1 = 1, 1 + 1 = 2
            return;
        }

        int newLen = len / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cut(x + newLen * i, y + newLen * j, newLen);
            }
        }
    }

    public static boolean checkColor(int x, int y, int len) {
        int p = paper[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (paper[i][j] != p)
                    return false;
            }
        }

        return true;
    }
}