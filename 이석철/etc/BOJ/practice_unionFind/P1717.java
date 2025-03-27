import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717 {
    static int n;
    static int[] parents;

    public static void make() {
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i; // initialize. parents = self
        }
    }

    public static int find(int a) { // find a's set(who is a's representation number)
        if (a == parents[a])
            return a;

        return parents[a] = find(parents[a]); // path compression
    }

    // in kruskal algorithm, return type(boolean) is important
    public static boolean union(int a, int b) { // union a's set and b's set(by representation number)
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot; // union to A(not B)
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        make();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 0) {
                union(a, b);
            }

            else if (order == 1) {
                int aRoot = find(a);
                int bRoot = find(b);

                sb.append(aRoot == bRoot ? "YES" : "NO").append("\n");
            }
        }

        System.out.println(sb);
    }

}