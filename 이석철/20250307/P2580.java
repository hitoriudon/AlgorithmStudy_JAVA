/** 
 * 시간 초과. 가지치기 잘 했다고 생각했는데 그냥 재귀 형태로 하는 게 맞는듯
 */
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
public class P2580 {
    static int[][] grid;
    static ArrayList<ArrayList<Integer>> lack; // �� ����(loc 1~9)���� ������ ���ڵ�
    static ArrayList<ArrayList<Point>> blankBlocks; // �� ������ �� ĭ ��ǥ��
    static boolean solved = false;

    static class Point {
        int x;
        int y;
        int loc; // �ش� ���� ���ϴ� 3��3 ���� ��ȣ (1~9)

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

        // ������ �� �Է�
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // loc 1~9�� ���� �ε��� 0�� ������� �ʰ�, �� ���Ϻ��� �� ĭ ������ ������ ���ڸ� ������ ����Ʈ �ʱ�ȭ
        // 
        lack = new ArrayList<>();
        blankBlocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lack.add(new ArrayList<Integer>());
            blankBlocks.add(new ArrayList<Point>());
        }

        // �� 3��3 ������ ��ȸ�ϸ� ó��
        for (int bi = 0; bi < 3; bi++) {
            for (int bj = 0; bj < 3; bj++) {
                int blockNum = bi * 3 + bj + 1; // ���� ��ȣ 1~9
                int[] oneToNine = new int[10];
                ArrayDeque<Point> temp = new ArrayDeque<>();
                // �ش� ���� �� 3��3 �� ó��
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
                // �� ĭ�� �� �� ���� �ٷ� ä���
                if (oneToNine[0] == 1) {
                    Point p = temp.poll();
                    for (int idx = 1; idx < 10; idx++) {
                        if (oneToNine[idx] == 0) {
                            grid[p.x][p.y] = idx;
                            break;
                        }
                    }
                } else {
                    // �� ĭ�� ���� ���� ���: �ش� ������ �� ĭ ��ǥ�� ������ ���ڵ��� ����
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

        // ���� 1���� 9���� ������� ������ �̿��� �� ĭ ä��� ����
        solveBlocks(1);

    }

    // block ��ȣ 1~9�� ���������� ó��
    public static void solveBlocks(int block) {
        if (block > 9) {
            // ��� ���� ä�� �� ��ü ������ ��Ģ �˻�
            if (isValid()) {
                solved = true;
                printGrid();
            }
            return;
        }

        // ���� ���Ͽ� �� ĭ�� ���ٸ� ���� ��������
        if (blankBlocks.get(block).isEmpty()) {
            solveBlocks(block + 1);
            return;
        }

        // ���� ������ �� ĭ�� ���� ������ �̿��� ������ ���ڵ��� ����
        solveBlockPermutation(block, 0);
    }

    // �ش� ����(block)�� �� ĭ�� ���� missing ���� ������ ���� (index: ���� ������ ��ġ)
    // 
    public static void solveBlockPermutation(int block, int index) {
        // �ش� ������ missing ���� ����Ʈ�� �� ĭ ����Ʈ
        ArrayList<Integer> missing = lack.get(block);
        ArrayList<Point> blanks = blankBlocks.get(block);

        if (index == missing.size()) {
            // �ش� ���Ͽ� ��� �� ĭ�� ä���ٸ�, ��������� grid�� ��ȿ���� �˻� �� ���� ���� ó��
            if (isValid()) {
                solveBlocks(block + 1);
            }
            return;
        }

        // ���� ����: missing ����Ʈ�� index���� ������ swap �ϸ鼭 ����
        for (int i = index; i < missing.size(); i++) {
            // swap
            int temp = missing.get(index);
            missing.set(index, missing.get(i));
            missing.set(i, temp);

            // �� ĭ ����Ʈ���� index��° ���� missing[index] ���� �Ҵ�
            Point p = blanks.get(index);
            grid[p.x][p.y] = missing.get(index);

            solveBlockPermutation(block, index + 1);
            if (solved)
                return;

            // ��Ʈ��ŷ: �Ҵ� ����
            grid[p.x][p.y] = 0;
            // swap ����
            temp = missing.get(index);
            missing.set(index, missing.get(i));
            missing.set(i, temp);
        }
    }

    // ��ü grid�� ������ ��Ģ(��, ��, 3��3 ���� �ߺ� ����)�� �����ϴ��� �˻�
    public static boolean isValid() {
        // �� �˻�
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
        // �� �˻�
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
        // 3��3 ���� �˻�
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
