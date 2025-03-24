// [골드5] 13023번. ABCDE
// 메모리 : 16784 KB, 시간 : 172 ms

import java.io.*;
import java.util.*;

public class P13023 {

    static List<Integer>[] graph;
    static boolean[] visited;
    static int result;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        visited = new boolean[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cntFriends(i, 1);
                visited[i] = false;
            }
        }
        System.out.println(0);
    }

    static void cntFriends(int node, int cnt) {
        if (cnt == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (int n : graph[node]) {
            if (!visited[n]) {
                visited[n] = true;
                cntFriends(n, cnt + 1);
                visited[n] = false;
            }
        }
    }
}
