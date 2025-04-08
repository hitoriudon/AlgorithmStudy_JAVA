import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P2578
 */
public class P2578 {

    public static int check(List<Integer[]> list){
        // 가로 5줄, 세로 5줄, 대각선 2줄 총 12줄 체크하면 됨
        int bingo = 0;
        for (int i = 0; i < 5; i++){
            // 가로
            if (list.get(i)[0] == 0 && list.get(i)[1] == 0 && list.get(i)[2] == 0 && list.get(i)[3] == 0 && list.get(i)[4] == 0) {
                bingo++;
            }

            // 세로
            if (list.get(0)[i] == 0 && list.get(1)[i] == 0 && list.get(2)[i] == 0 && list.get(3)[i] == 0 && list.get(4)[i] == 0) {
                bingo++;
            }
        }
        if (list.get(0)[0] == 0 && list.get(1)[1] == 0 && list.get(2)[2] == 0 && list.get(3)[3] == 0 && list.get(4)[4] == 0) {
            bingo++;
        }
        if (list.get(4)[0] == 0 && list.get(3)[1] == 0 && list.get(2)[2] == 0 && list.get(1)[3] == 0 && list.get(0)[4] == 0) {
            bingo++;
        }

        return bingo;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 2차원 배열 선언
        List<Integer[]> board = new ArrayList<Integer[]>();
        
        // 2차원 배열 채우기
        for (int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer[] temp = new Integer[5];
            for (int j = 0; j < 5; j++){
                temp[j] = Integer.parseInt(st.nextToken());
            }
            board.add(temp);
        }
        
        // 사회자 숫자 배열
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++){
                numbers.add(Integer.parseInt(st.nextToken()));
            }
        }
        
        // 완탐으로 찾고, 찾으면 숫자 0으로 바꿔주기. X 표시한 이후엔 빙고 줄 완성됐나 체크하기
        for (int index = 0; index < 25; index++){
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (board.get(i)[j] == numbers.get(index)){
                        board.get(i)[j] = 0;

                        if (check(board) >= 3){
                            System.out.println(index + 1);
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }
}