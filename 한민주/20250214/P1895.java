import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1895 {

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] arr = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }   

        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    list.add(arr[i+j][k]);
                }
            }
            Collections.sort(list);
            answerList.add(list.get(4));

            if (c == 3)
            {
                list.clear();
                continue;
            }
                
            for (int j = 1; j < c - 2; j++) {
                for (int k = 0; k < 3; k++) {
                    list.remove(list.indexOf(arr[k + i][j - 1]));
                    list.add(arr[k + i][j + 2]);

                }
                Collections.sort(list);
                answerList.add(list.get(4));
                
            }
            list.clear();
        }

        int t = Integer.parseInt(br.readLine());
        answerList.add(t);
        Collections.sort(answerList);
        int ans = answerList.size() - answerList.indexOf(t)-1;
        // int idx = answerList.indexOf(t) - 1;
        // while (idx >= 0 && answerList.get(idx) == t) {
        //     ans++;
        //     idx--;
        // }
        System.out.println(ans);
    }
}