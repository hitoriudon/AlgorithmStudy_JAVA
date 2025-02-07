import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P10773 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int res = 0;
		
		int K = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int k = 0; k < K; k++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				q.removeLast();
			} else {
				q.addLast(input);
			}
		}
		
		for (int i : q) {
			res += i;
		}
		System.out.println(res);
	}
}