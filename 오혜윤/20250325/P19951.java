// [골드5] 19951번. 태상이의 훈련소 생활
// 메모리 : 64012 KB, 시간 : 488 ms

import java.io.*;
import java.util.*;

class P19951 {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 연병장의 크기 N
        int M = Integer.parseInt(st.nextToken()); // 조교의 수 M

        int[] heights = new int[N];
        int[] orders = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 시작 칸
            int b = Integer.parseInt(st.nextToken()); // 종료 칸
            int k = Integer.parseInt(st.nextToken()); // 줄거나 늘어나야할 흙의 양

            orders[a] += k;
            orders[b] -= k;
        }

        for (int i = 0; i < N; i++) {
            orders[i + 1] += orders[i];
            sb.append(heights[i] + orders[i]).append(" ");
        }
        System.out.println(sb);
    }

}