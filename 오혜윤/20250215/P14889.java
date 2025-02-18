// [실버1] 14889번. 스타트와 링크

import java.io.*;
import java.util.*;

public class P14889 {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];

        S = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                S[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(result);
    }

    // 팀원 선택 : 조합 활용
    static void combination(int start, int r) {
        // N/2개를 다 선택한 경우
        if (r == N / 2) {
            minCalc();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i + 1, r + 1);
                visited[i] = false;
            }
        }
    }

    // 뽑은 조합에 대해 두 팀의 능력치 차이 및 최솟값 구하기
    static void minCalc() {
        int teamA = 0;
        int teamB = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((visited[i] == true) && (visited[j] == true)) {
                    teamA += (S[i][j] + S[j][i]);
                }

                else if ((visited[i] == false) && (visited[j] == false)) {
                    teamB += (S[i][j] + S[j][i]);
                }
            }
        }

        int diff = Math.abs(teamA - teamB);
        if (diff == 0) {
            System.out.println(diff);
            System.exit(0);
        }
        result = Math.min(result, diff);
    }
}
