// [골드5] 15686번. 치킨 배달
// 메모리 : 13464 KB, 시간 : 140 ms

// 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
// 도시의 치킨 거리 : 모든 집의 치킨 거리의 합
// 도시의 치킨 거리 최솟값 찾기

import java.io.*;
import java.util.*;

class P15686 {

    static int N, M;
    static int[][] maps;
    static List<int[]> houses;   // 모든 집의 위치가 담긴 리스트
    static List<int[]> chickens; // 모든 치킨집의 위치가 담긴 리스트
    static int minDistacne = Integer.MAX_VALUE;  // 최소 도시의 치킨 거리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N x N 크기의 도시
        M = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 치킨집의 최대 개수

        maps = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());
                maps[i][j] = info;
                if (info == 1)
                    houses.add(new int[] { i, j });
                else if (info == 2)
                    chickens.add(new int[] { i, j });
            }
        }

        makeCombination(0, 0, new ArrayList<>());
        System.out.println(minDistacne);
    }
    
    // 남을 치킨집 조합
    static void makeCombination(int start, int cnt, List<int[]> restChickens) {
        if (cnt == M) {
            minDistacne = Math.min(minDistacne, getDistance(restChickens));
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            restChickens.add(chickens.get(i)); // 현재 치킨집 남기기
            makeCombination(i + 1, cnt + 1, restChickens);
            restChickens.remove(restChickens.size() - 1); // 백트래킹 (방금 넣은 현재 치킨집 제거)
        }
    }
    
    static int getDistance(List<int[]> restChickens) {
        int total = 0;

        for (int i = 0; i < houses.size(); i++) {
            int xHouse = houses.get(i)[0];
            int yHouse = houses.get(i)[1];
            int distance = Integer.MAX_VALUE;

            for (int j = 0; j < restChickens.size(); j++) {
                int xChicken = restChickens.get(j)[0];
                int yChicken = restChickens.get(j)[1];

                int d = Math.abs(xHouse - xChicken) + Math.abs(yHouse - yChicken);
                distance = Math.min(distance, d);
            }

            total += distance;
        }

        return total;
    }
    
}