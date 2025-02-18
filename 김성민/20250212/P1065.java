import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());
		int hansuCount = 0;
		if (num >= 100) {
			hansuCount += 99;
			for (int numCount = 101; numCount <= num; numCount++) {
				if ((numCount / 100) - (numCount / 10 % 10) == (numCount / 10 % 10) - (numCount % 10)) {
					hansuCount++;
				}
			}
		} else {
			hansuCount = num;
		}

		System.out.println(hansuCount);
	}

}
