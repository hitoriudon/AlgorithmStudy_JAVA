package no;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P18111 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n, m, b;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		int[] map = new int[n * m];

		int idx = 0;
		int maxV = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[idx++] = Integer.parseInt(st.nextToken());
				maxV = Math.max(maxV, map[idx-1]);
			}
		}

		int h = maxV;
		int ansH = h;
		int plus;
		int minus;
		int time = Integer.MAX_VALUE;
		while (h >= 0) {
			plus = 0;
			minus = 0;
			for (int i = 0; i < n * m; i++) {
				if (map[i] - h < 0) {
					minus += Math.abs(map[i] - h);
				} else {
					plus += map[i] - h;
				}
			}
			if (b + plus >= minus && time > minus+plus*2) {
				time = minus+plus*2;
				ansH = h;
				
			} 
			h--;
		}
		
		
		System.out.println(time + " " +  ansH);
		

	}
}
