import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * P1365
 */
public class P1365 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> lis = new ArrayList<>();

        int len = 0;
        for (int i = 0; i < n; i++){
            int idx = Collections.binarySearch(lis, arr[i]);

            if (idx < 0) idx = -(idx + 1);

            if (idx == len){
                lis.add(arr[i]);
                len++;
            } else {
                lis.set(idx, arr[i]);
            }
        }
        
        System.out.println(n - len);
    }
}