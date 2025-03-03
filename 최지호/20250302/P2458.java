import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2458 {
    static int n;
    static int m;
    static ArrayList<Integer>[] bigList;
    static ArrayList<Integer>[] smallList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //인접 리스트 두개 만들기 더 큰 애들, 더 작은 애들
        bigList = new ArrayList[n+1];
        smallList = new ArrayList[n+1];

        for(int i = 1; i <= n; i++) {
            bigList[i] = new ArrayList<>();
            smallList[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a가 b보다 작다
            bigList[a].add(b);
            
            //b가 a보다 크다
            smallList[b].add(a);
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            int smaller = dfs(i,bigList);

            visited = new boolean[n+1];
            int bigger = dfs(i, smallList);

            if(smaller + bigger == n - 1) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static int dfs(int i, ArrayList<Integer>[] list) {
        visited[i] = true;
        int cnt = 0;
        
        for (int next : list[i]) {
            if (!visited[next]) {
                int subCount = dfs(next, list);
                cnt += subCount + 1;
            }
        }
        return cnt;
    }
}
