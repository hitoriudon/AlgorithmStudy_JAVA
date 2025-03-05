/**
 * ABCDE 전략
 * 
 * 한 점에서 4개의 dfs 연결 길이를 찾는 문제
 * 친구 관계 어쩌구 하길래 뭔 개소린가 싶었음 문제 이해가 가장 어려웠다
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    static boolean[] vis;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < n; i++){
            vis = new boolean[n];
            dfs(i, 0);
        }
        System.out.println(0);
    }

    public static void dfs(int node, int len){
        if (len == 4){
            System.out.println(1); // 찾았으면 종료
            System.exit(0);
        }
        vis[node] = true;

        for (int i = 0; i < graph[node].size(); i++){
            int nextNode = graph[node].get(i);
            if (!vis[nextNode]){
                vis[nextNode] = true;
                dfs(nextNode, len + 1);
                vis[nextNode] = false; // backtracking
            }
        }
    }    
}
