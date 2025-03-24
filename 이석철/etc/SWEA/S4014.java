package SWEA;

/**
 * SWEA 4014 활주로 건설
 * 1. 슬라이딩 윈도우
 * 2. 윈도우 안에 있는 값 v1, v2 (왼, 오)의 차이가 1보다 크다 -> false
 * 3. 여유 공간이 없다? - false (여유 공간이 있다는 뜻은, v2 값인 애가 X 길이만큼 있따)
 * 4. 범위 넘어갔다? -> false
 * 5. 이미 놓아진 곳이다 -> false
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4014 {
    static int N;
    static int X;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            grid = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = 0;
            for (int i = 0; i < N; i++) {
                if (canBuild(i))
                    ans++;
                if (canBuild2(i))
                    ans++;
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean canBuild(int x) {
        boolean[] isBuild = new boolean[N];

        for (int y = 0; y < N - 1; y++) {
            int v1 = grid[x][y];
            int v2 = grid[x][y + 1];
            if (Math.abs(v1 - v2) > 1)
                return false;

            if (v1 > v2) {
                for (int i = 1; i <= X; i++) {
                    if (y + i >= N) // 범위 벗어났으면.
                        return false;
                    if (v2 != grid[x][y + i]) // 여유공간 체크하는데 값이 다르다?
                        return false;
                    if (isBuild[y + i]) // 이미 놓아졌따? 나가
                        return false;
                }

                for (int i = 1; i <= X; i++) {
                    isBuild[y + i] = true;
                }
            } else if (v1 < v2) {
                for (int i = 1; i < X; i++) {
                    if (y - i < 0)
                        return false;
                    if (v1 != grid[x][y - i])
                        return false;
                    if (isBuild[y - i])
                        return false;
                }

                for (int i = 0; i < X; i++) {
                    isBuild[y - i] = true;
                }
            }
        }

        return true;
    }

    public static boolean canBuild2(int y) {
        boolean[] isBuild = new boolean[N];

        for (int x = 0; x < N - 1; x++) {
            int v1 = grid[x][y];
            int v2 = grid[x + 1][y];
            if (Math.abs(v1 - v2) > 1)
                return false;

            if (v1 > v2) {
                for (int i = 1; i <= X; i++) {
                    if (x + i >= N)
                        return false;
                    if (v2 != grid[x + i][y])
                        return false;
                    if (isBuild[x + i])
                        return false;
                }

                for (int i = 1; i <= X; i++) {
                    isBuild[x + i] = true;
                }
            } else if (v1 < v2) {
                for (int i = 1; i < X; i++) {
                    if (x - i < 0)
                        return false;
                    if (v1 != grid[x - i][y])
                        return false;
                    if (isBuild[x - i])
                        return false;
                }

                for (int i = 0; i < X; i++) {
                    isBuild[x - i] = true;
                }
            }
        }

        return true;
    }
}
