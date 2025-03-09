package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17472 {
	static int n, m; 
	static int[][] map; // 지도
	static boolean visited[][]; // 섬 파악할 때 사용한 자료구조
	static boolean bridgeVisited[][][]; // 다리 만들 때 사용한 자료구조
	static ArrayList<int[]> bridges = new ArrayList<int[]>(); // 전체 다리 목록
	static ArrayList<Integer> linkedIslands = new ArrayList<Integer>(); // 현재 연결된 섬과 그 개수
	static HashSet<Integer> selectedBridges = new HashSet<Integer>(); // 선택된 다리들
	static int islandCnt = 0; // 전체 섬 개수
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int nowLength = 0; // 현재 다리 길이
	static int answer = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Integer>> linkInfo = new ArrayList<ArrayList<Integer>>(); // 현재 연결 정보

	// 전체 섬 파악
	static void search(int x, int y) {
		// TODO Auto-generated method stub
		int newX, newY;
		for (int dir = 0; dir < 4; dir++) {
			newX = x + dx[dir];
			newY = y + dy[dir];
			if (inRange(newX, newY) && map[newX][newY] != -1 && !visited[newX][newY]) {
				map[newX][newY] = map[x][y];
				visited[newX][newY] = true;
				search(newX, newY);
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && 0 <= y && x < n && y < m;
	}

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = map[i][j] == 0 ? -1 : map[i][j];
			}
		}

		// 전체 섬 파악
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != -1 && !visited[i][j]) {
					visited[i][j] = true;
					map[i][j] = islandCnt;
					islandCnt++;
					search(i, j);
				}
			}
		}

		// 초기화
		bridgeVisited = new boolean[n][m][4];
		for (int i = 0; i < islandCnt; i++) {
			linkedIslands.add(0);
		}

	
		// 다리 짓기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != -1) {
					makeBridges(i, j, map[i][j]);
				}
			}
		}

		// 다리 고르기
		selectBridges();

		if (answer == Integer.MAX_VALUE)
			answer = -1;

		System.out.println(answer);
	}

	// 다리 고르기
	static void selectBridges() {
		if (nowLength > answer)
			return;
		
		// 섬 4개 다 연결 되었는지 확인
		boolean isAns = true;
		for (int islands : linkedIslands) {
			if (islands == 0) {
				isAns = false;
				break;
			}
		}

		if (isAns) {
			answer = Math.min(nowLength, answer);
			return;
		}

		for (int i = 0; i < bridges.size(); i++) {
			int startN = map[bridges.get(i)[0]][bridges.get(i)[1]];
			int endN = map[bridges.get(i)[2]][bridges.get(i)[3]];
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(startN);
			list.add(endN);

			// 연결 가능한 다리면 연결
			if (!linkInfo.contains(list) && check(startN, endN)) {
				nowLength += Math.abs(bridges.get(i)[0] - bridges.get(i)[2] + bridges.get(i)[1] - bridges.get(i)[3])
						- 1;
				linkedIslands.set(endN, linkedIslands.get(endN) + 1);
				linkedIslands.set(startN, linkedIslands.get(startN) + 1);
				selectedBridges.add(i);
				linkInfo.add(list);
				selectBridges();

				// 연결 취소
				nowLength -= Math.abs(bridges.get(i)[0] - bridges.get(i)[2] + bridges.get(i)[1] - bridges.get(i)[3])
						- 1;
				linkedIslands.set(endN, linkedIslands.get(endN) - 1);
				linkedIslands.set(startN, linkedIslands.get(startN) - 1);
				selectedBridges.remove(i);
				linkInfo.remove(list);
			}
		}
	}

	static boolean check(int startN, int endN) {
		if (selectedBridges.isEmpty())
			return true;
		for (int i : selectedBridges) {
			if (map[bridges.get(i)[0]][bridges.get(i)[1]] == startN || map[bridges.get(i)[0]][bridges.get(i)[1]] == endN
					|| map[bridges.get(i)[2]][bridges.get(i)[3]] == startN
					|| map[bridges.get(i)[2]][bridges.get(i)[3]] == endN) {

				return true;
			}
		}
		return false;
	}

	static void makeBridges(int x, int y, int startN) {
		// TODO Auto-generated method stub
		int newX, newY;
		for (int dir = 0; dir < 4; dir++) {
			if (bridgeVisited[x][y][dir]) {
				continue;
			}

			newX = x + dx[dir];
			newY = y + dy[dir];

			while (inRange(newX, newY) && map[newX][newY] == -1) {
				newX += dx[dir];
				newY += dy[dir];
			}
			if (inRange(newX, newY) && map[newX][newY] != startN && Math.abs(newX - x + newY - y) - 1 >= 2) {
				int[] b = { x, y, newX, newY, dir };
				bridgeVisited[x][y][dir] = true;
				if (dir >= 2)
					bridgeVisited[newX][newY][(dir + 1) % 4] = true;

				else
					bridgeVisited[newX][newY][(dir + 1) % 2] = true;

				bridges.add(b);

			}
		}
	}
}