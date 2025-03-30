// 메모리 28352kb, 시간 244ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int house = Integer.parseInt(st.nextToken());
		int router = Integer.parseInt(st.nextToken());

		int[] houseList = new int[house];
		for (int i = 0; i < house; i++) {
			houseList[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houseList);

		int left = 1, mid = 0, right = houseList[house - 1];
		int cnt, last, res = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			
			// 첫 집 무조건 설치
			last = houseList[0];
			cnt = 1;
			
			// 다음 집이 최소 거리보다 멀다면 카운트 증가
			for (int i = 1; i < house; i++) {
				if (houseList[i] - last >= mid) {
					cnt++;
					last = houseList[i];
				}
			}
			
			if (cnt < router) {
				// 만약 설치한 공유기가 적다면 최소 거리 감소
				right = mid - 1;
			} else {
				// 설치한 공유기가 많다면 최소 거리 증가
				left = mid + 1;
				res = mid;
			}
		}
		
		System.out.println(res);
	}
}
