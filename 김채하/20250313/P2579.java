import java.io.*;

public class P2579 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }

        int[][] stair = new int[N][2];
        stair[0][0] = stair[0][1] = Integer.parseInt(br.readLine());
        stair[1][0] = Integer.parseInt(br.readLine());
        stair[1][1] = stair[0][0] + stair[1][0];

        for (int i = 2; i < N; i++) {
            int s = Integer.parseInt(br.readLine());
            stair[i][0] = Math.max(stair[i - 2][0], stair[i - 2][1]) + s;
            stair[i][1] = stair[i - 1][0] + s;
        }

        System.out.println(Math.max(stair[N - 1][0], stair[N - 1][1]));
    }
}
