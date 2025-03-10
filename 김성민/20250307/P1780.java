import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
	static int mapSize;
	static int[][] map;

	// 순서대로 -1, 0, 1 의 갯수 카운트
	static int[] counts = new int[] { 0, 0, 0 };

	static void dfs(int curLength, int curY, int curX) {
		int curNum = map[curY][curX];

		boolean otherCheck = false;
		for (int yCount = curY; yCount < curY + curLength; yCount++) {
			for (int xCount = curX; xCount < curX + curLength; xCount++) {
				if (map[yCount][xCount] != curNum) {
					otherCheck = true;
					break;
				}
			}
			if (otherCheck) {
				break;
			}
		}

		if (otherCheck) {
			int divideLength = curLength / 3;
			for (int yDivide = 0; yDivide < 3; yDivide++) {
				for (int xDivide = 0; xDivide < 3; xDivide++) {
					dfs(divideLength, curY + divideLength * yDivide, curX + divideLength * xDivide);
				}
			}
		} else {
			// +1을 해서 배열에서 벗어나지 않게 카운팅
			counts[curNum + 1]++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		for (int yCount = 0; yCount < mapSize; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int xCount = 0; xCount < mapSize; xCount++) {
				map[yCount][xCount] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(mapSize, 0, 0);

		for (int count : counts) {
			sb.append(count).append("\n");
		}

		System.out.println(sb);
	}

}
