import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1051 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		ArrayList<String> map = new ArrayList<>();
		for (int line = 0; line < y; line++) {
			map.add(br.readLine());
		}

		// 정사각형이기에 x열과 y열 중 더 짧은 것이 길이의 맥시멈
		int maximum = Math.min(y, x);
		int maxLength = 1;

		// 여기서 length는 정사각형의 length가 아닌 옆으로 뻗어나기는 길이 1부터 확인
		for (int length = 1; length < maximum; length++) {
			// y 한줄씩 내려가기 length와 더했을때 넘어가지 않게 체크
			for (int yCount = 0; yCount + length < y; yCount++) {
				// x 한줄씩 내려가기 length와 더했을때 넘어가지 않게 체크
				for (int xCount = 0; xCount + length < x; xCount++) {
					// 왼쪽 위 꼭지점 저장
					int curNum = map.get(yCount).charAt(xCount);

					// 나머지 꼭지점과 비교해보며 성공하면 갱신
					if (map.get(yCount + length).charAt(xCount) == curNum
							&& map.get(yCount).charAt(xCount + length) == curNum
							&& map.get(yCount + length).charAt(xCount + length) == curNum) {
						maxLength = length + 1;
					}
				}
			}
		}

		System.out.println(maxLength * maxLength);

	}

}
