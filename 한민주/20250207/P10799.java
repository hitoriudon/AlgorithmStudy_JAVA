import java.io.BufferedReader;
import java.io.InputStreamReader;

class P10799 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().replace("()", "0");
   
        int stack = 0;
        int sum = 0;
        char now;
        for(int i = 0; i < str.length(); i++){
            now = str.charAt(i);

            switch(now){
                case '(':
                    stack++;
                    break;
                case ')':
                    stack--;
                    sum++;
                    break;
                default:
                    sum += stack;
            }
        }
        

        System.out.println(sum);
	}
}