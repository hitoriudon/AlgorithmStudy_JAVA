
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P1992 {
    static int n;
    static int[][] grid;
    static ArrayDeque<String> stack;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            String inputString = br.readLine();
            for (int j = 0; j < n; j++){
                grid[i][j] = inputString.charAt(j) - '0';
            }
        }

        stack = new ArrayDeque<>();
        // recursive
        cut(n, 0, 0);
        for(String element: stack){
            System.out.print(element);
        }
        System.out.println();
    }
    
    public static void cut(int len, int x, int y){
        // 기저
        if (isSameColor(len, x, y)){
            if (grid[x][y] == 0){
                stack.offer("0");
            } else {
                stack.offer("1");
            }
            return;
        }

        // 같지 않다면 나누기
        stack.offer("(");
        int halfLen = len / 2;
        cut(halfLen, x, y); // 왼쪽 위
        cut(halfLen, x, y + halfLen); // 오른쪽 위
        cut(halfLen, x + halfLen, y); // 왼쪽 아래
        cut(halfLen, x + halfLen, y + halfLen); // 오른쪽 아래
        stack.offer(")");
    }

    public static boolean isSameColor(int len, int x, int y){
        // 0 이거나 1 * size * size거나 체크하기 위해 다 더해주는 방식을 생각했는데
        // 그것조차 낭비임. 그냥 첫번째 색깔을 기준으로 쭉 비교했는데 한 번이라도 다르다? 그 즉시 false 리턴
        // 그게 시간 덜 쓸듯
        int firstColor = grid[x][y];
        for (int i = x; i < x + len; i++){
            for (int j = y; j < y + len; j++){
                if (firstColor != grid[i][j])
                    return false; 
            }
        }

        return true;
    }
}
