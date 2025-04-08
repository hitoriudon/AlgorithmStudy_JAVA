import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * loc
 * 
 * [1, 1, 1][2, 2, 2][3, 3, 3]
 * [1, 1, 1][2, 2, 2][3, 3, 3]
 * [1, 1, 1][2, 2, 2][3, 3, 3]
 * [4, 4, 4][5, 5, 5][6, 6, 6]
 * [4, 4, 4][5, 5, 5][6, 6, 6]
 * [4, 4, 4][5, 5, 5][6, 6, 6]
 * [7, 7, 7][8, 8, 8][9, 9, 9]
 * [7, 7, 7][8, 8, 8][9, 9, 9]
 * [7, 7, 7][8, 8, 8][9, 9, 9]
 */
public class Main {
    static int[][] grid;
    static ArrayList<ArrayList<Integer>> lack; // 각 블록(loc 1~9)에서 부족한 숫자들
    static ArrayList<ArrayList<Point>> blankBlocks; // 각 블록의 빈 칸 좌표들
    static boolean solved = false;

    static class Point {
        int x;
        int y;
        int loc; // 해당 셀이 속하는 3×3 블록 번호 (1~9)

        Point(int x, int y, int loc) {
            this.x = x;
            this.y = y;
            this.loc = loc;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        grid = new int[9][9];

        // 스도쿠 판 입력
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // loc 1~9에 대해 인덱스 0은 사용하지 않고, 각 블록별로 빈 칸 정보와 부족한 숫자를 저장할 리스트 초기화
        // 
        //
        //
        //
        lack = new ArrayList<>();
        blankBlocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lack.add(new ArrayList<Integer>());
            blankBlocks.add(new ArrayList<Point>());
        }

        // 각 3×3 블록을 순회하며 처리
        for (int bi = 0; bi < 3; bi++) {
            for (int bj = 0; bj < 3; bj++) {
                int blockNum = bi * 3 + bj + 1; // 블록 번호 1~9
                int[] oneToNine = new int[10];
                ArrayDeque<Point> temp = new ArrayDeque<>();
                // 해당 블록 내 3×3 셀 처리
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        int r = bi * 3 + x;
                        int c = bj * 3 + y;
                        int num = grid[r][c];
                        oneToNine[num]++;
                        if (num == 0) {
                            temp.offer(new Point(r, c, blockNum));
                        }
                    }
                }
                // 빈 칸이 딱 한 개면 바로 채우기
                if (oneToNine[0] == 1) {
                    Point p = temp.poll();
                    for (int idx = 1; idx < 10; idx++) {
                        if (oneToNine[idx] == 0) {
                            grid[p.x][p.y] = idx;
                            break;
                        }
                    }
                } else {
                    // 빈 칸이 여러 개인 경우: 해당 블록의 빈 칸 좌표와 부족한 숫자들을 저장
                    while (!temp.isEmpty()) {
                        Point p = temp.poll();
                        blankBlocks.get(blockNum).add(p);
                    }
                    for (int idx = 1; idx < 10; idx++) {
                        if (oneToNine[idx] == 0) {
                            lack.get(blockNum).add(idx);
                        }
                    }
                }
            }
        }

        // (디버깅용) 초기 스도쿠 판 출력
        // System.out.println("초기 스도쿠 판:");
        // printGrid();

        // 블록 1부터 9까지 순서대로 순열을 이용해 빈 칸 채우기 시작
        solveBlocks(1);

        if (!solved) {
            // System.out.println("스도쿠 해결 불가");
        }
    }

    // block 번호 1~9를 순차적으로 처리
    public static void solveBlocks(int block) {
        if (block > 9) {
            // 모든 블록 채운 후 전체 스도쿠 규칙 검사
            if (isValid()) {
                solved = true;
                // System.out.println("해결된 스도쿠:");
                printGrid();
            }
            return;
        }

        // 현재 블록에 빈 칸이 없다면 다음 블록으로
        if (blankBlocks.get(block).isEmpty()) {
            solveBlocks(block + 1);
            return;
        }

        // 현재 블록의 빈 칸에 대해 순열을 이용해 부족한 숫자들을 배정
        solveBlockPermutation(block, 0);
    }

    // 해당 블록(block)의 빈 칸에 대해 missing 숫자 순열로 배정 (index: 현재 배정할 위치)
    // 
    //
    //
    //
    public static void solveBlockPermutation(int block, int index) {
        // 해당 블록의 missing 숫자 리스트와 빈 칸 리스트
        ArrayList<Integer> missing = lack.get(block);
        ArrayList<Point> blanks = blankBlocks.get(block);

        if (index == missing.size()) {
            // 해당 블록에 모든 빈 칸을 채웠다면, 현재까지의 grid가 유효한지 검사 후 다음 블록 처리
            if (isValid()) {
                solveBlocks(block + 1);
            }
            return;
        }

        // 순열 생성: missing 리스트의 index부터 끝까지 swap 하면서 배정
        for (int i = index; i < missing.size(); i++) {
            // swap
            int temp = missing.get(index);
            missing.set(index, missing.get(i));
            missing.set(i, temp);

            // 빈 칸 리스트에서 index번째 셀에 missing[index] 숫자 할당
            Point p = blanks.get(index);
            grid[p.x][p.y] = missing.get(index);

            solveBlockPermutation(block, index + 1);
            if (solved)
                return;

            // 백트래킹: 할당 원복
            grid[p.x][p.y] = 0;
            // swap 복구
            temp = missing.get(index);
            missing.set(index, missing.get(i));
            missing.set(i, temp);
        }
    }

    // 전체 grid가 스도쿠 규칙(행, 열, 3×3 블록 중복 없음)을 만족하는지 검사
    public static boolean isValid() {
        // 행 검사
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int num = grid[i][j];
                if (num != 0) {
                    if (seen[num])
                        return false;
                    seen[num] = true;
                }
            }
        }
        // 열 검사
        for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int num = grid[i][j];
                if (num != 0) {
                    if (seen[num])
                        return false;
                    seen[num] = true;
                }
            }
        }
        // 3×3 블록 검사
        for (int b = 0; b < 9; b++) {
            boolean[] seen = new boolean[10];
            int startRow = (b / 3) * 3;
            int startCol = (b % 3) * 3;
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    int num = grid[i][j];
                    if (num != 0) {
                        if (seen[num])
                            return false;
                        seen[num] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}