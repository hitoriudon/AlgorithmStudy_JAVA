package study.deck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P18115 {
	public static void main(String[] args) throws IOException {
		// 1. head 추가(push), 2. 2번째 추가(poll/pop, push, push(원래 첫 원소)), 3. tail 추가(offer)
		// 그냥 linkedList add 사용이 간편할 듯...
		// 카드 위치를 역으로 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> deck = new LinkedList<>();
//		Deque<Integer> deck = new ArrayDeque<>();
		
		StringBuilder line = new StringBuilder(br.readLine());
		line.reverse();
		StringTokenizer st = new StringTokenizer(line.toString());
		for (int i = 1; i <= N; i++) {
			int skill = Integer.parseInt(st.nextToken());
			switch (skill) {
			case 1: {
//				deck.push(i);
				deck.add(0, i);
				break;
			}
			case 2: {
//				int first = deck.pop();
//				deck.push(i);
//				deck.push(first);
				deck.add(1, i);
				break;
			}
			case 3: {
//				deck.offer(i);
				deck.add(deck.size(), i);  // 새로운 원소 추가
				break;
			}
			}
		}
		
		Object[] deck_arr = deck.toArray();
		StringBuilder sb = new StringBuilder();
		for (Object item : deck_arr) {
			sb.append(item).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
