import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11441 합구하기
public class P11441 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i] += arr[i-1];
		}
		
		int answer = 0;
		int m = Integer.parseInt(br.readLine());
		for(int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			answer = arr[end] - arr[start - 1];
			System.out.println(answer);
		}
	}
}

//10 30 60 100 150