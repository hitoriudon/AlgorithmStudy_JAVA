import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class P11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int n = Integer.parseInt(br.readLine());
		Integer[] atm = new Integer[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			atm[i] = Integer.parseInt(st.nextToken());
		}
        //역순으로 정렬 후 규칙에 따라 곱해주기
		Arrays.sort(atm, Collections.reverseOrder());
		for(int i = 0; i < n; i++) {
			sum += (i + 1) * atm[i];
		}
		System.out.println(sum);
	}
}