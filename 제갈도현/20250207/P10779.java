import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] lst = br.readLine().toCharArray();
		int q = 0, res = 0;
		
		int len = lst.length;
		for (int i = 0; i < len; i++) {
			char curr = lst[i];
			if (curr == '(') {
				q += 1;
			} else {
				if (i != 0 && lst[i-1] == '(') {
					res += --q;
				} else {
					q--;
					res += 1;
				}
			}
		}
		
		System.out.println(res);
	}
}