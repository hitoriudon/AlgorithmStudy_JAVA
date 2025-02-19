import java.io.*;
import java.util.*;

public class P2615 {
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[19][19];
        for(int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                if(j <= 14) {
                    if(checkRight(i, j)) {
                        System.out.println(board[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }
                }
                if(i <= 14) {
                    if(checkBottom(i, j)) {
                        System.out.println(board[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }
                }
                if(i <= 14 && j <= 14) {
                    if(checkDigonalb(i, j)) {
                        System.out.println(board[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }
                }
                if(i >= 4 && j <= 14) {
                    if(checkDigonalt(i, j)) {
                        System.out.println(board[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static boolean checkRight(int i, int j) {
        int color = board[i][j];
        if(color == 0) return false;
        if(j - 1 >= 0 && board[i][j - 1] == color) return false;
        for(int k = 1; k < 5; k++) {
            if(board[i][j + k] != color) return false;
        }
        if(j + 5 < 19 && board[i][j + 5] == color) return false;
        return true;
    }

    private static boolean checkBottom(int i, int j) {
        int color = board[i][j];
        if(color == 0) return false;
        if(i - 1 >= 0 && board[i - 1][j] == color) return false;
        for(int k = 1; k < 5; k++) {
            if(board[i + k][j] != color) return false;
        }
        if(i + 5 < 19 && board[i + 5][j] == color) return false;
        return true;
    }

    private static boolean checkDigonalb(int i, int j) {
        int color = board[i][j];
        if(color == 0) return false;
        if(i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == color) return false;
        for(int k = 1; k < 5; k++) {
            if(board[i + k][j + k] != color) return false;
        }
        if(i + 5 < 19 && j + 5 < 19 && board[i + 5][j + 5] == color) return false;
        return true;
    }

    private static boolean checkDigonalt(int i, int j) {
        int color = board[i][j];
        if(color == 0) return false;
        if(i + 1 < 19 && j - 1 >= 0 && board[i + 1][j - 1] == color) return false;
        for(int k = 1; k < 5; k++) {
            if(board[i - k][j + k] != color) return false;
        }
        if(i - 5 >= 0 && j + 5 < 19 && board[i - 5][j + 5] == color) return false;
        return true;
    }
}
