// [실버2] 1780번. 종이의 개수
// 메모리 : 314936 KB, 시간 : 752 ms

import java.io.*;
import java.util.*;

public class P1780 {

    static int[][] paper;
    static int result_1, result0, result1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result_1 = 0;
        result0 = 0;
        result1 = 0;
        cntPaper(0, 0, N);
        System.out.println(result_1);
        System.out.println(result0);
        System.out.println(result1);
    }

    static void cntPaper(int x, int y, int N) {
        if (checkPaper(x, y, N)) {
            if (paper[x][y] == -1)
                result_1++;
            else if (paper[x][y] == 0)
                result0++;
            else
                result1++;
        } else {
            int size = N / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cntPaper(x + i * size, y + j * size, size);
                }
            }
        }
    }

    static boolean checkPaper(int x, int y, int N) {
        int now = paper[x][y];
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                if (paper[i][j] != now)
                    return false;
            }
        }
        return true;
    }
}
