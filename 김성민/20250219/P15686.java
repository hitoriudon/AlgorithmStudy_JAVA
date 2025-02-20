import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15686 {

	static int[][] map;
	static int mapSize;
	static int chickenAmount;

	// 집들의 좌표 저장
	static ArrayList<int[]> homes = new ArrayList<>();
	// 치킨집들의 좌표 저장
	static ArrayList<int[]> chickens = new ArrayList<>();
	static boolean[] visited;

	static int minDist = Integer.MAX_VALUE;

	static void recur(int chickCount, int startIdx) {
		// 현재 선택한 치킨집의 수가 남길 원하는 치킨집의 수와 같을때
		if (chickCount == chickenAmount) {
			int distSum = 0;
			for (int homeIdx = 0; homeIdx < homes.size(); homeIdx++) {
				int homeMinDist = Integer.MAX_VALUE;
				for (int chickenIdx = 0; chickenIdx < chickens.size(); chickenIdx++) {
					// 방문한 치킨 집이면 집과의 거리를 확인 후 가장 짧은 거리 저장
					if (visited[chickenIdx]) {
						int dist = Math.abs(homes.get(homeIdx)[0] - chickens.get(chickenIdx)[0])
								+ Math.abs(homes.get(homeIdx)[1] - chickens.get(chickenIdx)[1]);
						homeMinDist = Math.min(homeMinDist, dist);
					}
				}
				distSum += homeMinDist;
			}
			minDist = Math.min(minDist, distSum);
		}

		for (int chickenIdx = startIdx; chickenIdx < chickens.size(); chickenIdx++) {
			if (!visited[chickenIdx]) {
				visited[chickenIdx] = true;
				recur(chickCount + 1, chickenIdx + 1);
				visited[chickenIdx] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		mapSize = Integer.parseInt(st.nextToken());
		chickenAmount = Integer.parseInt(st.nextToken());

		map = new int[mapSize][mapSize];
		
		for (int y = 0; y < mapSize; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < mapSize; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1) {
					homes.add(new int[] { y, x });
				} else if (map[y][x] == 2) {
					chickens.add(new int[] { y, x });
				}
			}
		}
		visited = new boolean[chickens.size()];

		recur(0, 0);

		System.out.println(minDist);
	}

}
