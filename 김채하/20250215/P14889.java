import java.io.*;
import java.util.*;

public class P14889 {
    static int[][] field;
    static boolean[] team;
    static int answer;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        field = new int[N][N];
        team = new boolean[N];
        answer = Integer.MAX_VALUE;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);

        System.out.println(answer);
    }

    public static void backtracking(int number, int player) {
        if (player == N / 2) {
            int team1Ability = 0;
            int team2Ability = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (team[i] && team[j]) {
                        team1Ability += field[i][j] + field[j][i];
                    } else if (!team[i] && !team[j]) {
                        team2Ability += field[i][j] + field[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(team1Ability - team2Ability));
            return;
        }

        for (int i = number; i < N; i++) {
            if (team[i]) continue; // 이미 팀에 포함된 경우는 건너뛰기
            team[i] = true;
            backtracking(i + 1, player + 1);
            team[i] = false;
        }
    }
}
