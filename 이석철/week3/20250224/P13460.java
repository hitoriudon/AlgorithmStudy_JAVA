
/**
 * 구슬 탈출 2 전략
 * 많은 세부 조건 때문에 어려운 문제였다.
 * 한 시간 동안은 내가 코드를 이렇게 짜도 되나? 반례 없나? 반례 있으면 다른 방법 생각 -> 이걸 반복했음
 * N <= 20이라서 백트래킹도 가능할 거 같았으나, BFS로 구현하고 que에 넣는 조건을 디테일하게 정하는 식으로 했다.
 * que에 넣는 조건은, 빨간 구슬과 파란 구슬을 네 방향으로 모두 기울이고,
 * 파란 구슬이 구멍에 빠지면 que에 넣지 않고, 빨간 구슬이 구멍에 빠지면 그 즉시 타임 출력하고 종료시켰다.
 * 앞 두 조건에 걸리지 않았다면 방문 조건에 걸리지 않으면 que에 우선 넣고 계속 BFS를 돌렸다. 무한루프는 어차피 빠짐
 */

 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayDeque;
 import java.util.StringTokenizer;
 
 public class P13460 {
     static int n, m;
     static char[][] board;
     static final int[] dxs = { 0, 1, 0, -1 };
     static final int[] dys = { 1, 0, -1, 0 };
     static boolean[][][][] vis;
     static int rx, ry, bx, by;
     static ArrayDeque<int[]> que;
 
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
 
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
 
         board = new char[n][m];
         vis = new boolean[n][m][n][m]; // Red x, Red y, Blue x, Blue y. 빨간 구슬과 파란 구슬을 동시에 visit 관리하기
         for (int i = 0; i < n; i++) {
             String inputString = br.readLine();
             for (int j = 0; j < m; j++) {
                 char ret = inputString.charAt(j);
                 if (ret == 'R') {
                     rx = i;
                     ry = j;
                 } else if (ret == 'B') {
                     bx = i;
                     by = j;
                 }
                 board[i][j] = ret;
             }
         }
 
         que = new ArrayDeque<>();
         que.offer(new int[] { rx, ry, bx, by, 1 }); // time 때문에 Point Class 두 개 넣는 거 포기
         vis[rx][ry][bx][by] = true;
 
         while (!que.isEmpty()) {
             int[] currentInfo = que.poll();
             rx = currentInfo[0];
             ry = currentInfo[1];
             bx = currentInfo[2];
             by = currentInfo[3];
             int time = currentInfo[4];
 
             for (int d = 0; d < 4; d++) {
                 int[] red = move(rx, ry, dxs[d], dys[d]);
                 int redX = red[0];
                 int redY = red[1];
                 int redMoveCount = red[2];
 
                 int[] blue = move(bx, by, dxs[d], dys[d]);
                 int blueX = blue[0];
                 int blueY = blue[1];
                 int blueMoveCount = blue[2];
 
                 if (board[blueX][blueY] == 'O') { // que에 넣지 않는 조건 1
                     continue;
                 }
 
                 if (board[redX][redY] == 'O') { // que에 넣지 않는 조건 2
                     System.out.println(time <= 10 ? time : -1);
                     System.exit(0);
                 }
 
                 // 둘 다 똑같은 위치에 있을 수 없어서, 만약 똑같은 위치에 있으면 더 적게 움직인 애가 그 자리를 차지한다(기울일 때 우선 순위)
                 if (redX == blueX && redY == blueY && redMoveCount > blueMoveCount) {
                     redX -= dxs[d];
                     redY -= dys[d];
                 } else if (redX == blueX && redY == blueY && blueMoveCount > redMoveCount) {
                     blueX -= dxs[d];
                     blueY -= dys[d];
                 }
 
                 if (!vis[redX][redY][blueX][blueY]) { // que에 넣지 않는 조건 3. 이게 무한 루프를 방지함
                     que.offer(new int[] { redX, redY, blueX, blueY, time + 1 });
                     vis[redX][redY][blueX][blueY] = true;
                 }
             }
         }
         System.out.println(-1);
     }
 
     public static int[] move(int x, int y, int dx, int dy) {
         int moveCount = 0;
         while (true) {
             int nx = x + dx;
             int ny = y + dy;
             if (board[nx][ny] == '#' || board[x][y] == 'O') {
                 return new int[] { x, y, moveCount };
             }
             x = nx;
             y = ny;
             moveCount += 1;
         }
     }
 }