import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int wine = Integer.parseInt(br.readLine());
		
		int[] orig = new int[wine+1];
		for (int i = 1; i <= wine; i++) {
			orig[i] = Integer.parseInt(br.readLine());
		}
		
		// 포도주가 한 잔인 경우
		if(wine == 1) {
			System.out.println(orig[1]);
			return;
		}
		
		int[] d = new int[wine+1];
		d[1] = orig[1];
		d[2] = orig[1] + orig[2];
		for (int i = 3; i <= wine; i++) {
			// 만약 이번 잔을 마시기로 했을 경우 최댓값
			int drink = Math.max(d[i-3] + orig[i-1] + orig[i], d[i-2] + orig[i]);
			// 마시지 않으면 이전 값 유지
			d[i] = Math.max(d[i-1], drink);
		}
		
		int res = 0;
		for (int i : d) {
			res = Math.max(res, i);
		}
		System.out.println(res); 
	}
}