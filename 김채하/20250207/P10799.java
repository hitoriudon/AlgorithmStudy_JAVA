import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pipe = 0;
        int depth = 0;
        char pre = 0;

        for (char value : br.readLine().toCharArray()){
            if (value == '('){
                depth++;
            }else{
                depth--;
                if (pre == '('){
                    pipe += depth;
                }else{
                    pipe++;
                }
            }
            pre = value;
        }
        System.out.print(pipe);

    }

}