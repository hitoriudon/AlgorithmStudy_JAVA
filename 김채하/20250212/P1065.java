import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 99;
        int number, gap;
        for (int i = 111; i <= N; i++) {
            gap = (i / 10 % 10) - i % 10;
            number = i / 10;
            while (number >= 100) {
                if ((number / 10) % 10 - number % 10 != gap) {
                    break;
                }
                number = number / 10;
            }
            if ((number / 10) % 10 - number % 10 == gap) {
                answer++;
            }
        }
        System.out.println(Math.min(answer, N));
    }
}