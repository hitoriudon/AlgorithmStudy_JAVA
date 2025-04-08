import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int blocksInInventory = Integer.parseInt(st.nextToken());

        int[][] land = new int[n][m];
        int maxHeight = 0;
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int height = Integer.parseInt(st.nextToken());
                land[i][j] = height;
                maxHeight = Math.max(maxHeight, height);
                minHeight = Math.min(minHeight, height);
            }
        }

        // 높이는 256를 넘을 수 없다
        if (maxHeight > 256) {
            maxHeight = 256;
        }

        /**
         * 생각하기 가장 어려웠던 부분
         * 땅고르기를 할 높이는 결국 min값과 max 값에서 결정됨.
         * 그래서 그 사이의 높이를 다 만들어 보고 최소 비용을 비교하는 브루트 포스로 풀기로 결정
         * 비용을 계산하는 순서는
         * 1. 모든 땅을 다 탐색하면서, 높이가 만들고자 하는 높이(h)보다 크면 인벤에 넣기(부족한 애들은 우선 패스)
         * 2. 부족한 애들은 인벤토리에서 가져와서 채워주기.
         * 3. 만약 채워주다가 인벤토리가 0이 되면, 그 땅의 높이를 만들 수 없다.
         */
        int answerTime = Integer.MAX_VALUE;
        int answerHeight = 0;
        for (int h = maxHeight; h >= minHeight; h--) {
            int time = 0;
            int[][] copyLand = new int[n][m];
            int copyBlocks = blocksInInventory;
            // copy
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyLand[i][j] = land[i][j];

                    // 1번 과정
                    int heightDiff = copyLand[i][j] - h;
                    if (heightDiff > 0) {
                        copyLand[i][j] -= heightDiff;
                        copyBlocks += heightDiff;
                        time += heightDiff * 2;
                    }
                }
            }

            // 2, 3번 과정
            int hCount = 0;
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyLand[i][j] == h) {
                        hCount++;
                    } else { // 이젠 부족한 경우밖에 없기 때문에 else 처리해도 됨
                        int heightDiff = h - copyLand[i][j];

                        if (copyBlocks < heightDiff) {
                            // 그 땅의 높이를 만들 수 없다
                            flag = true;
                            break;
                        } else {
                            copyLand[i][j] += heightDiff;
                            copyBlocks -= heightDiff;
                            time += heightDiff;
                            hCount++;
                        }
                    }
                }
                if (flag)
                    break;
            }

            if (flag) {
                continue;
            }

            if (hCount == n * m) {
                if (answerTime == time) {
                    answerHeight = Math.max(answerHeight, h);
                } else if (answerTime > time) {
                    answerTime = time;
                    answerHeight = h;
                }
            }
        }
        System.out.println(answerTime + " " + answerHeight);
    }
}

// 반례: https://www.acmicpc.net/board/view/138638