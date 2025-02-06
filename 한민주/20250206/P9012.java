import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class P9012{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            int openCnt = 0;
            String answer = "YES";
            String input = br.readLine();
            
            for(char token : input.toCharArray()){
                if(token == '('){
                    openCnt++;
                }
                else{
                    openCnt--;
                    if(openCnt < 0) break;
                }         
            }
            if(openCnt != 0) answer = "NO";

            sb.append(answer);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}