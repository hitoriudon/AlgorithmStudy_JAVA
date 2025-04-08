/**
 * 전깃줄
 * 14268kb	108ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P2565 {
    static ArrayList<Integer> A;
    static ArrayList<Integer> B;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        A = new ArrayList<>();
        B = new ArrayList<>();

        HashMap<Integer, Integer> line = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A.add(a);

            line.put(a, b);
        }
        
        Collections.sort(A);
        for (int i = 0; i < n; i++){
            int a = A.get(i);
            B.add(line.get(a));
        }
        
        Solution2();
    }
    
    // Binary Search, n이 클수록 유리 (O(n))
    public static void Solution2(){
        ArrayList<Integer> lis = new ArrayList<>();

        int len = 0;
        for (int i = 0; i < n; i++){
            int value = B.get(i);
            int idx = Collections.binarySearch(lis, value);

            if (idx < 0){
                idx = -(idx + 1);
            }

            if (idx == len){
                lis.add(value);
                len++;
            } else {
                lis.set(idx, value);
            }
        }

        System.out.println(n - len);
    }

    // DP, n이 크면 못씀 (O(n^2))
    public static void Solution1(){
        int[] dp = new int[n];
        for (int i = 0; i < n; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (dp[i] < dp[j] + 1 && B.get(j) < B.get(i)){
                    dp[i] = dp[j] + 1;
                }
            }
        }
    
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, dp[i]);
        }
    
        System.out.println(n - max);
    }
}