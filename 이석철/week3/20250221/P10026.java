/** 
 * 적록색약 전략
 * 
 * 처음 입력받을 때 적록색약 2차원 배열과 그렇지 않은 2차원 배열 총 두 개 선언
 * 적록색약 배열에서 초록색을 빨강색으로 통일시킴.
 * 그 뒤에 같은 색깔 영역 찾는 완탐 + BFS 진행
 * */

 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayDeque;
 
 public class P10026 {
     static char[][] originalPicture;
     static char[][] weirdPicture;
     static int n;
     static final int[] dxs = {1, 0, -1, 0};
     static final int[] dys = {0, -1, 0, 1};
     static boolean[][] vis;
     
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         n = Integer.parseInt(br.readLine());
         originalPicture = new char[n][n];
         weirdPicture = new char[n][n];
         vis = new boolean[n][n];
 
         for (int i = 0; i < n; i++){
             String inputColor = br.readLine();
             for (int j = 0; j < n; j++){
                 char color = inputColor.charAt(j);
                 originalPicture[i][j] = color;
                 if (color == 'R' || color == 'G'){
                     weirdPicture[i][j] = 'R'; // 그냥 레드로 통합
                 } else {
                     weirdPicture[i][j] = 'B';
                 }
             }
         }
 
         
         int originalCount = 0;
         for (int i = 0; i < n; i++){
             for (int j = 0; j < n; j++){
                 if (!vis[i][j]){
                     vis[i][j] = true;
                     originalCount += bfs(i, j, originalPicture[i][j], originalPicture);
                 }
             }
         }
         
         int weirdCount = 0;
         vis = new boolean[n][n];
         for (int i = 0; i < n; i++){
             for (int j = 0; j < n; j++){
                 if (!vis[i][j]){
                     vis[i][j] = true;
                     weirdCount += bfs(i, j, weirdPicture[i][j], weirdPicture);
                 }
             }
         }
         
         System.out.println(originalCount + " " + weirdCount);
     }
 
     public static int bfs(int x, int y, char v, char[][] grid){
         ArrayDeque<int[]> que = new ArrayDeque<>();
         int[] start = {x, y};
         que.offer(start);
         
         while (!que.isEmpty()){
             int[] dots = que.poll();
             x = dots[0];
             y = dots[1];
 
             for (int d = 0; d < 4; d++){
                 int nx = x + dxs[d];
                 int ny = y + dys[d];
 
                 if (isRange(nx, ny) && !vis[nx][ny] && grid[nx][ny] == v){
                     vis[nx][ny] = true;
                     int[] temp = {nx, ny};
                     que.offer(temp);
                 }
             }
         }
         return 1;
     }
 
     public static boolean isRange(int x, int y){
         return x >= 0 && y >= 0 && x < n && y < n;
     }
 }
 