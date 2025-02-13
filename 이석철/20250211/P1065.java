import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class P1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int num = 99;

        if (N <= 99) {
            System.out.println(N);
            return;
        } else if (N <= 110) {
            System.out.println(num);
            return;
        }

        for (int n = 111; n <= N; n++) {
            if (isHansu(n)) {
                num++;
            }
        }
        System.out.println(num);
    }

    private static boolean isHansu(int number) {
        String strNum = String.valueOf(number);
        int len = strNum.length();
        
        if (len < 3) return true; 

        Set<Integer> diffs = new HashSet<>();
        for (int i = 0; i < len - 1; i++) {
            int diff = (strNum.charAt(i + 1) - '0') - (strNum.charAt(i) - '0');
            diffs.add(diff);
        }

        return diffs.size() == 1;
    }
}
