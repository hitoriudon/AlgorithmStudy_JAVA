import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= n; i++) {
			arr[i] += Integer.parseInt(st.nextToken()) + arr[i-1];
		}
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			System.out.println(-arr[Integer.parseInt(st.nextToken())- 1] + arr[Integer.parseInt(st.nextToken())]);
		}
	}
}