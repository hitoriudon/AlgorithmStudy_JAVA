// [실버2] 18111번. 마인크래프트
// 1. (2초 소요) (i, j)의 가장 위에 있는 블록 제거 -> 인벤토리에 넣기
// 2. (1초 소요) 인벤토리에서 블록 하나를 꺼내어 (i, j)의 가장 위에 놓기

import java.io.*;
import java.util.*;

class P18111 {
    public static void main(String[] args) throws Exception {
        // 2차원 배열 maps[N][M] 사용
        // 메모리 : 35228 KB, 시간 : 644 ms
        Solution_1();

        // 1차원 배열 heightCount[257] 사용
        // 메모리 : 32504 KB, 시간 : 256 ms
        Solution_2();
    }

    static void Solution_1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집터 세로
        int M = Integer.parseInt(st.nextToken()); // 집터 가로
        int B = Integer.parseInt(st.nextToken()); // 작업 시작 시 인벤토리에 있는 블록의 개수

        int minHeight = Integer.MAX_VALUE; // maps 중 최소 높이
        int maxHeight = Integer.MIN_VALUE; // maps 중 최대 높이

        // 땅의 높이 maps 입력 받기
        int[][] maps = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                maps[n][m] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, maps[n][m]);
                maxHeight = Math.max(maxHeight, maps[n][m]);
            }
        }

        // 최종 최소 시간 및 최대 높이
        int resultTime = Integer.MAX_VALUE;
        int resultHeight = -1;

        for (int h = minHeight; h <= maxHeight; h++) {
            // 현재 목표 높이인 h까지 땅고르기를 하기 위해 필요한 Block 수, 시간
            int blocks = B;
            int time = 0;

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    int diff = maps[n][m] - h;

                    // maps[n][m]이 더 높은 경우 -> 1번 작업 실행 (2초 소요)
                    if (h < maps[n][m]) {
                        blocks += diff;
                        time += (2 * diff);
                    }

                    // maps[n][m]이 더 낮은 경우 -> 2번 작업 실행 (1초 소요)
                    else if (h > maps[n][m]) {
                        blocks -= (-diff);
                        time += (-diff);
                    }
                }
            }

            if (blocks > -1) {
                if (time < resultTime) {
                    resultTime = time;
                    resultHeight = h;
                } else if ((time == resultTime) && (h > resultHeight)) {
                    resultHeight = h;
                }
            }
        }
        System.out.println(resultTime + " " + resultHeight);
    }

    static void Solution_2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집터 세로
        int M = Integer.parseInt(st.nextToken()); // 집터 가로
        int B = Integer.parseInt(st.nextToken()); // 작업 시작 시 인벤토리에 있는 블록의 개수

        int minHeight = Integer.MAX_VALUE; // maps 중 최소 높이
        int maxHeight = Integer.MIN_VALUE; // maps 중 최대 높이

        // 땅의 높이 별 등장 횟수 heightCount
        int[] heightCount = new int[257];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int height = Integer.parseInt(st.nextToken());
                heightCount[height]++;
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        // 최종 최소 시간 및 최대 높이
        int resultTime = Integer.MAX_VALUE;
        int resultHeight = -1;

        for (int h = minHeight; h <= maxHeight; h++) {
            // 현재 목표 높이인 h까지 땅고르기를 하기 위해 필요한 Block 수, 시간
            int blocks = B;
            int time = 0;

            for (int i = 0; i <= 256; i++) {
                // 나온 적 없는 땅의 높이 -> continue
                if (heightCount[i] == 0)
                    continue;

                int diff = i - h; // 현재 높이 - 목표 높이

                // 1번 작업 실행 (2초 소요) : 삭제 후 인벤토리에 추가
                if (diff > 0) {
                    blocks += (diff * heightCount[i]);
                    time += (diff * 2 * heightCount[i]);
                }

                // 2번 작업 실행 (1초 소요) : 현재 위치에 땅 블록 추가
                else if (diff < 0) {
                    blocks -= ((-diff) * heightCount[i]);
                    time += ((-diff) * heightCount[i]);
                }
            }

            if (blocks > -1) {
                if (time < resultTime) {
                    resultTime = time;
                    resultHeight = h;
                } else if ((time == resultTime) && (h > resultHeight)) {
                    resultHeight = h;
                }
            }
        }
        System.out.println(resultTime + " " + resultHeight);
    }
}