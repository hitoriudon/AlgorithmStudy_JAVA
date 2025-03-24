import java.io.*;
import java.util.*;

public class P13023 {
    static int n, m;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            dfs(i, 1);
            if (found) {
                break;
            }
        }

        System.out.println(found ? 1 : 0);
    }

    private static void dfs(int curr, int depth) {
        if (depth == 5) {
            found = true;
            return;
        }

        visited[curr] = true;

        for (int next : adjList[curr]) {
            if (!visited[next]) {
                dfs(next, depth + 1);
                if (found) {
                    return;
                }
            }
        }

        visited[curr] = false;
    }
}
