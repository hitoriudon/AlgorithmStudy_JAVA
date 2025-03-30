// 메모리 180924kb, 시간 624ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P12738 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());

		// 리스트의 마지막 값을 비교해 활용하기에 미리 MIN_VALUE를 넣어줌
		// Ai의 최솟값 -10억
		ArrayList<Integer> lst = new ArrayList<>();
		lst.add(Integer.MIN_VALUE);

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < size; i++) {
			int curr = Integer.parseInt(st.nextToken());

			if (curr > lst.get(lst.size() - 1)) {
				// 리스트의 마지막 값보다 크다면 리스트에 추가
				lst.add(curr);
			} else {
				// 리스트의 마지막 값보다 작다면 리스트의 요소를 변경
				// binarySearch를 이용해 변경할 위치 탐색
				int position = Collections.binarySearch(lst, curr);

				// binarySearch로 검색 중 해당 값을 찾으면 해당 인덱스를 반환
				// 해당 값이 없으면 삽입 추천 인덱스를 '-(위치)-1' 형태로 반환
				if (position < 0) {
					position = -(position + 1);
				}

				lst.set(position, curr);
			}
		}

		// LIS의 요소를 구하는게 아닌 길이를 구하는 것이기에 lst.size()로 결과 도출 가능
		// 앞에서 넣어준 MIN_VALUE를 제외한 길이 반환
		System.out.println(lst.size() - 1);
	}
}