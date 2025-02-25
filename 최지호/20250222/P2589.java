import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2589 {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static char[][] map;
    static boolean[][] visited;
    static int l;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[l][k];

        for(int i = 0; i < l; i++) {
            String input = br.readLine();
            for(int j = 0; j < k; j++) {
                map[i][j] = input.charAt(j);
            }
        }


        List<Integer> distance = new ArrayList<>();

        for(int i = 0; i < l; i++) {
            for(int j = 0; j < k; j++) {
                if(map[i][j] == 'L') {
                    visited = new boolean[l][k];
                    distance.add(bfs(i, j));
                }
            }
        }

        int answer = 0;
        for(int a : distance) {
            answer = Math.max(answer, a);
        }

        System.out.println(answer);
    }
        

    private static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        int[][] newMap = new int[l][k];
        int maxDistance = 0;
        q.add(new int[]{i,j});
        visited[i][j] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int g = 0; g < 4; g++) {
                int nx = now[0] + dx[g];
                int ny = now[1] + dy[g];

                if(nx >= 0 && nx < l && ny >= 0 && ny < k && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    newMap[nx][ny] = newMap[now[0]][now[1]] + 1;
                    maxDistance = Math.max(maxDistance, newMap[nx][ny]);
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return maxDistance;
    }
}
