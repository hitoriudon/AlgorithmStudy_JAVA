import java.io.*;
import java.util.*;

public class P1987 {
    static char[][] board;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean[][] visited;
    static boolean[] alphabet = new boolean[26];
    static int maxLength;
    static int r,c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for(int i = 0; i < r; i++) {
            String input = br.readLine();
            for(int j = 0; j < c; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        //정수값으로 빼기 ex) A = 0, B = 1 ...
        alphabet[board[0][0] - 'A'] = true;
        dfs(0,0,1);

        System.out.println(maxLength);
    }
    private static void dfs(int i, int j, int length) {
        maxLength = Math.max(maxLength, length);

        //돌리기~
        for(int q = 0; q < 4; q++) {
            int nx = i + dx[q];
            int ny = j + dy[q];

            if(nx >= 0 && ny >= 0 && nx < r && ny < c) {
                int nextChar = board[nx][ny] - 'A';

                if(!alphabet[nextChar]) {
                    //방문 안한 곳이면 ~ 방문처리 후 dfs
                    alphabet[nextChar] = true;
                    dfs(nx,ny,length+1);
                    
                    //백트래킹 다시 복구하기
                    alphabet[nextChar] = false;
                }
            }
        }
    }
}