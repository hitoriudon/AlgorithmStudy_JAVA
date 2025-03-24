import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2167 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int[][] map = new int[y + 1][x + 1];

		for (int yCount = 1; yCount <= y; yCount++) {
			st = new StringTokenizer(br.readLine());
			for (int xCount = 1; xCount <= x; xCount++) {
				map[yCount][xCount] = Integer.parseInt(st.nextToken());
			}
		}

		int caseAmount = Integer.parseInt(br.readLine());
		for (int caseCount = 0; caseCount < caseAmount; caseCount++) {
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());

			// 직접 시작부터 끝까지 합치기
			int sum = 0;
			for (int countY = startY; countY <= endY; countY++) {
				for (int countX = startX; countX <= endX; countX++) {
					sum += map[countY][countX];
				}
			}
			sb.append(sum).append("\n");
		}

		System.out.println(sb);
	}
}
