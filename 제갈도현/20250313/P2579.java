package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int s = Integer.parseInt(br.readLine());
		
		int[] orig = new int[s+1];
		for (int i = 1; i <= s; i++) {
			orig[i] = Integer.parseInt(br.readLine());
		}
		
		// 계단이 한 칸인 경우
		if(s == 1) {
			System.out.println(orig[1]);
			return;
		}
		
		int[] d = new int[s+1];
		d[1] = orig[1];
		d[2] = orig[1] + orig[2];
		for (int i = 3; i <= s; i++) {
			d[i] = Math.max(d[i-3] + orig[i-1] + orig[i], d[i-2] + orig[i]);
		}
		
		System.out.println(d[s]);
	}
}