import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        if(n < 100) {
            System.out.println(n);
            return;
        }
    
        int count = 99;
        // 3자리 숫자: 십의자리 = (일의자리 + 백의자리) / 2 가 되면 counting 
        for(int i = 111; i <= n; i++) {
               if((i / 10) % 10 == ((i % 10) + (i / 100)) / 2 && ((i % 10) + (i / 100)) % 2 == 0) {
                count ++;
               } 
        }
        System.out.println(count);
    }    
}
