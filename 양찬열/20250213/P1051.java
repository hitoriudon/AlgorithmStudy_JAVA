// 숫자 정사각형
// 실수: 사이즈 업데이트 시만 break, 길이는 좌표 차 + 1
// 알고리즘적 오류: 원점 기준 x축, y축으로 동일한 위치만 확인하고, 정작 x+dx, y+dy에 해당 값이 있는지 확인 X
// 메모리: 약 2배, 시간: 약 5배 -> 다른 방법: 2중 반복문으로 모든 점을 방문, 그릴 수 있는 최대 사각형 크기(위->아래, 왼쪽->오른쪽) 계산 후 큰 사각형부터 나머지 3자리의 꼭짓점 비교
// 3중 반복문을 막기 위해 map을 이용해 반복 횟수를 줄이려 했으나, 오히려 시간이 늘어났다...
// Map을 사용해 반복 횟수를 줄이려는 시도는 구조를 복잡하게 만들어 오히려 데이터 삽입/삭제, 정렬 등에서 오버헤드가 발생하고, 많은 좌표를 다루는 상황에서는 내부 반복문이 크게 줄어들지 않아 전체 성능이 저하되는 결과를 초래한 것입니다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1051 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		Map<Character, List> nums = new HashMap<>();  // 같은 숫자끼리 모아 처리
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char num = line.charAt(j);
				map[i][j] = num;
				if (!nums.containsKey(num)) nums.put(num, new ArrayList<>());
				nums.get(num).add(new int[] {i, j});  // 같은 값끼리 좌표 저장
			}
		}
		
		int maxSize = 1;  // 초기값
		for (Character key: nums.keySet()) {
			List<int[]> positions = nums.get(key);
			if (positions.size() < 4) {
				continue;
			}
//			System.out.println("key: " + key);
//			System.out.println(positions.size());
			
			for (int i = 0; i < positions.size(); i++) {
				PriorityQueue<Integer> xLength = new PriorityQueue<>((l1, l2) -> l2 - l1);  // 큰 값부터 나열
				PriorityQueue<Integer> yLength = new PriorityQueue<>((l1, l2) -> l2 - l1);
				
				for (int j = i + 1; j < positions.size(); j++) {  // 조합
					int[] firstP = positions.get(i);
					int[] secondP = positions.get(j);
					if (firstP[0] == secondP[0]) {  // x 축 같음
						xLength.add(secondP[1] - firstP[1] + 1);  // 길이는 +1 해야...
//						System.out.println("xLength: " + (firstP[1] - secondP[1]));
					}
					if (firstP[1] == secondP[1]) {  // y 축 같음
						yLength.add(secondP[0] - firstP[0] + 1);
//						System.out.println("yLength: " + (firstP[0] - secondP[0]));
					}
				}
				
				// x 길이와 y 길이 비교하며 최대 값 찾기
//				System.out.println(xLength.peek() + " " + yLength.peek());
				while(xLength.size() > 0 && yLength.size() > 0) {
//					System.out.println(xLength.peek() + " " + yLength.peek());
					if (xLength.peek() == yLength.peek() && map[positions.get(i)[0] + xLength.peek() - 1][positions.get(i)[1] + yLength.peek() - 1] == key) {  // 대각선 방향 꼭지점 확인
						if (xLength.peek() * xLength.peek() > maxSize) {
							maxSize = xLength.peek() * xLength.peek();
						}
						break;
					}
					else if (xLength.peek() > yLength.peek()) {
						xLength.poll();
					}
					else {
						yLength.poll();
					}
				}
			}
		}
		System.out.println(maxSize);
	}
}
