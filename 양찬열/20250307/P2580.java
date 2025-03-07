package study.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 스도쿠: 아직 안 맞춘 좌표값을 queue에 넣고 하나씩 채워가며 반복? -> 여러 후보 중 하나를 선택하는 경우도 존재해 시간 초과
// 가능한 값을 넣고 전부 시도해보는 back tracking으로 해야할 듯
// 이전 bit masking 값을 넘겨주는 dp
public class P2580 {
	static int[][] map = new int[9][9];
	static List<Position> list = new ArrayList<>();
	static boolean flag;
	static int[] rows, cols, recs;
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		list.clear();
		rows = new int[9];
		cols = new int[9];
		recs = new int[9];  // (x/3)*3 + (y/3)
		for (int r = 0; r < 9; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++) {
				int value = Integer.parseInt(st.nextToken());
				map[r][c] = value;
				rows[r] |= 1 << value;
				cols[c] |= 1 << value;
				recs[3*(r/3) + c/3] |= 1 << value;
				if (value == 0) {
					list.add(new Position(r, c));
				}
			}
		}

		flag = false;
		findAnswer(0);
		
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void findAnswer(int idx) {
		// TODO Auto-generated method stub
		if (idx == list.size()) {
			flag = true;
			return;
		}

		Position pos = list.get(idx);
		Set<Integer> candidates = new HashSet<>();  // 중복 제거

		// 실수: 후보가 1개일 때 무작정 시도하면, 다른 값의 시도값과 중복되어 문제 발생!
		// 실수: 후보군끼리 필터링 X!
		// 행+열+사각형 확인
		int startX = pos.x/3;  // 3개씩 나눌 때 시작점은 3으로 나눈 나머지가 0 -> 몫 * 나눈 값
		int startY = pos.y/3;
		
		for (int i = 1; i < 10; i++) {
			// 실수: not 연산 사용 시 부호 비트까지 반전된다!
			if ((rows[pos.x] & (1 << i)) == 0 && (cols[pos.y] & (1 << i)) == 0 && (recs[startX*3 + startY] & 1 << i) == 0) {  // 아직 사용하지 않은 후보
				candidates.add(i);
			}
		}
		
		for (int value : candidates) {  // 모든 후보 시도
			if (flag) {  // 모두 완성 시 return;
				return;
			}
			map[pos.x][pos.y] = value;
			rows[pos.x] |= 1<<value;
			cols[pos.y] |= 1<<value;
			recs[3*(pos.x/3) + pos.y/3] |= 1<<value;
			findAnswer(idx + 1);
			// 초기화
			rows[pos.x] &= ~(1<<value);
			cols[pos.y] &= ~(1<<value);
			recs[3*(pos.x/3) + pos.y/3] &= ~(1<<value);
		}
	}
}
