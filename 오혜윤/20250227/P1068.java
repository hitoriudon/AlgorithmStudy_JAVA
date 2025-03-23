// [골드5] 1068번. 트리
// 메모리 : 11636 KB, 시간 : 68 ms

import java.io.*;
import java.util.*;

public class P1068 {

    static int N, root, result;
    static List<Integer>[] graph;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 노드의 개수

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == -1) {
                root = i; // 루트 노드
            } else {
                graph[tmp].add(i);
            }
        }

        int target = Integer.parseInt(br.readLine());
        if (target == root) { // 루트 노드 삭제 -> 리프 노드 = 0개
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (graph[i].contains(target)) {
                graph[i].remove(Integer.valueOf(target));
                break;
            }
        }

        result = 0;
        cntLeaf(root);
        System.out.println(result);
    }

    // 리프 노드 개수 카운트
    static void cntLeaf(int node) {
        if (graph[node].isEmpty()) {
            result++;
            return;
        }

        for (int child : graph[node]) {
            cntLeaf(child);
        }
    }
}
