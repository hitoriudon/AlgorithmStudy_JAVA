package study.rearrange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] wait_arr = new int[N];
		for (int i = 0; i < N; i++) {
			wait_arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(wait_arr);
		int total_time = 0;
		for (int i = 0; i < N; i++) {
			total_time += wait_arr[i]*(N-i);
		}
		System.out.println(total_time);
	}
}