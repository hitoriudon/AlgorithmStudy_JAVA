package study.deck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P1021 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] items = new int[M];
		List<Integer> deck = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			deck.add(i + 1);  // 원소에 맞게 +1
		}
				
		StringTokenizer line = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			items[i] = Integer.parseInt(line.nextToken());
		}
		
		int count = 0;
		int idxM = 0;
		while (idxM != M) {
//			count += check(idxN, items, idxM, N);
			if (deck.get(0) == items[idxM]) {
				deck.remove(0);
				idxM++;
				continue;
			}
			int idx = deck.indexOf(items[idxM]);
			// 오른쪽이 더 짧은 경우
			if (idx < deck.size() - idx) {
//				System.out.println(items[idxM]+" "+idx);
				for (int i = 0; i < idx; i++) {
					int temp = deck.remove(0);
					deck.add(temp);
					count++;
				}
			}
			else {
//				System.out.println(items[idxM]+" "+(deck.size()-idx));
				for (int i = 0; i < deck.size() - idx; i++) {
					int temp = deck.remove(deck.size() - 1);
					deck.add(0, temp);
					count++;
				}
			}
			
//			idxN = items[idxM] + 1;
//			idxM++;
			// idxN 처리
//			if (idxN == N - 1) {
//				for (int j = 0; j < idxM; j++) {
//					if (idxN == items[j]) {
//						idxN = 0;
//						break;
//					}
//				}
//			}
//			if (idxN >= N) {
//				idxN -= N;
//			}
//			if (idxN < 0) {
//				idxN += N;
//			}
		}
		System.out.println(count);
	}
	
	// Array 구현 시 너무 복잡해진다....
//	public static int check(int idxN, int[] items, int idxM, int N) {
//		int currItem = items[idxM];  // index와 맞추기
////		System.out.println(currItem +  " " + idxN);
//		
//		// 같으면 바로 반환
//		if (currItem == idxN) {
//			return 0;
//		}
//		
//		// 기본 값
//		int high;
//		int low;
//		if (idxN > currItem) {
//			high = idxN;
//			low = currItem;
//		}
//		else {
//			high = currItem;
//			low = idxN;
//		}
//		int innerDist = high - low;  // 두 값 사이
//		int outerDist = N - innerDist;  // 두 값 바깥
//		
//		// 빈 칸 점프
//		for (int i = 0; i < idxM; i++) {
//			if (low <= items[i] && items[i] <= high) {
//				innerDist--;
//			}
//			if (items[i] >= high || items[i] <= low) {
//				outerDist--;
//			}
//		}
////		System.out.println(innerDist +  " " + outerDist);
//		return Math.min(innerDist, outerDist);
//	}
}
