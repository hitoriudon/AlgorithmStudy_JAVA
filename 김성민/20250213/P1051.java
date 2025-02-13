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

		int maximum = Math.min(y, x);
		int maxLength = 1;

		for (int length = 1; length < maximum; length++) {
			for (int yCount = 0; yCount + length < y; yCount++) {
				for (int xCount = 0; xCount + length < x; xCount++) {
					int curNum = map.get(yCount).charAt(xCount);
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
