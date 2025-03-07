import java.io.BufferedReader;
import java.io.InputStreamReader;

//  * N-Queen 전략
//  * 만약 이 문제를 완탐으로 풀면 어떻게 될까? nCr이 말도 안 되게 커짐.
//  * 8-Queens이라면, 전체 경우의 수는 64C8 = 4,426,165,368인데
//  * 실제 해는 92개밖에 없음. 즉 44억 개가 넘는 후보 속에서 92개를 최대한 효율적으로 찾아내는 게 관건

//  * 어떤 노드의 유망성을 점검한 후에 유망하지 않타고 결정되면 그 노드의 부모로 되돌아가 다음 자식 노드로 향함

//  * 유망: 어떤 노드를 방문하였을 때 그 노드를 포함한 경로가 해답이 될 수 있는가?
//  * 가지치기: 유망하지 않는 노드가 포함되는 경로는 더 이상 고려하지 않는다(밑으로 탐색되지 않게끔)

public class P9663 {

    static int n;
    static int[] row; // 수정: vis 처리를 하는 게 아닌 행 배열에 열열 넘버를 넣는다
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        row = new int[n];
        answer = 0;
        nQueen(0);
        System.out.println(answer);
    }

    public static void nQueen(int depth) {
        // 종료 조건: 끝 행까지 갔으면, 즉 모든 행에 퀸을 적절히 놨다면
        if (depth == n) {
            answer++;
            return;
        }

        /** 여기가 논리적으로 틀렸음 */
        for (int y = 0; y < n; y++) {
            row[depth] = y;
            if (check(depth))
                nQueen(depth + 1);
        }
    }

    public static boolean check(int col) {
        /**
         * 대각선의 기준을 찾기(행 차이와 열 차이가 같으면 대각선에 위치한다는 것)
         */

        for (int i = 0; i < col; i++) {
            if (row[i] == row[col]) // 열을 탐색했는데 열 요소(행 값)이 같다면
                return false;
            else if (Math.abs(col - i) == Math.abs(row[col] - row[i])) 
                return false;
        }
        return true;
    }
}