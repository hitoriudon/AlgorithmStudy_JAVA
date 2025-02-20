import java.io.*;
import java.util.*;

/** 게리맨더링 */
public class P17471 {
    static int n;
    static int[] people;
    static List<Integer>[] nodes;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        nodes = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        // 인덱싱 맞춰주기 위해 인덱스 0에 0!
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                nodes[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        makeCombination(1, new ArrayList<>());
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // N <= 10에서 묶일 수 있는 조합 개수 구하기
    static void makeCombination(int start, List<Integer> voter1) {
        if (!voter1.isEmpty()) {
            f(new ArrayList<>(voter1));
        }

        for (int i = start; i <= n; i++) {
            voter1.add(i);
            makeCombination(i + 1, voter1);
            voter1.remove(voter1.size() - 1);
        }
    }

    static void f(List<Integer> voter1) {
        List<Integer> voter2 = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            // 1부터 N까지에서 voter1에 없는 애들은 voter2
            if (!voter1.contains(i)) {
                voter2.add(i);
            }
        }

        // bfs 함수 ret 값은 boolean 타입
        if (bfs(voter1) && bfs(voter2)) {
            int population1 = 0, population2 = 0;
            for (int v : voter1) {
                population1 += people[v];
            }
            for (int v : voter2) {
                population2 += people[v];
            }
            ans = Math.min(ans, Math.abs(population1 - population2));
        }
    }

    // 연결이 되어 있으면 true, 안 되어 있으면 false 리턴
    static boolean bfs(List<Integer> voter) {
        if (voter.isEmpty())
            return false;

        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1]; // 0은 항상 비우기
        que.add(voter.get(0));
        visited[voter.get(0)] = true;
        int count = 1;

        while (!que.isEmpty()) {
            int node = que.poll();
            for (int neighbor : nodes[node]) {
                if (!visited[neighbor] && voter.contains(neighbor)) {
                    visited[neighbor] = true;
                    que.add(neighbor);
                    count++;
                }
            }
        }

        return count == voter.size(); // 같으면 연결 된 것
    }
}
