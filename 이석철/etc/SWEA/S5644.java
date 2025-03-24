package SWEA;

/** 
 * SWEA 5644 무선충전
 * 여기가 바로 if지옥
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S5644 {
    static int M;
    static int A;
    static final int[] dxs = { 0, -1, 0, 1, 0 }; // 정지 상 우 하 좌
    static final int[] dys = { 0, 0, 1, 0, -1 };
    static int[] userA; // 유저A 움직이는 방향 인덱스s
    static int[] userB; // 유저B 움직이는 방향 인덱스s
    static int[][] ap;
    static ArrayList<Integer>[][] grid; // 3차원 배열
    static int[] ability;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            userA = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                userA[i] = Integer.parseInt(st.nextToken());
            }

            userB = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                userB[i] = Integer.parseInt(st.nextToken());
            }

            ap = new int[A][4];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    ap[i][j] = Integer.parseInt(st.nextToken()); // y, x, 십자가 모양 배수, 능력치(점수)
                }
            }

            grid = new ArrayList[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    grid[i][j] = new ArrayList<>();
                }
            }
            ability = getArea(); // 무선 충전 영역 만들기

            int ax = 0;
            int ay = 0;
            int bx = 9;
            int by = 9;
            int score = 0;
            for (int time = 0; time <= M; time++) {
                /**
                 * userA, userB 배열엔 움직이는 방향이 담겨있다.
                 * 우선 움직이고, 현재 위치 배열 길이가 0보다 크면 그 안 요소들의 어빌리티 중 최대값을 구한 것을 충전 인덱스로 삼는다.
                 * 그 다음 둘이 같은 인덱스인지 비교한다.
                 * 만약 둘 다 같은 인덱스면, 유저 A와 B의 두번째 최대값을 구한 뒤 비교해서 어떤 걸 선택할 지 정해야 한다.
                 * 그러기 위해선 또 현재 배열 위치 길이를 구한 뒤, 둘 다 1, 1이면 반씩 나눠가지고
                 * 1보다 큰 유저가 있다면 두번째 최대값을 구해주고, 둘 다 있다면 두번째 최대값도 비교해서 더해준다
                 */
                ax += dxs[userA[time]];
                ay += dys[userA[time]];
                bx += dxs[userB[time]];
                by += dys[userB[time]];

                int aChoice = -1;
                int bChoice = -1;
                if (!grid[ax][ay].isEmpty()) {
                    aChoice = getMaxIndex(ax, ay);
                }
                if (!grid[bx][by].isEmpty()) {
                    bChoice = getMaxIndex(bx, by);
                }

                if (aChoice == -1 && bChoice == -1)
                    continue;

                if (aChoice != bChoice) {
                    if (aChoice != -1) {
                        score += ability[aChoice];
                    }
                    if (bChoice != -1) {
                        score += ability[bChoice];
                    }
                } else if (aChoice == bChoice) {
                    int aSecondChoice = getSecondMaxIndex(ax, ay, aChoice);
                    int bSecondChoice = getSecondMaxIndex(bx, by, bChoice);

                    if (aChoice == aSecondChoice && bChoice == bSecondChoice) {
                        score += ability[aChoice];
                    } else if (aChoice != aSecondChoice && bChoice == bSecondChoice) {
                        score += (ability[aSecondChoice] + ability[bChoice]);
                    } else if (aChoice == aSecondChoice && bChoice != bSecondChoice) {
                        score += (ability[aChoice] + ability[bSecondChoice]);
                    } else {
                        if (ability[aSecondChoice] <= ability[bSecondChoice])
                            score += (ability[aChoice] + ability[bSecondChoice]);
                        else if (ability[aSecondChoice] > ability[bSecondChoice])
                            score += (ability[aSecondChoice] + ability[bChoice]);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(score).append("\n");
        }
        System.out.println(sb);
    }

    public static int getSecondMaxIndex(int x, int y, int skipIndex) {
        int len = grid[x][y].size();
        if (len == 1) {
            return skipIndex; // 그냥 쩔수 없다고 리턴
        }

        int maxAbility = 0;
        int secondMaxIndex = -1;

        for (int idx : grid[x][y]) {
            int currentAbility = ability[idx];
            if (maxAbility < currentAbility && idx != skipIndex) {
                maxAbility = currentAbility;
                secondMaxIndex = idx;
            }
        }

        return secondMaxIndex;
    }

    public static int getMaxIndex(int x, int y) {
        int maxAbility = 0;
        int len = grid[x][y].size();
        int index = -1;
        for (int idx : grid[x][y]) {
            int currentAbility = ability[idx];
            if (maxAbility < currentAbility) {
                maxAbility = currentAbility;
                index = idx;
            }
        }
        return index;
    }

    public static int[] getArea() {
        int[] temp = new int[A];
        for (int i = 0; i < A; i++) {
            int x = ap[i][1] - 1;
            int y = ap[i][0] - 1;
            int mul = ap[i][2];
            temp[i] = ap[i][3];
            boolean[][] vis = new boolean[10][10];

            ArrayDeque<Point> que = new ArrayDeque<>();
            que.offer(new Point(x, y));
            vis[x][y] = true;

            for (int m = 0; m < mul; m++) {
                int len = que.size();
                for (int l = 0; l < len; l++) {
                    Point p = que.poll();
                    grid[p.x][p.y].add(i);

                    for (int dir = 1; dir < 5; dir++) {
                        int nx = p.x + dxs[dir];
                        int ny = p.y + dys[dir];

                        if (isRange(nx, ny) && !vis[nx][ny]) {
                            que.offer(new Point(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
            int len = que.size();
            for (int l = 0; l < len; l++) {
                Point p = que.poll();
                grid[p.x][p.y].add(i);
            }
        }

        return temp;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}