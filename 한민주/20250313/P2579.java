package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2579 {
	static int scores[][];
	static int stairs[];
	static int answer;
	static int n;

	static void up(int stair, int cnt, int score) {
		// 다 올라왔는지 체크
		if (stair == n-1) {
			answer = Math.max(answer, score + stairs[n-1]);
			return;
		} else if (stair >= n) {
			return;
		}

		// 현재 점수
		int nowS = score + stairs[stair];

		// 이전 점수와 비교
		if (scores[stair][cnt] >= nowS) {
			return;
		} else {
			scores[stair][cnt] = nowS;
		}

		// 다음 계단
		up(stair + 2, 1, nowS);
		if (cnt < 2) {
			up(stair + 1, cnt + 1, nowS);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		answer = 0;

		scores = new int[n][3];
		stairs = new int[n];

		for (int i = 0; i < n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		up(0, 1, 0);
		up(1, 1, 0);
		


		System.out.println(answer);
	}

}
