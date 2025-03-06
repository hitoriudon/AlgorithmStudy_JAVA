import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
    static int[][] map;
    static int white = 0;
    static int black = 0;;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //자르고 확인, 자르고, 확인
        divide(0,0,n);
    }

    private static void divide(int x, int y, int size) {
        if(check(x,y,size)) {
            if(map[x][y] == 0) {
                white++;
            }
            else {
                black++;
            }
            return;
        }
        //나누기
        int nSize = size / 2;

        divide(x, y, nSize);
        divide(x + nSize, y, nSize);
        divide(x, y + nSize, nSize);
        divide(x + nSize, y + nSize, nSize);
    }

    private static boolean check(int x, int y, int size) {
        int color = map[x][y];

		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
                //첫 색상 기준으로 다르면 false return;
				if(map[i][j] != color) {
					return false;
				}
			}
		}
		// 검사가 모두 통과했다는 의미이므로 true 리턴
		return true;
	}
}
