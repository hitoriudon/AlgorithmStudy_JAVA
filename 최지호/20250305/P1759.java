import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
    static int l;
    static int c;
    static char[] result;
    static char[] inputs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inputs = new char[c]; 
        for (int i = 0; i < c; i++) {
            inputs[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(inputs);
        
        result = new char[l];

        comb(0, 0);
    }

    private static void comb(int start, int count) {
        if (count == l) {
            if(check(result)) {
                System.out.println(new String(result));
            }
            return;
        }

        for (int i = start; i < c; i++) {
            result[count] = inputs[i];
            comb(i + 1, count + 1);
        }
    }

    private static boolean check(char[] result) {
        // 모음 vowel, 자음 consonant
        int vo = 0;
        int co = 0;
        for(char c : result) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vo++;
            }
            else {
                co++;
            }
        }
        if(vo < 1) {
            return false;
        }
        if(co < 2) {
            return false;
        }
        return true;
    }
}
