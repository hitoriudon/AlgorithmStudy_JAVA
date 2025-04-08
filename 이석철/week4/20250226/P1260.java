
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class P1260 {
    static int n;
    static int m;
    static int start;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] vis;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        for (int i = 1; i <= n; i++){
            Collections.sort(graph.get(i));
        }

        vis = new boolean[n + 1];
        dfs(start);
        System.out.println();
        bfs(start);
    }    
    
    public static void dfs(int start){
        vis[start] = true;
        System.out.print(start + " ");
        for (int node: graph.get(start)){
            if (!vis[node]){
                dfs(node);
            }
        }
    }

    public static void bfs(int start){
        ArrayDeque<Integer> que = new ArrayDeque<>();
        vis = new boolean[n + 1];
        que.offer(start);
        vis[start] = true;
        
        while (!que.isEmpty()){
            int node = que.poll();
            System.out.print(node + " ");
            for (int nextNode: graph.get(node)){
                if (!vis[nextNode]){
                    vis[nextNode] = true;
                    que.offer(nextNode);
                }
            }
        }
    }
}
