package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] d = new int[N+1];
		// 1부터 N까지 숫자를 증가시켜가며 횟수 계산
		for (int i = 2; i <= N; i++) {
			d[i] = d[i-1] + 1;
						
			// 현재 숫자가 2로 나눠떨어지면 i/2의 횟수에서 1 증가
			if (i % 2 == 0) {
				d[i] = Math.min(d[i], d[i/2] + 1);
			}
			
			// 현재 숫자가 3으로 나눠떨어지면 i/3의 횟수에서 1 증가
			if (i % 3 == 0) {
				d[i] = Math.min(d[i], d[i/3] + 1);
			}
		}
		
		System.out.println(d[N]);
	}
}