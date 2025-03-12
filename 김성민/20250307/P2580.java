import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2580 {
	static int[][] map = new int[9][9];
	// 0의 위치를 담고 있는 리스트
	static ArrayList<int[]> zeroList = new ArrayList<>();
	// y라인 1~9 사용한 적이 있는지 기록용
	static boolean[][] yCheck = new boolean[9][10];
	// x라인 1~9 사용한 적이 있는지 기록용
	static boolean[][] xCheck = new boolean[9][10];
	// 3x3을 구역으로 나눠 1~9 사용한 적이 있는지 기록용
	static boolean[][] areaCheck = new boolean[9][10];

	// 완성 확인 플래그
	static boolean clear = false;

	// 몇번째 구역인지 확인한는 함수
	// 왼쪽 위부터 0,1,2
	// 3,4,5
	// 6,7,8
	static int checkAreaNum(int y, int x) {
		return ((y / 3) * 3) + (x / 3);
	}

	static void dfs(int zeroIdx) {
		// 끝까지 완성됐다면 클리어
		if (zeroIdx == zeroList.size()) {
			clear = true;
			return;
		}

		int[] curPos = zeroList.get(zeroIdx);
		int curY = curPos[0];
		int curX = curPos[1];

		for (int num = 1; num <= 9; num++) {
			// 한 군데에서 라도 사용한적 없는 숫자라면 다음 0을 찾아서 들어가기
			if (!yCheck[curY][num] && !xCheck[curX][num] && !areaCheck[checkAreaNum(curY, curX)][num]) {
				// 전부 사용체크
				yCheck[curY][num] = true;
				xCheck[curX][num] = true;
				areaCheck[checkAreaNum(curY, curX)][num] = true;
				// 대입 후 다음 0을 찾아 들어가기
				// 불가능하다면 다른 숫자로 덮일거기 때문에 0으로 초기화X
				map[curY][curX] = num;
				dfs(zeroIdx + 1);
				// 전부 사용 다시 해제
				yCheck[curY][num] = false;
				xCheck[curX][num] = false;
				areaCheck[checkAreaNum(curY, curX)][num] = false;
			}
			
			// 완성이 되었다면 정지
			if(clear) {
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		for (int yCount = 0; yCount < 9; yCount++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int xCount = 0; xCount < 9; xCount++) {
				int curNum = Integer.parseInt(st.nextToken());
				map[yCount][xCount] = curNum;

				if (curNum == 0) {
					zeroList.add(new int[] { yCount, xCount });
				} else {
					yCheck[yCount][curNum] = true;
					xCheck[xCount][curNum] = true;
					areaCheck[checkAreaNum(yCount, xCount)][curNum] = true;
				}
			}
		}
		
		dfs(0);
		
		for(int[] line : map) {
			for(int num : line) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
