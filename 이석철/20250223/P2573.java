/**
 * 빙산 전략
 * 
 * 녹는 건 dx dy 기법
 * 녹고 얼음 덩이 몇 개인지 체크하는 건 BFS
 * 
 * calculateIcePartition: 얼음 덩어리 수 체크 함수(bfs 함수와 연결)
 * isAllMelted: 모든 얼음이 녹았는지 체크하는 함수(완탐)
 * melt: 상하좌우 물 개수만큼 얼음을 녹이는 함수
 */

 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayDeque;
 import java.util.StringTokenizer;
 
 public class P2573 {
     static int n;
     static int m;
     static int[][] ice;
     static final int[] dxs = {1, 0, -1, 0};
     static final int[] dys = {0, 1, 0, -1};
     static boolean[][] vis;
 
     static class Point{
         int x;
         int y;
 
         Point(int x, int y){
             this.x = x;
             this.y = y;
         }
     }
 
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
 
         ice = new int[n][m];
         for (int i = 0; i < n; i++){
             st = new StringTokenizer(br.readLine());
             for (int j = 0; j < m; j++){
                 ice[i][j] = Integer.parseInt(st.nextToken());
             }
         }
 
         int time = 0;
         while (calculateIcePartition() == 1 && !isAllMelted()){ // 얼음 덩이가 2개 이상으로 분리되거나 다 녹았으면 break
             melt();
             time++;
         }
 
         System.out.println(isAllMelted() ? 0 : time); // 다 녹았는데 while을 빠져나온 거면, 덩이 수는 계속 한 개였음. 그래서 0 출력
     }
 
     // 얼음 녹이는 함수
     public static void melt(){
         // 얼음이 녹는 건 동시에 진행되어야 함. 오리지널 2차원 배열에서 0으로 만들어버리면 값이 달라짐
         int[][] afterMelt = new int[n][m]; // 그래서 결과 temp처럼 놓고 오리지널 배열에 복사해야 함
 
         for (int x = 0; x < n; x++){
             for (int y = 0; y < m; y++){
                 if (ice[x][y] == 0){ 
                     afterMelt[x][y] = 0;
                 } else if (ice[x][y] > 0){
                     int countWater = 0; // 인접 상하좌우 물 세기
                     for (int d = 0; d < 4; d++){
                         int nx = x + dxs[d];
                         int ny = y + dys[d];
 
                         if (ice[nx][ny] == 0){
                             countWater++;
                         }
                     }
                     afterMelt[x][y] = ice[x][y] - countWater > 0 ? ice[x][y] - countWater : 0; // 마이너스로 떨어지면 그냥 0
                 }
             }
         }
 
         for (int i = 0; i < n; i++){
             for (int j = 0; j < m; j++){
                 ice[i][j] = afterMelt[i][j]; // 복사
             }
         }
     }
 
     // 얼음 몇 덩이로 나뉘었는지 세는 함수
     public static int calculateIcePartition(){
         vis = new boolean[n][m];
         int countIcePartition = 0;
         for (int i = 0; i < n; i++){
             for (int j = 0; j < m; j++){
                 if (ice[i][j] > 0 && !vis[i][j]){
                     Point p = new Point(i, j);
                     countIcePartition += bfs(p);
                 }
             }
         }
 
         return countIcePartition; // 얼음 덩어리 개수 반환
     }
 
     public static int bfs(Point p){
         ArrayDeque<Point> que = new ArrayDeque<>();
 
         que.offer(p);
         vis[p.x][p.y] = true;
 
         while (!que.isEmpty()){
             p = que.poll();
 
             for (int d = 0; d < 4; d++){
                 int nx = p.x + dxs[d];
                 int ny = p.y + dys[d];
 
                 if (ice[nx][ny] > 0 && !vis[nx][ny]){
                     vis[nx][ny] = true;
                     Point np = new Point(nx, ny);
                     que.offer(np);
                 }
             }
         }
 
         return 1; // 한 덩이
     }
 
     // 얼음 다 녹았는지 세는 함수
     public static boolean isAllMelted(){
         for (int i = 0; i < n; i++){
             for (int j = 0; j < m; j++){
                 if (ice[i][j] > 0){
                     return false;
                 }
             }
         }
 
         return true;
     }
 }
 