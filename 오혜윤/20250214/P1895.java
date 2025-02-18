// [실버4] 1895번. 필터

import java.io.*;
import java.util.*;

class P1895 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] I = new int[R][C];
        int[][] J = new int[R - 2][C - 2];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                I[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int T = Integer.parseInt(br.readLine());

        int result = 0;
        for (int r = 0; r < R - 2; r++) {
            for (int c = 0; c < C - 2; c++) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int x = r; x < r + 3; x++) {
                    for (int y = c; y < c + 3; y++) {
                        list.add(I[x][y]);
                    }
                }
                Collections.sort(list);
                if (list.get(4) >= T) {
                    result += 1;
                }
            }
        }
        System.out.println(result);
    }
}
