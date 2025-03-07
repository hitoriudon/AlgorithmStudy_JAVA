
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
    static int n;
    static int[][] grid;
    static int white = 0; // 자르고 난 뒤의 하얀 색종이 값
    static int blue = 0; // 이하 동
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // recursive
        cut(n, 0, 0, 0);
        System.out.println(white + "\n" + blue);
    }
    
    public static void cut(int len, int x, int y, int side){
        // 기저
        if (isSameColor(len, x, y)){
            if (grid[x][y] == 0){
                white++;
            } else {
                blue++;
            }
            System.out.println(x + " " + y + " " + len + ", side: " + side);
            return;
        }

        // 같지 않다면 나누기
        int halfLen = len / 2;
        cut(halfLen, x, y, 0); // 왼쪽 위
        cut(halfLen, x, y + halfLen, 1); // 오른쪽 위
        cut(halfLen, x + halfLen, y, 2); // 왼쪽 아래
        cut(halfLen, x + halfLen, y + halfLen, 3); // 오른쪽 아래
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

// 8
// 1 1 0 0 0 0 1 1
// 1 1 0 0 0 0 1 1
// 0 0 0 0 1 1 0 0
// 0 0 0 0 1 1 0 0
// 1 0 0 0 1 1 1 1
// 0 1 0 0 1 1 1 1
// 0 0 1 1 1 1 1 1
// 0 0 1 1 1 1 1 1

// 8
// 1 1 1 1 0 0 0 0
// 1 1 1 1 0 0 0 0
// 0 0 0 1 1 1 0 0
// 0 0 0 1 1 1 0 0
// 1 1 1 1 0 0 0 0 
// 1 1 1 1 0 0 0 0 
// 1 1 1 1 0 0 1 1
// 1 1 1 1 0 0 1 1
