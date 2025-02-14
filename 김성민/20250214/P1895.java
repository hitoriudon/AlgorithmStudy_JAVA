import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1895 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(bf.readLine());

		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int[][] map = new int[y][x];

		for (int yCount = 0; yCount < y; yCount++) {
			st = new StringTokenizer(bf.readLine());
			for (int xCount = 0; xCount < x; xCount++) {
				map[yCount][xCount] = Integer.parseInt(st.nextToken());
			}
		}

        // 검사할 숫자
		int checkNum = Integer.parseInt(bf.readLine());

        // 정렬을 위한 리스트
		ArrayList<Integer> filterNums = new ArrayList<>();
        // 검사 숫자 보다 큰 것들 체크
		int numCount = 0;

        // 탐색
		for (int yCount = 0; yCount <= y - 3; yCount++) {
			for (int xCount = 0; xCount <= x - 3; xCount++) {
                // 필터
				for (int curY = yCount; curY < yCount + 3; curY++) {
					for (int curX = xCount; curX < xCount + 3; curX++) {
                        // 필터에 걸리는 값 넣기
						filterNums.add(map[curY][curX]);
					}
				}
                // 필터에 걸린 값 정렬
				Collections.sort(filterNums);
                // 중앙 값 꺼내서 비교
				if (filterNums.get(4) >= checkNum) {
					numCount++;
				}
                // 리스트 초기화
				filterNums.clear();
			}
		}
		System.out.println(numCount);
	}
}