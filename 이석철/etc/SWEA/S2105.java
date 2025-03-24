package SWEA;

/**
 * SWEA 2105 디저트 카페
 * 4차원 포문 완전 탐색
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2105 {
    static int n;
    static int[][] cafe;
    static final int[] dxs = { 1, 1, -1, -1 }; // 반시계
    static final int[] dys = { -1, 1, 1, -1 };
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            ans = Integer.MIN_VALUE;
            n = Integer.parseInt(br.readLine());

            cafe = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int x = 0; x < n - 2; x++) {
                for (int y = 1; y < n - 1; y++) {
                    int leftDiff = y - 0; // 1
                    int rightDiff = (n - 1) - y; // 2
                    for (int i = 1; i <= leftDiff; i++) {
                        for (int j = 1; j <= rightDiff; j++) {
                            ans = Math.max(ans, findDessert(x, y, new int[] { i, j }));
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int findDessert(int x, int y, int[] mul) { // mul = {2, 1} -> 0: dir 0, 2, 1: dir 1, 3
        int countForDessert = 0;
        boolean[] number = new boolean[101]; // 인덱싱을 값으로 접근하죠

        for (int dir = 0; dir < 4; dir++) {
            for (int i = 1; i <= mul[dir % 2]; i++) {
                x += dxs[dir];
                y += dys[dir];

                if (!isRange(x, y) || number[cafe[x][y]])
                    return -1;

                countForDessert += 1;
                number[cafe[x][y]] = true;
            }
        }

        return countForDessert;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
