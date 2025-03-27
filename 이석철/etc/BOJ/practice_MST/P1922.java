package BOJ.practice_MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1922 {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight); // always first argu - second argu
        }
    }

    static Edge[] edgeList;
    static int[] parents;
    static int V, E;

    public static void make() {
        parents = new int[V + 1];

        for (int i = 1; i < V + 1; i++)
            parents[i] = i;
    }

    public static int find(int a) {
        if (a == parents[a]) // return condition
            return a;

        return parents[a] = find(parents[a]); // path compression
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot; // only left side(root)
        return true;
    }

    public static boolean unionSimilarRank(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        // not perfect rank. just similar
        if (aRoot > bRoot)
            parents[bRoot] = aRoot;
        else
            parents[aRoot] = bRoot;

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }

        make(); // (number of V tree) initialize

        Arrays.sort(edgeList); // MST essential: asc sort

        int result = 0;
        int edgeCount = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++edgeCount == V - 1)
                    break;
            }
        }

        System.out.println(result);
    }
}
