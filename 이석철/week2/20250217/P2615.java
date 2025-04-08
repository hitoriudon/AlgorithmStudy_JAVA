import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2615 {
    public static boolean isRange(int x, int y) {
        return x >= 0 && x < 19 && y >= 0 && y < 19;
    }

    public static void main(String[] args) throws Exception {
        /**
         * 완탐. 현재 좌표를 기준으로 오른쪽, 아래, 대각선 위, 대각선 아래 +4개씩 체크
         * 육목이 되면 안 되니까 5개의 양 옆 체크해주기
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] pan = new int[19][19];

        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                pan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dxs = { 0, 1, -1, 1 }; // 우, 하, 우상, 우하
        int[] dys = { 1, 0, 1, 1 };

        boolean isFind = false;
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                if (pan[x][y] != 0) {
                    int value = pan[x][y]; // 1: 검 2: 흰

                    for (int d = 0; d < 4; d++) {
                        int cnt = 1;
                        for (int i = 1; i < 5; i++) {
                            int nx = x + dxs[d] * i;
                            int ny = y + dys[d] * i;

                            if (isRange(nx, ny) && pan[nx][ny] == value) {
                                cnt++;
                            } else {
                                break;
                            }
                        }

                        if (cnt == 5) { // 육목 check
                            int bx = x - dxs[d];
                            int by = y - dys[d];
                            if (isRange(bx, by) && pan[bx][by] == value) {
                                continue;
                            }

                            int ax = x + dxs[d] * 5;
                            int ay = y + dys[d] * 5;
                            if (isRange(ax, ay) && pan[ax][ay] == value) {
                                continue;
                            }

                            System.out.println(value);
                            System.out.println((x + 1) + " " + (y + 1));
                            isFind = true;
                            System.exit(0);
                        }
                    }

                }
            }
        }
        if (!isFind) {
            System.out.println(0);
        }
    }
}
